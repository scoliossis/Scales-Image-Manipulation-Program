package com.scales.Elements.impl.ColourPicker;

import com.scales.Elements.Element;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.scales.Elements.impl.ColourPicker.ColourPickerBackground.*;

public class OpacitySlider extends Element {
    public OpacitySlider() {
        super(
                () -> Main.COLOUR_PICKER_BUTTON.x.getAsInt() - BOX_DIAMETER + BAR_SPACING,
                () -> ColourPickerBackground.BACKGROUND_Y + BAR_SPACING * 2 + SATURATION_BRIGHTNESS_BOX_SIZE,
                () -> SATURATION_BRIGHTNESS_BOX_SIZE,
                () -> BAR_SIZE
        );
    }

    @Override
    public void draw(Graphics2D g) {
        if (!Main.COLOUR_PICKER_BUTTON.open) return;

        for (int x = 0; x < SATURATION_BRIGHTNESS_BOX_SIZE; x++) {
            float opacity = (float) x / SATURATION_BRIGHTNESS_BOX_SIZE;
            g.setColor(new Color(1, 1, 1, opacity));
            g.fillRect(x, 0, 1, BAR_SIZE);
        }

        g.setColor(Color.WHITE);
        g.drawRect(
                (int) ((OPACITY * SATURATION_BRIGHTNESS_BOX_SIZE) - 3),
                -1,
                6,
                BAR_SIZE+1
        );
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        if (!Main.COLOUR_PICKER_BUTTON.open) return false;
        handleDrag(e);

        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        int mouseX = this.applyTransform(e.getX(), this.x.getAsInt());
        OPACITY = Math.clamp((float) (mouseX - BAR_SPACING) / SATURATION_BRIGHTNESS_BOX_SIZE, 0, 1);
        ColourPickerBackground.setColour();

        return false;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        if (!Main.COLOUR_PICKER_BUTTON.open) return false;

        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        return true;
    }
}