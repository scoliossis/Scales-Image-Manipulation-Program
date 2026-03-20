package com.scales.Elements.impl.ColourPicker;

import com.scales.Elements.Element;
import com.scales.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.scales.Elements.impl.ColourPicker.ColourPickerBackground.*;

public class SaturationBrightnessBox extends Element {
    public SaturationBrightnessBox() {
        super(
                () -> Main.COLOUR_PICKER_BUTTON.x.getAsInt() - BOX_DIAMETER + BAR_SPACING,
                () -> ColourPickerBackground.BACKGROUND_Y + BAR_SPACING,
                () -> SATURATION_BRIGHTNESS_BOX_SIZE,
                () -> SATURATION_BRIGHTNESS_BOX_SIZE
        );
    }

    @Override
    public void draw(Graphics2D g) {
        if (!Main.COLOUR_PICKER_BUTTON.open) return;

        // loops a full square
        for (int x = 0; x < SATURATION_BRIGHTNESS_BOX_SIZE; x++) {
            for (int y = 0; y < SATURATION_BRIGHTNESS_BOX_SIZE; y++) {
                // gets a ratio of x/width, this value is the ratio of the current progress through the gradient
                float saturation = (float) x / SATURATION_BRIGHTNESS_BOX_SIZE;
                float brightness = (float) y / SATURATION_BRIGHTNESS_BOX_SIZE;

                g.setColor(Color.getHSBColor(HSB[0], 1-saturation, 1-brightness));
                // draw the pixel
                g.fillRect(x, y, 1, 1);
            }
        }

        g.setColor(Color.WHITE);
        g.drawRect(
                (int) (HSB[1] * SATURATION_BRIGHTNESS_BOX_SIZE) - 3,
                (int) (HSB[2] * SATURATION_BRIGHTNESS_BOX_SIZE) - 3,
                6,
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
        if (!Main.COLOUR_PICKER_BUTTON.open) return false;

        int mouseX = this.applyTransform(e.getX(), this.x.getAsInt());
        int mouseY = this.applyTransform(e.getY(), this.y.getAsInt());

        HSB[1] = Math.clamp((float) mouseX / SATURATION_BRIGHTNESS_BOX_SIZE, 0, 1);
        HSB[2] = Math.clamp((float) mouseY / SATURATION_BRIGHTNESS_BOX_SIZE, 0, 1);
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
