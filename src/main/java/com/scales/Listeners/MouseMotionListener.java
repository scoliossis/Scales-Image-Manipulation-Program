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

        lastMouseDragX = e.getX();
        lastMouseDragY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        handleMouseMoveEvent(e);
    }

    private void handleMouseDragEvent(MouseEvent e) {
        for (Element element : Main.ELEMENTS.reversed()) {
            // breaks out of the loop if the element currently hovered wants to stop other elements from handling the drag event
            if (MouseUtil.isMouseHovering(e, element) && element.handleDrag(e)) break;
        }
    }

    private void handleMouseMoveEvent(MouseEvent e) {
        // reset the cursor to the default cursor, the element can then handle if it wants to change the cursor to something else
        Main.FRAME.setCursor(Cursor.getDefaultCursor());

        for (Element element : Main.ELEMENTS.reversed()) {
            // breaks out of the loop if the element currently hovered wants to stop other elements from handling the hover event
            if (MouseUtil.isMouseHovering(e, element) && element.handleHover(e)) break;
        }
    }
}