package com.scales.Utils;

import com.scales.Elements.impl.Canvas;
import com.scales.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageRenderingUtil {
    public static HashMap<Canvas, Long> ANTIALIAS_QUEUE = new HashMap<>();
    public static double lastBackgroundScale = -1;
    public static long backgroundLastQueued = 0;
    private static final long ANTIALIAS_DELAY = 200;

    public static final int TRANSPARENCY_INDENT_SIZE = 10;
    public static BufferedImage TRANSPARENT_BACKGROUND = null;
    public static Image CACHED_BACKGROUND = null;

    public static void handleAntiAliasing() {
        for (Canvas canvas : ANTIALIAS_QUEUE.keySet().toArray(new Canvas[0])) {
            Long lastPush = ANTIALIAS_QUEUE.get(canvas);
            if (lastPush == null || System.currentTimeMillis() - lastPush < ANTIALIAS_DELAY) continue;

            ANTIALIAS_QUEUE.remove(canvas);
            antialiasImage(canvas);
        }

        if (backgroundLastQueued != -1 && System.currentTimeMillis() - backgroundLastQueued >= ANTIALIAS_DELAY) {
            getBackground(true);
        }
    }

    // todo: when using the resize button, the extra background isnt drawn, maybe fix
    public static Image getBackground(boolean antialias) {
        // we arent currently trying to antialias a background, meaning the current one is fine
        if (backgroundLastQueued == -1) return CACHED_BACKGROUND;

        // create a new transparent background when antialiasing
        TRANSPARENT_BACKGROUND = TRANSPARENT_BACKGROUND == null || antialias
                ? ImageRenderingUtil.getTransparencyOverlay(Canvas.renderWidth, Canvas.renderHeight, TRANSPARENCY_INDENT_SIZE)
                : TRANSPARENT_BACKGROUND;
        if (antialias) {
            backgroundLastQueued = -1;
        }

        // store the antialiased background and keep using it until we antialias again
        CACHED_BACKGROUND = ImageRenderingUtil.getVisibleImage(
                TRANSPARENT_BACKGROUND,
                ImageRenderingUtil.getScaledPos(Canvas.canvasOffsetX, Main.CANVAS.scale),
                ImageRenderingUtil.getScaledPos(Canvas.canvasOffsetY, Main.CANVAS.scale),
                Main.CANVAS.scale,
                antialias
        );

        return CACHED_BACKGROUND;
    }

    public static void antialiasImage(Canvas canvas) {
        canvas.RENDERED_IMAGE = getVisibleImage(canvas, true);
    }

    public static Image getVisibleImage(Canvas canvas, boolean antialias) {
        return ImageRenderingUtil.getVisibleImage(
                canvas.CANVAS_IMAGE,
                ImageRenderingUtil.getScaledPos(Canvas.canvasOffsetX, Main.CANVAS.scale),
                ImageRenderingUtil.getScaledPos(Canvas.canvasOffsetY, Main.CANVAS.scale),
                Main.CANVAS.scale,
                antialias
        );
    }

    public static Image getVisibleImage(BufferedImage canvasImage, int leftOffset, int topOffset, double scale, boolean antialias) {
        // get the max x,y values of the canvas that are visible.
        int rightCulled = (int) Math.min(canvasImage.getWidth()-leftOffset, Math.ceil(Main.FRAME.getWidth()/scale)+1);
        int bottomCulled = (int) Math.min(canvasImage.getHeight()-topOffset, Math.ceil(Main.FRAME.getHeight()/scale)+1);

        if (rightCulled <= 0 || bottomCulled <= 0) return null;

        // crop the image to the visible area.
        BufferedImage croppedImage = canvasImage.getSubimage(leftOffset, topOffset, rightCulled, bottomCulled);

        // scale the image with antialiasing
        return croppedImage.getScaledInstance((int) (rightCulled*scale), (int) (bottomCulled*scale), antialias ? Image.SCALE_AREA_AVERAGING : Image.SCALE_DEFAULT);
    }

    public static BufferedImage getTransparencyOverlay(int width, int height, int increment) {
        BufferedImage overlay = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = overlay.createGraphics();

        // rendering a whole background then only drawing on odd pixels is faster
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(0, 0, 0, .2f));
        int loops = 0;
        for (int y = 0; y <= height; y+=increment) {
            for (int x = loops % 2 == 0 ? increment : 0; x <= width; x+=increment*2) {
                g.fillRect(x, y, increment, increment);
            }
            loops++;
        }

        return overlay;
    }

    // get the min x,y values of the canvas that are visible.
    public static int getScaledPos(int i, double scale) {
        return (int) (Math.max(i < 0 ? Math.abs(i) : 0, 0) / scale);
    }
    public static int getRenderPos(int i, double scale) {
        return  (int) (getScaledPos(i, scale)*scale);
    }
}
