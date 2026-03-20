package com.scales.Utils;

import com.scales.Elements.Element;
import com.scales.Main;

import java.awt.event.MouseEvent;

public class MouseUtil {
    public static boolean isMouseHovering(MouseEvent e, Element element) {
        return isMouseHovering(e, element.x.getAsInt(), element.y.getAsInt(), (int) (element.width.getAsInt()*element.scale), (int) (element.height.getAsInt()*element.scale));
    }
    public static boolean isMouseHovering(MouseEvent e, int x, int y, int width, int height) {
        return isMouseHovering(getX(e), getY(e), x, y, width, height);
    }
    public static boolean isMouseHovering(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public static int getX(MouseEvent event) {
        return event.getX();
    }
    public static int getY(MouseEvent event) {
        return event.getY() - Main.FRAME.getInsets().top;
    }
}
