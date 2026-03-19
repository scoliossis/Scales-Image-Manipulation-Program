package com.scales.Listeners;

import com.scales.UiElements.Canvas;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionListener extends MouseAdapter {
    @Setter private static int lastMouseDragX = -1, lastMouseDragY = -1;

    @Override
    public void mouseDragged(MouseEvent e) {
        Canvas.IMAGE_GRAPHICS.setColor(Color.RED);
        Canvas.IMAGE_GRAPHICS.drawLine(
                lastMouseDragX == -1 ? e.getX() : lastMouseDragX,
                lastMouseDragY == -1 ? e.getY() : lastMouseDragY,
                e.getX(),
                e.getY()
        );

        lastMouseDragX = e.getX();
        lastMouseDragY = e.getY();
    }
}