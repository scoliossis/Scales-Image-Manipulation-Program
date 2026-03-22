package com.scales.Listeners;

import com.scales.Elements.impl.Canvas;
import com.scales.Main;
import com.scales.Utils.MouseUtil;

import java.awt.event.MouseWheelEvent;

public class MouseWheelListener implements java.awt.event.MouseWheelListener {
    private static final int SCALE_INCREMENT = 10;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            // store old scale
            double oldScale = Main.CANVAS.scale;
            
            Main.CANVAS.scale -= (e.getUnitsToScroll() / 20f) * Main.CANVAS.scale;
            Main.CANVAS.scale = Math.clamp(Main.CANVAS.scale, 0.1f, 30);

            double scaleFactor = Main.CANVAS.scale / oldScale;

            // make sure that the pixel the mouse is over is the same after zooming in
            // Use double precision to avoid precision loss with large offsets
            double mouseX = MouseUtil.getX(e);
            double mouseY = MouseUtil.getY(e);
            double canvasX = Main.CANVAS.x.getAsInt();
            double canvasY = Main.CANVAS.y.getAsInt();

            Canvas.canvasOffsetX = (int) (mouseX - ((mouseX - canvasX) * scaleFactor));
            Canvas.canvasOffsetY = (int) (mouseY - ((mouseY - canvasY) * scaleFactor));
        }
        else {
            // scrolling just by "getUnitsToScroll" is very slow, so we multiply it by a constant
            int scrollAmount = -e.getUnitsToScroll() * SCALE_INCREMENT;
            if (e.isShiftDown()) Canvas.canvasOffsetX -= scrollAmount;
            else Canvas.canvasOffsetY += scrollAmount;
        }
    }
}