package com.scales.Elements;

import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeCanvasButton extends Element {
    public ResizeCanvasButton() {
        super(495, 495, 10, 10);
    }

    @Override
    public void draw(Graphics2D g) {
        updatePosition();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
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
        this.x = Main.CANVAS.x+Main.CANVAS.width - (this.width / 2);
        this.y = Main.CANVAS.y+Main.CANVAS.height - (this.height / 2);
    }
}