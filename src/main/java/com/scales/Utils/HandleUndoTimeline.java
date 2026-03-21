package com.scales.Utils;

import com.scales.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// todo: optimise + tie to each canvas
public class HandleUndoTimeline {
    public static final ArrayList<BufferedImage> CHANGES_BUFFER = new ArrayList<>();
    public static final ArrayList<BufferedImage> REDO_BUFFER = new ArrayList<>();

    public static void loadCanvas(BufferedImage image) {
        Main.CURRENT_CANVAS.CANVAS_IMAGE = image;
        Main.CURRENT_CANVAS.IMAGE_GRAPHICS = (Graphics2D) Main.CURRENT_CANVAS.CANVAS_IMAGE.getGraphics();
    }

    public static void bufferCanvas() {
        REDO_BUFFER.clear();

        BufferedImage imageClone = new BufferedImage(
                Main.CURRENT_CANVAS.CANVAS_IMAGE.getWidth(),
                Main.CURRENT_CANVAS.CANVAS_IMAGE.getHeight(),
                Main.CURRENT_CANVAS.CANVAS_IMAGE.getType()
        );
        imageClone.getGraphics().drawImage(Main.CURRENT_CANVAS.CANVAS_IMAGE, 0, 0, null);
        CHANGES_BUFFER.add(imageClone);
        imageClone.getGraphics().dispose();

        if (CHANGES_BUFFER.size() > 10) CHANGES_BUFFER.removeFirst();
    }

    public static void handleUndo() {
        if (CHANGES_BUFFER.isEmpty()) return;
        REDO_BUFFER.add(Main.CURRENT_CANVAS.CANVAS_IMAGE);
        loadCanvas(CHANGES_BUFFER.removeLast());
    }

    public static void handleRedo() {
        if (REDO_BUFFER.isEmpty()) return;
        CHANGES_BUFFER.add(Main.CURRENT_CANVAS.CANVAS_IMAGE);
        loadCanvas(REDO_BUFFER.removeLast());
    }
}
