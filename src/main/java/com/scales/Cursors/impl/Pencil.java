package com.scales.Cursors.impl;

import com.scales.Cursors.Cursor;
import com.scales.Elements.impl.Canvas;
import com.scales.Listeners.MouseMotionListener;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Pencil extends Cursor {
    public Pencil() {
        // todo: make icon
        super(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
    }

    @Override
    public void handleClick(MouseEvent e) {
        int x = PARENT.applyTransform(e.getX(), PARENT.x.getAsInt());
        int y = PARENT.applyTransform(e.getY(), PARENT.y.getAsInt());
        if (x >= 0 && x < getParentImage().getWidth() && y >= 0 && y < getParentImage().getHeight()) {
            getParentImage().setRGB(x, y, CURSOR_COLOR.getRGB());
        }
    }

    @Override
    public void handleDrag(MouseEvent e) {
        Canvas.IMAGE_GRAPHICS.setColor(CURSOR_COLOR);
        Canvas.IMAGE_GRAPHICS.drawLine(
                PARENT.applyTransform(MouseMotionListener.lastMouseDragX, PARENT.x.getAsInt()),
                PARENT.applyTransform(MouseMotionListener.lastMouseDragY, PARENT.y.getAsInt()),
                PARENT.applyTransform(e.getX(), PARENT.x.getAsInt()),
                PARENT.applyTransform(e.getY(), PARENT.y.getAsInt())
        );
    }

    @Override
    public void handleRelease(MouseEvent e) {

    }
}