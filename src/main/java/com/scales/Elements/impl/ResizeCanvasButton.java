package com.scales.Elements.impl;

import com.scales.Elements.Element;
import com.scales.Main;
import com.scales.Utils.MouseUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeCanvasButton extends Element {
    public ResizeCanvasButton() {
        super(
                () -> (int) (Canvas.canvasOffsetX + (Canvas.renderWidth * Main.CANVAS.scale)),
                () -> (int) (Canvas.canvasOffsetY + (Canvas.renderHeight * Main.CANVAS.scale)),
                () -> 10,
                () -> 10
        );
    }

    private boolean resizing = false;

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width.getAsInt(), height.getAsInt());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width.getAsInt(), height.getAsInt());

        if (resizing) {
            g.setColor(new Color(99, 108, 255,100));
            g.drawRect(-Canvas.renderWidth, -Canvas.renderHeight, Canvas.renderWidth, Canvas.renderHeight);
        }
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        resizing = true;
        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        Canvas.renderWidth = Math.max(Main.CANVAS.applyTransform(MouseUtil.getX(e), Canvas.canvasOffsetX), 1);
        Canvas.renderHeight = Math.max(Main.CANVAS.applyTransform(MouseUtil.getY(e), Canvas.canvasOffsetY), 1);
        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        return true;
    }
    
    @Override
    public void handleRelease(MouseEvent e) {
        resizing = false;
        Main.CANVAS.resizeCanvas();
    }
}