package com.scales.Listeners;

import com.scales.Main;

import javax.imageio.ImageIO;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class FileDropListener extends DropTarget {
    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            // accept drop and get the files dropped onto the frame
            dtde.acceptDrop(DnDConstants.ACTION_COPY);
            List<File> droppedFiles = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

            // read the image
            BufferedImage bufferedImage = ImageIO.read(droppedFiles.getFirst());
            // if the parsed file isnt an image, then just ignore it
            if (bufferedImage != null) Main.CANVAS.setCanvasImage(bufferedImage);

            // success!
            dtde.dropComplete(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}