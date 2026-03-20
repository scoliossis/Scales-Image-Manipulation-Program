package com.scales.Elements.impl.ColourPicker;

import com.scales.Elements.Element;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.scales.Elements.impl.ColourPicker.ColourPickerBackground.*;

public class HueSlider extends Element {
    public HueSlider() {
        super(
                () -> Main.COLOUR_PICKER_BUTTON.x.getAsInt() - BOX_DIAMETER + BAR_SPACING * 2 + SATURATION_BRIGHTNESS_BOX_SIZE,
                () -> ColourPickerBackground.BACKGROUND_Y + BAR_SPACING,
                () -> BAR_SIZE,
                () -> SATURATION_BRIGHTNESS_BOX_SIZE
        );
    }

    @Override
    public void draw(Graphics2D g) {
        if (!Main.COLOUR_PICKER_BUTTON.open) return;

        for (int y = 0; y < SATURATION_BRIGHTNESS_BOX_SIZE; y++) {
            float hue = (float) y / SATURATION_BRIGHTNESS_BOX_SIZE;

            g.setColor(Color.getHSBColor(hue, 1, 1));
            g.fillRect(0, y, BAR_SIZE, 1);
        }

        g.setColor(Color.WHITE);
        g.drawRect(
                -1,
                (int) (HSB[0] * SATURATION_BRIGHTNESS_BOX_SIZE) - 3,
                BAR_SIZE+1,
                6
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
        int mouseY = this.applyTransform(e.getY(), this.y.getAsInt());
        HSB[0] = Math.clamp((float) (mouseY - BAR_SPACING) / SATURATION_BRIGHTNESS_BOX_SIZE, 0, 1);
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