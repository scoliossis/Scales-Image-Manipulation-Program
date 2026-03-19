package com.scales.Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyListener extends KeyAdapter {
    private static final HashMap<Integer, Boolean> KEYS_PRESSED = new HashMap<>();
    public static boolean isKeyDown(int keyCode) {
        return KEYS_PRESSED.containsKey(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KEYS_PRESSED.put(e.getKeyCode(), true);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        KEYS_PRESSED.remove(e.getKeyCode());
    }
}
