package com.scales.Elements.impl;

import com.scales.Elements.Element;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Canvas extends Element {
    private static final int DEFAULT_CANVAS_WIDTH = 200, DEFAULT_CANVAS_HEIGHT = 200;

    public BufferedImage CANVAS_IMAGE = new BufferedImage(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
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
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.width.getAsInt(), this.height.getAsInt());

        g.clipRect(0, 0, this.width.getAsInt(), this.height.getAsInt());

        // todo: fix upper bound
        int leftCulled = Math.clamp(canvasOffsetX < 0 ? (int) (Math.abs(canvasOffsetX) / this.scale) : 0, 0, Integer.MAX_VALUE);
        int topCulled = Math.clamp(canvasOffsetY < 0 ? (int) (Math.abs(canvasOffsetY) / this.scale) : 0, 0, Integer.MAX_VALUE);

        for (Canvas canvas : Main.CANVAS_HIERARCHY) {
            int rightCulled = Math.min(canvas.CANVAS_IMAGE.getWidth()-leftCulled-1, (int) Math.ceil(Main.FRAME.getWidth() / this.scale) + 1);
            int bottomCulled = Math.min(canvas.CANVAS_IMAGE.getHeight()-topCulled-1, (int) Math.ceil(Main.FRAME.getHeight() / this.scale) + 1);

            if (rightCulled <= 0 || bottomCulled <= 0) continue;

            g.drawImage(
                    // this works as an optimisation, but it also fixes in issue where large images become invisible when zoomed in too far
                    canvas.CANVAS_IMAGE.getSubimage(leftCulled, topCulled, rightCulled, bottomCulled),
                    leftCulled,
                    topCulled,
                    null
            );
        }

        g.setClip(null);
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        //HandleUndoTimeline.bufferCanvas();
        Main.currentCursor.handleClick(e);

        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        Main.currentCursor.handleDrag(e);

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
        }
    }

    public void setCanvasImage(BufferedImage image) {
        this.IMAGE_GRAPHICS.dispose();
        this.CANVAS_IMAGE = image;
        this.IMAGE_GRAPHICS = (Graphics2D) image.getGraphics();

        renderWidth = image.getWidth();
        renderHeight = image.getHeight();
    }
}