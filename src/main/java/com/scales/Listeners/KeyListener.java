package com.scales.Listeners;

import com.scales.Utils.HandleUndoTimeline;
import com.scales.Utils.ImageFileUtil;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

public class KeyListener extends KeyAdapter {
    private static final HashMap<Integer, Boolean> KEYS_PRESSED = new HashMap<>();
    public static boolean isKeyDown(int keyCode) {
        return KEYS_PRESSED.containsKey(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KEYS_PRESSED.put(e.getKeyCode(), true);

        if (isKeyDown(KeyEvent.VK_CONTROL)) {
            try {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Z -> HandleUndoTimeline.handleUndo();
                    case KeyEvent.VK_Y -> HandleUndoTimeline.handleRedo();
                    case KeyEvent.VK_S -> ImageFileUtil.handleSaveKeyPress();
                    case KeyEvent.VK_V -> ImageFileUtil.handlePaste();
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        KEYS_PRESSED.remove(e.getKeyCode());
    }
}
