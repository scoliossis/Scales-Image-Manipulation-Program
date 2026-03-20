package com.scales.Cursors;

import com.scales.Elements.impl.Canvas;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class Cursor {
    public static Color CURSOR_COLOR = Color.RED;

    public final BufferedImage ICON;

    public Cursor(BufferedImage ICON) {
        this.ICON = ICON;
    }

    public final Canvas PARENT = Main.CANVAS;
    public BufferedImage getParentImage() {
        return Canvas.CANVAS_IMAGE;
    }
    public Graphics2D getParentGraphics() {
        return Canvas.IMAGE_GRAPHICS;
    }

    public abstract void handleClick(MouseEvent e);
    public abstract void handleDrag(MouseEvent e);
    public abstract void handleRelease(MouseEvent e);
}
