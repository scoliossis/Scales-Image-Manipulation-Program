package com.scales.Listeners;

import com.scales.Main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

public class MouseWheelListener implements java.awt.event.MouseWheelListener {
    private static final int SCALE_INCREMENT = 10;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (KeyListener.isKeyDown(KeyEvent.VK_CONTROL)) {
            // store old scale
            double oldScale = Main.CANVAS.scale;
            
            Main.CANVAS.scale -= e.getUnitsToScroll() / 10f;
            Main.CANVAS.scale = Math.clamp(Main.CANVAS.scale, 0.1f, 30);
            
            double scaleFactor = Main.CANVAS.scale / oldScale;

            // make sure that the pixel the mouse is over is the same after zooming in
            Main.CANVAS.x = (int) (e.getX() - ((e.getX() - Main.CANVAS.x) * scaleFactor));
            Main.CANVAS.y = (int) (e.getY() - ((e.getY() - Main.CANVAS.y) * scaleFactor));
        }
        else {
            // scrolling just by "getUnitsToScroll" is very slow, so we multiply it by a constant
            int scrollAmount = -e.getUnitsToScroll() * SCALE_INCREMENT;
            if (KeyListener.isKeyDown(KeyEvent.VK_SHIFT)) Main.CANVAS.x += scrollAmount;
            else Main.CANVAS.y += scrollAmount;
        }
    }
}