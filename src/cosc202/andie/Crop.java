package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crop implements ImageOperation, java.io.Serializable {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    Crop(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public BufferedImage apply(BufferedImage input) {
        int croppedWidth = Math.abs(endX - startX);
        int croppedHeight = Math.abs(endY - startY);
        startX = Math.min(startX, endX);
        startY = Math.min(startY, endY);
        Image croppedImage = input.getSubimage(startX, startY, croppedWidth, croppedHeight);
        BufferedImage output = new BufferedImage(croppedWidth, croppedHeight, input.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(croppedImage, 0, 0, null);
        g2d.dispose();
        return output;
    }
}
