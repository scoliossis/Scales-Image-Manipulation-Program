package com.scales.Listeners;

import com.scales.Elements.Element;
import com.scales.Main;
import com.scales.Utils.MouseUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionListener extends MouseAdapter {
    public static int lastMouseDragX = -1, lastMouseDragY = -1;
    public static Element CURRENTLY_DRAGGING_ELEMENT = null;

    @Override
    public void mouseDragged(MouseEvent e) {
        if (CURRENTLY_DRAGGING_ELEMENT != null) {
            CURRENTLY_DRAGGING_ELEMENT.handleDrag(e);
            CURRENTLY_DRAGGING_ELEMENT.handleHover(e);
        }

        lastMouseDragX = MouseUtil.getX(e);
        lastMouseDragY = MouseUtil.getY(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        handleMouseMoveEvent(e);
    }

    private void handleMouseMoveEvent(MouseEvent e) {
        for (Element element : Main.ELEMENTS.reversed()) {
            // returns if we are hovering an element to stop the cursor being reset
            if (MouseUtil.isMouseHovering(e, element) && element.handleHover(e)) return;
        }

        // reset the cursor to the default cursor because no element is hovered
        Main.FRAME.setCursor(Cursor.getDefaultCursor());
    }
}