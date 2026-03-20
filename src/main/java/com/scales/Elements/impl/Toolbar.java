package com.scales.Elements.impl;

import com.scales.Cursors.Cursor;
import com.scales.Elements.Element;
import com.scales.Main;
import com.scales.Utils.MouseUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Toolbar extends Element {
    public Toolbar() {
        super(
                () -> 0,
                () -> 0,
                Main.FRAME::getWidth,
                () -> 75
        );
    }

    public static final int ICON_SIZE = 50;
    public static final int ICON_SPACING = 10;
    private int getIconX(int i) {
        return (ICON_SPACING+ICON_SIZE) * i + ICON_SPACING;
    }
    public int getIconY() {
        return (this.height.getAsInt() - ICON_SIZE) / 2;
    }

    private Cursor getHoveredCursor(MouseEvent e) {
        for (int i = 0; i < Main.CURSORS.size(); i++) {
            if (MouseUtil.isMouseHovering(e, getIconX(i), getIconY(), ICON_SIZE, ICON_SIZE)) {
                return Main.CURSORS.get(i);
            }
        }

        return null;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(199, 199, 199));
        g.fillRect(0, 0, width.getAsInt(), height.getAsInt());

        for (int i = 0; i < Main.CURSORS.size(); i++) {
            Cursor cursor = Main.CURSORS.get(i);
            // if the cursor box being drawn right now is the current cursor, its drawn darker.
            g.setColor(Main.currentCursor == cursor ? Color.WHITE.darker() : Color.WHITE);
            g.fillRect(getIconX(i), getIconY(), ICON_SIZE, ICON_SIZE);
            g.drawImage(cursor.ICON, getIconX(i), getIconY(), ICON_SIZE, ICON_SIZE, null);
        }
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        if (getHoveredCursor(e) != null) {
            Main.currentCursor = getHoveredCursor(e);
        }

        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        if (getHoveredCursor(e) != null) Main.FRAME.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        else Main.FRAME.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        return true;
    }
}
