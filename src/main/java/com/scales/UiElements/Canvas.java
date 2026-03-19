package com.scales.UiElements;

import com.scales.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Canvas {
    public static BufferedImage CANVAS_IMAGE = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    public static Graphics2D IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();

    public static int CANVAS_X, CANVAS_Y;
    public static double CANVAS_SCALE = 1;

    public static void draw(Graphics2D g) {
        AffineTransform currentTransform = g.getTransform();
        g.translate(CANVAS_X, CANVAS_Y);
        g.scale(CANVAS_SCALE, CANVAS_SCALE);

        g.drawImage(CANVAS_IMAGE, 0, 0, null);

        g.setTransform(currentTransform);
    }

    public static void resizeCanvas(int width, int height) {
        BufferedImage oldImage = CANVAS_IMAGE;
        IMAGE_GRAPHICS.dispose();

        CANVAS_IMAGE = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();
        IMAGE_GRAPHICS.drawImage(oldImage, 0, 0, null);
    }
}