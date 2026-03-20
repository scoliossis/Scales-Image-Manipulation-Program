package com.scales;

import com.scales.Cursors.Cursor;
import com.scales.Cursors.impl.Pencil;
import com.scales.Cursors.impl.Rubber;
import com.scales.Elements.Element;
import com.scales.Elements.impl.Canvas;
import com.scales.Elements.impl.ColourPicker.*;
import com.scales.Elements.impl.ResizeCanvasButton;
import com.scales.Elements.impl.Toolbar;
import com.scales.Listeners.KeyListener;
import com.scales.Listeners.MouseListener;
import com.scales.Listeners.MouseMotionListener;
import com.scales.Listeners.MouseWheelListener;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.List;

public class Main {
    public static final Rectangle DEFAULT_FRAME_SIZE = new Rectangle(950, 540);

    public static final JFrame FRAME = new JFrame();
    public static Graphics2D FRAME_GRAPHICS;

    public static final Canvas CANVAS = new Canvas();
    public static final ResizeCanvasButton RESIZE_CANVAS_BUTTON = new ResizeCanvasButton();
    public static final Toolbar TOOLBAR = new Toolbar();
    public static final ColourPickerButton COLOUR_PICKER_BUTTON = new ColourPickerButton();
    public static final ColourPickerBackground COLOUR_PICKER_BACKGROUND = new ColourPickerBackground();
    public static final SaturationBrightnessBox COLOUR_PICKER_SATURATION_BRIGHTNESS = new SaturationBrightnessBox();
    public static final HueSlider COLOUR_PICKER_HUE_SLIDER = new HueSlider();
    public static final OpacitySlider COLOUR_PICKER_OPACITY_SLIDER = new OpacitySlider();

    public static final List<Element> ELEMENTS = List.of(
            CANVAS,
            RESIZE_CANVAS_BUTTON,
            TOOLBAR,
            COLOUR_PICKER_BUTTON,
            COLOUR_PICKER_BACKGROUND,
            COLOUR_PICKER_SATURATION_BRIGHTNESS,
            COLOUR_PICKER_HUE_SLIDER,
            COLOUR_PICKER_OPACITY_SLIDER
    );

    public static final Pencil PENCIL = new Pencil();
    public static final Rubber RUBBER = new Rubber();
    public static Cursor currentCursor = PENCIL;

    public static final List<Cursor> CURSORS = List.of(
            PENCIL,
            RUBBER
    );

    public static void main(String[] args) {
        initialiseGUI();
        drawLoop();
    }

    private static void initialiseGUI() {
        // frame size
        FRAME.setSize(DEFAULT_FRAME_SIZE.width, DEFAULT_FRAME_SIZE.height);
        // centres frame on screen
        FRAME.setLocationRelativeTo(null);
        // closes the program when the window is closed
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // todo: remove
        FRAME.setUndecorated(true);

        // makes the frame visible
        FRAME.setVisible(true);
        // create a frame buffer, which will be drawn to as opposed to drawing directly to the screen
        FRAME.createBufferStrategy(3);

        FRAME.addMouseMotionListener(new MouseMotionListener());
        FRAME.addMouseListener(new MouseListener());
        FRAME.addMouseWheelListener(new MouseWheelListener());
        FRAME.addKeyListener(new KeyListener());
    }

    // constant value for time between frames being rendered
    private static final int FRAME_TIME_GAP = 5;
    // variable to save the time the last frame was drawn at
    private static long lastFrameMS = 0;

    @Setter private static boolean running = true;

    private static void drawLoop() {
        BufferStrategy bufferStrategy = FRAME.getBufferStrategy();

        while (running) {
            // drawing the program with a frame cap makes the program run smoother than attempting to draw every frame
            if (System.currentTimeMillis() - lastFrameMS < FRAME_TIME_GAP) continue;

            // create graphics
            FRAME_GRAPHICS = (Graphics2D) bufferStrategy.getDrawGraphics();
            // reset screen
            FRAME_GRAPHICS.clearRect(0, 0, FRAME.getWidth(), FRAME.getHeight());

            // draw each element
            ELEMENTS.forEach(e -> {
                AffineTransform currentTransform = FRAME_GRAPHICS.getTransform();
                FRAME_GRAPHICS.translate(e.x.getAsInt(), e.y.getAsInt());
                FRAME_GRAPHICS.scale(e.scale, e.scale);

                e.draw(FRAME_GRAPHICS);

                FRAME_GRAPHICS.setTransform(currentTransform);
            });

            // the drawing is finished, display the buffer
            bufferStrategy.show();
            // dispose of graphics from this buffer to free up its memory
            FRAME_GRAPHICS.dispose();

            // save time the frame is drawn at
            lastFrameMS = System.currentTimeMillis();
        }
    }
}