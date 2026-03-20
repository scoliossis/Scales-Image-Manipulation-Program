package com.scales.Utils;

import com.scales.Elements.Element;

import java.awt.event.MouseEvent;

public class MouseUtil {
    public static boolean isMouseHovering(MouseEvent e, Element element) {
        return isMouseHovering(e, element.x.getAsInt(), element.y.getAsInt(), (int) (element.width.getAsInt()*element.scale), (int) (element.height.getAsInt()*element.scale));
    }
    public static boolean isMouseHovering(MouseEvent e, int x, int y, int width, int height) {
        return isMouseHovering(e.getX(), e.getY(), x, y, width, height);
    }
    public static boolean isMouseHovering(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
