package com.scales.Elements;

import com.scales.Main;
import com.scales.Utils.MouseUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeCanvasButton extends Element {
    public ResizeCanvasButton() {
        super(500, 500, 10, 10);
    }

    @Override
    public void draw(Graphics2D g) {
        updatePosition();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        return false;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        Main.CANVAS.resizeCanvas(e.getX(), e.getY());
        updatePosition();
        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        return true;
    }

    private void updatePosition() {
        this.x = Main.CANVAS.undoTransform(Main.CANVAS.width);
        this.y = Main.CANVAS.undoTransform(Main.CANVAS.height);
    }
}