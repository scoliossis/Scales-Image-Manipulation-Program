package com.scales.Cursors.impl;

import com.scales.Cursors.Cursor;
import com.scales.Elements.impl.Canvas;
import com.scales.Listeners.MouseMotionListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Pencil extends Cursor {
    public int DIAMETER = 10;

    public Pencil() {
        // todo: make icon
        super(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
    }

    @Override
    public void handleClick(MouseEvent e) {
        Canvas.IMAGE_GRAPHICS.setColor(CURSOR_COLOR);
        drawDot(e, DIAMETER);
    }

    public void drawDot(MouseEvent e, int width) {
        int[] mouseCoords = fixMouseCoords(e.getX(), e.getY());
        Canvas.IMAGE_GRAPHICS.fillRect(mouseCoords[0] - width / 2, mouseCoords[1] - width / 2, width, width);
    }

    @Override
    public void handleDrag(MouseEvent e) {
        Canvas.IMAGE_GRAPHICS.setColor(CURSOR_COLOR);
        drawLine(e, DIAMETER);
    }

    public void drawLine(MouseEvent e, int width) {
        // fix coordinates to be local to the canvas.
        int[] lastMouse = fixMouseCoords(MouseMotionListener.lastMouseDragX, MouseMotionListener.lastMouseDragY);
        int[] newMouse = fixMouseCoords(e.getX(), e.getY());

        int xChange = newMouse[0] - lastMouse[0];
        int yChange = newMouse[1] - lastMouse[1];
        // no point rendering a 0-width line
        if (xChange == 0 && yChange == 0) return;

        // store previous stroke to be restored
        Stroke stroke = Canvas.IMAGE_GRAPHICS.getStroke();
        // set stroke width to the parameter passed
        Canvas.IMAGE_GRAPHICS.setStroke(new BasicStroke(width));
        // draw the line, offset from the previous position as we don't want the lines to overlap, as that messes with transparency
        Canvas.IMAGE_GRAPHICS.drawLine(lastMouse[0] + Math.clamp(xChange, -1, 1), lastMouse[1] + Math.clamp(yChange, -1, 1), newMouse[0], newMouse[1]);
        // restore stroke
        Canvas.IMAGE_GRAPHICS.setStroke(stroke);
    }

    private int[] fixMouseCoords(int x, int y) {
        return new int[] {
            PARENT.applyTransform(x, PARENT.x.getAsInt()),
            PARENT.applyTransform(y, PARENT.y.getAsInt())
        };
    }

    @Override
    public void handleRelease(MouseEvent e) {

    }
}