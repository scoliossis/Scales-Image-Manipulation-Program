package com.scales.Elements.impl.ColourPicker;

import com.scales.Elements.Element;
import com.scales.Elements.impl.Toolbar;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ColourPickerButton extends Element {
    public boolean open = false;

    public ColourPickerButton() {
        super(
                () -> Main.FRAME.getWidth() - Toolbar.ICON_SIZE - Toolbar.ICON_SPACING,
                Main.TOOLBAR::getIconY,
                () -> Toolbar.ICON_SIZE,
                () -> Toolbar.ICON_SIZE
        );
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(com.scales.Cursors.Cursor.CURSOR_COLOR);
        g.fillRect(0, 0, width.getAsInt(), height.getAsInt());
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        Main.COLOUR_PICKER_BUTTON.open = !Main.COLOUR_PICKER_BUTTON.open;
        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return true;
    }
}