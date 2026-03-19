package com.scales.Elements;

import com.scales.Listeners.MouseMotionListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Canvas extends Element {
    private static final int DEFAULT_CANVAS_WIDTH = 500, DEFAULT_CANVAS_HEIGHT = 500;

    public static BufferedImage CANVAS_IMAGE = new BufferedImage(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public static Graphics2D IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();

    public Canvas() {
        // priority 0 means it is drawn first
        super(0, 0, DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
    }

    @Override
    public void draw(Graphics2D g) {
        //g.scale((double) this.width / CANVAS_IMAGE.getWidth(), (double) this.height / CANVAS_IMAGE.getHeight());

        g.drawRect(0, 0, CANVAS_IMAGE.getWidth(), CANVAS_IMAGE.getHeight());
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
        Canvas.IMAGE_GRAPHICS.drawLine(MouseMotionListener.lastMouseDragX, MouseMotionListener.lastMouseDragY, e.getX(), e.getY());

        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        return false;
    }

    public void resizeCanvas(int width, int height) {
        // store old image so it can be redrawn to the new resized image.
        BufferedImage oldImage = CANVAS_IMAGE;
        IMAGE_GRAPHICS.dispose();

        CANVAS_IMAGE = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();
        IMAGE_GRAPHICS.drawImage(oldImage, 0, 0, null);
        this.width = width;
        this.height = height;
    }
}