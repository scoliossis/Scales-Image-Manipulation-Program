package com.scales.Elements;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Element {
    public int x, y, width, height;
    public double scale = 1;

    public Element(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics2D g);

    /// return true if it wishes to block the click from registering to others
    public abstract boolean handleClick(MouseEvent e);
    public abstract boolean handleDrag(MouseEvent e);
    public abstract boolean handleHover(MouseEvent e);

    public int applyTransform(int n, int offset) {
        return (int) ((n / this.scale) - (offset / this.scale));
    }

    public int undoTransform(int n, int offset) {
        return (int) (n * this.scale) + offset;
    }
}
