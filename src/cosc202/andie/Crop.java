package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to crop an image to a selected region.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Tristan Kitto
 * @version 1.0
 */
public class Crop implements ImageOperation, java.io.Serializable {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    /**
     * <p>
     * Create a new crop operation.
     * </p>
     * 
     * @param startX Starting pixel for the crop on the x axis
     * @param startY Starting pixel for the crop on the y axis
     * @param endX   Ending pixel for the crop on the x axis
     * @param endY   Ending pixel for the crop on the y axis
     */
    Crop(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * <p>
     * Apply a crop to an image.
     * </p>
     * 
     * <p>
     * The crop takes a starting point and ending point on an image and returns a
     * subimage corresponding to that start and end point.
     * </p>
     * 
     * @param input The image to be cropped.
     * @return The resulting cropped subimage.
     */
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
