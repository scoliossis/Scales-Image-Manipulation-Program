package com.scales.Cursors.impl;

import com.scales.Cursors.Cursor;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PaintBrush extends Cursor {
    public PaintBrush() {
        // todo: make icon
        super(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
    }

    @Override
    public void handleClick(MouseEvent e) {
        Main.PENCIL.handleClick(e);
    }

    @Override
    public void handleDrag(MouseEvent e) {
        getParentGraphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Main.PENCIL.handleDrag(e);
        getParentGraphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    @Override
    public void handleRelease(MouseEvent e) {

    }
}