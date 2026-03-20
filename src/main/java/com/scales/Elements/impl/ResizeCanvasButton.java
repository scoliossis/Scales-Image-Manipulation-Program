package com.scales.Elements.impl;

import com.scales.Elements.Element;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeCanvasButton extends Element {
    public ResizeCanvasButton() {
        super(
                () -> Main.CANVAS.undoTransform(Main.CANVAS.width.getAsInt(), Main.CANVAS.x.getAsInt()),
                () -> Main.CANVAS.undoTransform(Main.CANVAS.height.getAsInt(), Main.CANVAS.y.getAsInt()),
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
        Main.CANVAS.resizeCanvas(e.getX(), e.getY());
        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        return true;
    }
}