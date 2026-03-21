package com.scales.Utils;

import com.scales.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageFileUtil {
    public static void loadDroppedImage(DropTargetDropEvent dtde) {
        try {
            // accept drop and get the files dropped onto the frame
            dtde.acceptDrop(DnDConstants.ACTION_COPY);
            // check that the dropped item is files
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List<File> droppedFiles = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                // read the image
                Image fileImage = ImageIO.read(droppedFiles.getFirst());
                // if the parsed file isn't an image, then just ignore it
                if (fileImage != null) Main.CURRENT_CANVAS.setCanvasImage(bufferImage(fileImage));
            }

            // success!
            dtde.dropComplete(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleSaveKeyPress() throws IOException {
        // gets the folder which is currently running the program
        File outputFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();

        // default file name is appended to with a number if the file already exists
        String defaultOutputName = "Image";
        String outputName = defaultOutputName;

        // increment file save name until its unique
        int outputIndex = 0;
        while (new File(outputFolder.getAbsolutePath() + "/" + outputName + ".png").exists()) {
            outputIndex++;
            outputName = defaultOutputName + outputIndex;
        }
        ImageIO.write(Main.CURRENT_CANVAS.CANVAS_IMAGE, "PNG", new File(outputFolder.getAbsolutePath() + "/"+outputName+".png"));
    }

    // https://stackoverflow.com/questions/7834768/setting-images-to-clipboard-java
    public static void handlePaste() throws IOException, UnsupportedFlavorException {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

        Image clipboardImage;

        // if the clipboard contains an image then get that image from the clipboard
        if (t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            clipboardImage = (Image) t.getTransferData(DataFlavor.imageFlavor);
        }
        else if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            // get the first file in the clipboard, parse it as an image
            clipboardImage = ImageIO.read(((List<File>) t.getTransferData(DataFlavor.javaFileListFlavor)).getFirst());
        }
        else return;

        if (clipboardImage == null) return;

        // update the canvas image
        Main.CURRENT_CANVAS.setCanvasImage(bufferImage(clipboardImage));
    }

    private static BufferedImage bufferImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bufferedImage;
    }
}
