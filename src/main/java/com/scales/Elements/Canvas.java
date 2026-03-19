package com.scales.Elements;

import com.scales.Listeners.MouseMotionListener;
import com.scales.Main;
import com.scales.Utils.MouseUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Canvas extends Element {
    private static final int DEFAULT_CANVAS_WIDTH = 200, DEFAULT_CANVAS_HEIGHT = 200;

    public static BufferedImage CANVAS_IMAGE = new BufferedImage(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public static Graphics2D IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();

    public Canvas() {
        // priority 0 means it is drawn first
        super(0, 0, DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
    }

    @Override
    public void draw(Graphics2D g) {
        g.scale(this.scale, this.scale);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, CANVAS_IMAGE.getWidth(), CANVAS_IMAGE.getHeight());
        g.drawImage(CANVAS_IMAGE, 0, 0, null);
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        return false;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        if (MouseMotionListener.lastMouseDragX == -1) return false;

        Canvas.IMAGE_GRAPHICS.setColor(Color.RED);
        Canvas.IMAGE_GRAPHICS.drawLine(
                this.applyTransform(MouseMotionListener.lastMouseDragX),
                this.applyTransform(MouseMotionListener.lastMouseDragY),
                this.applyTransform(e.getX()),
                this.applyTransform(e.getY())
        );

        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        return false;
    }

    public void resizeCanvas(int width, int height) {
        // store the old image so it can be redrawn to the new resized image.
        BufferedImage oldImage = CANVAS_IMAGE;
        IMAGE_GRAPHICS.dispose();

        int newWidth = this.applyTransform(width);
        int newHeight = this.applyTransform(height);

        CANVAS_IMAGE = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();
        IMAGE_GRAPHICS.drawImage(oldImage, 0, 0, null);
        this.width = newWidth;
        this.height = newHeight;
    }
}