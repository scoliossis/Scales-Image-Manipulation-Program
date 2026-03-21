package com.scales.Listeners;

import com.scales.Utils.ImageFileUtil;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

public class FileDropListener extends DropTarget {
    @Override
    public void drop(DropTargetDropEvent dtde) {
        ImageFileUtil.loadDroppedImage(dtde);
    }
}