package com.scales.Utils;

import com.scales.Elements.Element;

import java.awt.event.MouseEvent;

public class MouseUtil {
    public static boolean isMouseHovering(MouseEvent e, Element element) {
        return isMouseHovering(e, element.x, element.y, (int) (element.width*element.scale), (int) (element.height*element.scale));
    }
    public static boolean isMouseHovering(MouseEvent e, int x, int y, int width, int height) {
        return e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height;
    }
}
