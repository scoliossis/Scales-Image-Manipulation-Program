package com.scales.Elements;

import com.scales.Listeners.MouseMotionListener;

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
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, CANVAS_IMAGE.getWidth(), CANVAS_IMAGE.getHeight());
        g.drawImage(CANVAS_IMAGE, 0, 0, null);
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        int x = this.applyTransform(e.getX(), this.x);
        int y = this.applyTransform(e.getY(), this.y);
        if (x >= 0 && x < CANVAS_IMAGE.getWidth() && y >= 0 && y < CANVAS_IMAGE.getHeight()) {
            Canvas.CANVAS_IMAGE.setRGB(x, y, Color.RED.getRGB());
        }

        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        Canvas.IMAGE_GRAPHICS.setColor(Color.RED);
        Canvas.IMAGE_GRAPHICS.drawLine(
                this.applyTransform(MouseMotionListener.lastMouseDragX, this.x),
                this.applyTransform(MouseMotionListener.lastMouseDragY, this.y),
                this.applyTransform(e.getX(), this.x),
                this.applyTransform(e.getY(), this.y)
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

        int newWidth = Math.max(this.applyTransform(width, this.x), 1);
        int newHeight = Math.max(this.applyTransform(height, this.y), 1);

        CANVAS_IMAGE = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();
        IMAGE_GRAPHICS.drawImage(oldImage, 0, 0, null);
        this.width = newWidth;
        this.height = newHeight;
    }
}