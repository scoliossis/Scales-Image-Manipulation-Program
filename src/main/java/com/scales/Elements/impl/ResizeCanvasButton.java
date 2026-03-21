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

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width.getAsInt(), height.getAsInt());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width.getAsInt(), height.getAsInt());
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        Canvas.renderWidth = Main.CANVAS.applyTransform(MouseUtil.getX(e), Canvas.canvasOffsetX);
        Canvas.renderHeight = Main.CANVAS.applyTransform(MouseUtil.getY(e), Canvas.canvasOffsetY);
        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        return true;
    }
    
    @Override
    public void handleRelease(MouseEvent e) {
        Main.CANVAS.resizeCanvas();
    }
}