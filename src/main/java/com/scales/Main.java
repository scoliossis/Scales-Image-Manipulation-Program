package com.scales;

import com.scales.Listeners.MouseListener;
import com.scales.Listeners.MouseMotionListener;
import com.scales.UiElements.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Main {
    public static final JFrame FRAME = new JFrame();
    public static Graphics2D FRAME_GRAPHICS;

    public static void main(String[] args) {
        initialiseGUI();
        drawLoop();
    }

    private static void initialiseGUI() {
        // frame size
        FRAME.setSize(950, 540);
        // centres frame on screen
        FRAME.setLocationRelativeTo(null);
        // closes the program when the window is closed
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // makes the frame visible
        FRAME.setVisible(true);
        // create a frame buffer, which will be drawn to as opposed to drawing directly to the screen
        FRAME.createBufferStrategy(3);

        FRAME.addMouseMotionListener(new MouseMotionListener());
        FRAME.addMouseListener(new MouseListener());
    }

    // constant value for time between frames being rendered
    private static final int FRAME_TIME_GAP = 5;
    // variable to save the time the last frame was drawn at
    private static long lastFrameMS = 0;

    private static void drawLoop() {
        BufferStrategy bufferStrategy = FRAME.getBufferStrategy();

        while (true) {
            // drawing the program with a frame cap makes the program run smoother than attempting to draw every frame
            if (System.currentTimeMillis() - lastFrameMS < FRAME_TIME_GAP) continue;

            // create graphics
            FRAME_GRAPHICS = (Graphics2D) bufferStrategy.getDrawGraphics();
            // reset screen
            FRAME_GRAPHICS.clearRect(0, 0, FRAME.getWidth(), FRAME.getHeight());

            // draw the canvas
            Canvas.draw(FRAME_GRAPHICS);

            // the drawing is finished, display the buffer
            bufferStrategy.show();
            // dispose of graphics from this buffer to free up its memory
            FRAME_GRAPHICS.dispose();

            // save time the frame is drawn at
            lastFrameMS = System.currentTimeMillis();
        }
    }
}