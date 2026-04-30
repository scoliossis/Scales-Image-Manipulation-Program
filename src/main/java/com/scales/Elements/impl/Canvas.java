package com.scales.Elements.impl;

import com.scales.Elements.Element;
import com.scales.Main;
import com.scales.Utils.ImageRenderingUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Canvas extends Element {
    private static final int DEFAULT_CANVAS_WIDTH = 200, DEFAULT_CANVAS_HEIGHT = 200;

    public BufferedImage CANVAS_IMAGE = new BufferedImage(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public Image RENDERED_IMAGE = CANVAS_IMAGE;
    public Graphics2D IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();

    public static int
            canvasOffsetX = -DEFAULT_CANVAS_WIDTH / 2,
            canvasOffsetY = -DEFAULT_CANVAS_HEIGHT / 2;

    public static int renderWidth = DEFAULT_CANVAS_WIDTH, renderHeight = DEFAULT_CANVAS_HEIGHT;

    public Canvas() {
        super(
                () -> canvasOffsetX,
                () -> canvasOffsetY,
                () -> renderWidth,
                () -> renderHeight
        );
    }

    @Override
    public void draw(Graphics2D g) {
        g.clipRect(0, 0, this.width.getAsInt(), this.height.getAsInt());

        // undo canvas scaling for images, their scaling is handled by the ImageRenderingUtil class.
        g.scale(1/this.scale, 1/this.scale);

        int x = ImageRenderingUtil.getRenderPos(canvasOffsetX, this.scale);
        int y = ImageRenderingUtil.getRenderPos(canvasOffsetY, this.scale);

        g.drawImage(ImageRenderingUtil.getBackground(false), x, y, null);

        for (Canvas canvas : Main.CANVAS_HIERARCHY) {
            g.drawImage(getImageToRender(canvas), x, y, null);
        }

        g.setClip(null);
    }

    private Image getImageToRender(Canvas canvas) {
        return ImageRenderingUtil.ANTIALIAS_QUEUE.containsKey(canvas) ? ImageRenderingUtil.getVisibleImage(canvas, false) : canvas.RENDERED_IMAGE;
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        //HandleUndoTimeline.bufferCanvas();
        Main.currentCursor.handleClick(e);
        Main.CURRENT_CANVAS.queueAntialiasCanvas();

        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        Main.currentCursor.handleDrag(e);
        Main.CURRENT_CANVAS.queueAntialiasCanvas();

        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        return true;
    }

    public void resizeCanvas() {
        for (Canvas canvas : Main.CANVAS_HIERARCHY) {
            // store the old image so it can be redrawn to the new resized image.
            BufferedImage oldImage = canvas.CANVAS_IMAGE;
            canvas.IMAGE_GRAPHICS.dispose();

            canvas.CANVAS_IMAGE = new BufferedImage(
                    Math.max(canvas.applyTransform(renderWidth, 0), 1),
                    Math.max(canvas.applyTransform(renderHeight, 0), 1),
                    oldImage.getType()
            );

            canvas.IMAGE_GRAPHICS = (Graphics2D) canvas.CANVAS_IMAGE.getGraphics();

            canvas.IMAGE_GRAPHICS.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
            canvas.queueAntialiasCanvas();
        }

        queueAntialiasBackground();
    }

    public static void queueAntialiasAll() {
        for (Canvas canvas : Main.CANVAS_HIERARCHY) canvas.queueAntialiasCanvas();
        queueAntialiasBackground();
    }

    public void queueAntialiasCanvas() {
        ImageRenderingUtil.ANTIALIAS_QUEUE.put(this, System.currentTimeMillis());
    }

    public static void queueAntialiasBackground() {
        if (ImageRenderingUtil.backgroundLastQueued == -1) ImageRenderingUtil.lastBackgroundScale = Main.CANVAS.scale;
        ImageRenderingUtil.backgroundLastQueued = System.currentTimeMillis();
    }

    public void setCanvasImage(BufferedImage image) {
        renderWidth = image.getWidth();
        renderHeight = image.getHeight();

        this.IMAGE_GRAPHICS.dispose();
        this.CANVAS_IMAGE = image;
        this.IMAGE_GRAPHICS = (Graphics2D) image.getGraphics();
        ImageRenderingUtil.antialiasImage(this);
    }
}