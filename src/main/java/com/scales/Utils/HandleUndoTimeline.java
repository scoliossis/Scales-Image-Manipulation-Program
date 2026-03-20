package com.scales.Utils;

import com.scales.Elements.impl.Canvas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HandleUndoTimeline {
    public static final ArrayList<BufferedImage> CHANGES_BUFFER = new ArrayList<>();
    public static final ArrayList<BufferedImage> REDO_BUFFER = new ArrayList<>();

    public static void loadCanvas(BufferedImage image) {
        Canvas.CANVAS_IMAGE = image;
        Canvas.IMAGE_GRAPHICS = (Graphics2D) Canvas.CANVAS_IMAGE.getGraphics();
    }

    public static void bufferCanvas() {
        REDO_BUFFER.clear();

        BufferedImage imageClone = new BufferedImage(Canvas.CANVAS_IMAGE.getWidth(), Canvas.CANVAS_IMAGE.getHeight(), Canvas.CANVAS_IMAGE.getType());
        imageClone.getGraphics().drawImage(Canvas.CANVAS_IMAGE, 0, 0, null);
        CHANGES_BUFFER.add(imageClone);
        imageClone.getGraphics().dispose();

        if (CHANGES_BUFFER.size() > 10) CHANGES_BUFFER.removeFirst();
    }

    public static void handleCtrlKey(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z && !CHANGES_BUFFER.isEmpty()) {
            REDO_BUFFER.add(Canvas.CANVAS_IMAGE);
            loadCanvas(CHANGES_BUFFER.removeLast());
        }
        else if (e.getKeyCode() == KeyEvent.VK_Y && !REDO_BUFFER.isEmpty()) {
            CHANGES_BUFFER.add(Canvas.CANVAS_IMAGE);
            loadCanvas(REDO_BUFFER.removeLast());
        }
    }
}
