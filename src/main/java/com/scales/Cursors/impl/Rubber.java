package com.scales.Cursors.impl;

import com.scales.Cursors.Cursor;
import com.scales.Elements.impl.Canvas;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Rubber extends Cursor {
    public int DIAMETER = 10;

    public Rubber() {
        // todo: make icon
        super(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
    }

    @Override
    public void handleClick(MouseEvent e) {
        Composite composite = Canvas.IMAGE_GRAPHICS.getComposite();
        Canvas.IMAGE_GRAPHICS.setComposite(AlphaComposite.Clear);
        Canvas.IMAGE_GRAPHICS.setColor(new Color(0, 0, 0, 0));
        Main.PENCIL.drawDot(e, DIAMETER);
        Canvas.IMAGE_GRAPHICS.setComposite(composite);
    }

    @Override
    public void handleDrag(MouseEvent e) {
        Composite composite = Canvas.IMAGE_GRAPHICS.getComposite();
        Canvas.IMAGE_GRAPHICS.setComposite(AlphaComposite.Clear);
        Canvas.IMAGE_GRAPHICS.setColor(new Color(0, 0, 0, 0));
        Main.PENCIL.drawLine(e, DIAMETER);
        Canvas.IMAGE_GRAPHICS.setComposite(composite);
    }

    @Override
    public void handleRelease(MouseEvent e) {

    }
}