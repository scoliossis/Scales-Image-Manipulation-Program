package com.scales;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main {
    private static final JFrame FRAME = new JFrame("window title");

    public static void main(String[] args) {
        FRAME.setSize(1000, 1000);
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setVisible(true);
        FRAME.createBufferStrategy(2);

        drawLoop();
    }

    private static void drawLoop() {
        while (true) {
            BufferStrategy bufferStrategy = FRAME.getBufferStrategy();
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, FRAME.getWidth(), FRAME.getHeight());
            g.fillRect((int) System.currentTimeMillis() % (FRAME.getWidth()-100), (int) System.currentTimeMillis() % (FRAME.getHeight()-100), 100, 100);
            bufferStrategy.show();
        }
    }
}