package com.scales.Listeners;

import com.scales.Elements.impl.Canvas;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ComponentListener extends ComponentAdapter {
    private static int prevWidth, prevHeight;

    @Override
    public void componentResized(ComponentEvent e) {
        Canvas.canvasOffsetX += (e.getComponent().getWidth() - prevWidth) / 2;
        Canvas.canvasOffsetY += (e.getComponent().getHeight() - prevHeight) / 2;

        prevWidth = e.getComponent().getWidth();
        prevHeight = e.getComponent().getHeight();
    }
}
