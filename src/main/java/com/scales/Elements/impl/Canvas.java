package com.scales.Elements.impl;

import com.scales.Elements.Element;
import com.scales.Main;
import com.scales.Utils.HandleUndoTimeline;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Canvas extends Element {
    private static final int DEFAULT_CANVAS_WIDTH = 200, DEFAULT_CANVAS_HEIGHT = 200;

    public static BufferedImage CANVAS_IMAGE = new BufferedImage(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public static Graphics2D IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();

    public static int
            canvasOffsetX = Main.DEFAULT_FRAME_SIZE.width / 2 - DEFAULT_CANVAS_WIDTH / 2,
            canvasOffsetY = Main.DEFAULT_FRAME_SIZE.height / 2 - DEFAULT_CANVAS_HEIGHT / 2;

    public Canvas() {
        super(
                () -> canvasOffsetX,
                () -> canvasOffsetY,
                () -> CANVAS_IMAGE.getWidth(),
                () -> CANVAS_IMAGE.getHeight()
        );
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, CANVAS_IMAGE.getWidth(), CANVAS_IMAGE.getHeight());
        g.drawImage(CANVAS_IMAGE, 0, 0, null);
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        HandleUndoTimeline.bufferCanvas();
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

    public void resizeCanvas(int width, int height) {
        // store the old image so it can be redrawn to the new resized image.
        BufferedImage oldImage = CANVAS_IMAGE;
        IMAGE_GRAPHICS.dispose();

        CANVAS_IMAGE = new BufferedImage(
                Math.max(this.applyTransform(width, this.x.getAsInt()), 1),
                Math.max(this.applyTransform(height, this.y.getAsInt()), 1),
                BufferedImage.TYPE_INT_ARGB
        );
        IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();
        IMAGE_GRAPHICS.drawImage(oldImage, 0, 0, null);
    }
}