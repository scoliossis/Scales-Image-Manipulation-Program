package com.scales.Elements.impl.ColourPicker;

import com.scales.Elements.Element;
import com.scales.Elements.impl.Toolbar;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ColourPickerBackground extends Element {
    protected static final int BOX_DIAMETER = 220;
    protected static final int BAR_SIZE = 15;
    protected static final int BAR_SPACING = 5;
    protected static final int SATURATION_BRIGHTNESS_BOX_SIZE = BOX_DIAMETER-BAR_SIZE-BAR_SPACING*3;

    protected static final float[] HSB = Color.RGBtoHSB(getCurrentColour().getRGB(), getCurrentColour().getGreen(), getCurrentColour().getBlue(), new float[4]);
    protected static float OPACITY = getCurrentColour().getAlpha() / 255f;

    protected static Color getCurrentColour() {
        return com.scales.Cursors.Cursor.CURSOR_COLOR;
    }

    protected static final int BACKGROUND_Y = Main.TOOLBAR.getIconY() + Toolbar.ICON_SIZE;

    protected static void setColour() {
        // set the colour to our new edited HSB colours
        Color newHsbColour = Color.getHSBColor(HSB[0], 1-HSB[1], 1-HSB[2]);
        // set opacity
        com.scales.Cursors.Cursor.CURSOR_COLOR = new Color(newHsbColour.getRed(), newHsbColour.getGreen(), newHsbColour.getBlue(), (int) (OPACITY*255));
    }

    public ColourPickerBackground() {
        super(
                () -> Main.COLOUR_PICKER_BUTTON.x.getAsInt() - BOX_DIAMETER,
                () -> BACKGROUND_Y,
                () -> BOX_DIAMETER,
                () -> BOX_DIAMETER
        );
    }

    @Override
    public void draw(Graphics2D g) {
        if (!Main.COLOUR_PICKER_BUTTON.open) return;

        g.setColor(new Color(22,22,22));
        g.fillRect(0, 0, width.getAsInt(), height.getAsInt());
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        return Main.COLOUR_PICKER_BUTTON.open;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        if (!Main.COLOUR_PICKER_BUTTON.open) return false;

        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return true;
    }
}