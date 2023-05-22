package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw an Line on an image in a selected region.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Joshua Bodeker
 * @version 1.0
 */
public class DrawLine implements ImageOperation, java.io.Serializable {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    /**
     * <p>
     * Create a new draw Line operation.
     * </p>
     * 
     * @param startX Starting pixel for the crop on the x axis
     * @param startY Starting pixel for the crop on the y axis
     * @param endX   Ending pixel for the crop on the x axis
     * @param endY   Ending pixel for the crop on the y axis
     */
    DrawLine(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * <p>
     * Draw a Line on an image.
     * </p>
     * 
     * <p>
     * The draw Line operation takes a starting point and ending point on an
     * image and draws a Line on the image corresponding to that start and end
     * point.
     * </p>
     * 
     * @param input The image to draw on.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2d = input.createGraphics();
        g2d.drawLine(startX, startY, endX, endY);
        return input;
    }
}