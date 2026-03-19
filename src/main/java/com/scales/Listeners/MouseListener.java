package com.scales.Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    @Override
    public void mouseReleased(MouseEvent e) {
        MouseMotionListener.setLastMouseDragX(-1);
        MouseMotionListener.setLastMouseDragY(-1);
    }
}
