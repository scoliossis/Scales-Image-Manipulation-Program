package com.scales.Elements;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.function.IntSupplier;

@RequiredArgsConstructor
public abstract class Element {
    @NonNull public IntSupplier x, y, width, height;
    public double scale = 1;

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