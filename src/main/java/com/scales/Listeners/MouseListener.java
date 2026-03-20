package com.scales.Listeners;

import com.scales.Elements.Element;
import com.scales.Main;
import com.scales.Utils.MouseUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    @Override
    public void mouseReleased(MouseEvent e) {
        MouseMotionListener.CURRENTLY_DRAGGING_ELEMENT = null;
        Main.currentCursor.handleRelease(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        boolean setDraggingElement = false;
        for (Element element : Main.ELEMENTS.reversed()) {
            // breaks out of the loop if the element currently hovered wants to stop other elements from handling the click event
            if (MouseUtil.isMouseHovering(e, element)) {
                boolean clicked = element.handleClick(e);
                if (!setDraggingElement && (clicked || element.handleDrag(e))) {
                    MouseMotionListener.lastMouseDragX = e.getX();
                    MouseMotionListener.lastMouseDragY = e.getY();
                    MouseMotionListener.CURRENTLY_DRAGGING_ELEMENT = element;
                    setDraggingElement = true;
                }
                if (clicked) break;
            }
        }
    }
}
