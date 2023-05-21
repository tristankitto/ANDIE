package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a rectangle on an image in a selected region.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Joshua Bodeker and Tristan Kitto
 * @version 1.0
 */
public class DrawShapes implements ImageOperation, java.io.Serializable {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Color colour;
    private String shape;
    private BasicStroke strokeSize;

    /**
     * <p>
     * Create a new draw rectangle operation.
     * </p>
     * 
     * @param startX Starting pixel for the crop on the x axis
     * @param startY Starting pixel for the crop on the y axis
     * @param endX   Ending pixel for the crop on the x axis
     * @param endY   Ending pixel for the crop on the y axis
     */
    DrawShapes(int startX, int startY, int endX, int endY, String shape, Color colour, BasicStroke strokeSize) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.shape = shape;
        this.colour = colour;
        this.strokeSize = strokeSize;
    }

    /**
     * <p>
     * Draw a rectangle on an image.
     * </p>
     * 
     * <p>
     * The draw rectangle operation takes a starting point and ending point on an
     * image and draws a rectangle on the image corresponding to that start and end
     * point.
     * </p>
     * 
     * @param input The image to draw on.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage input) {

        Graphics2D g2d = input.createGraphics();
        g2d.setColor(colour);
        g2d.setStroke(strokeSize);
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int startXOriginal = startX;
        int startYOriginal = startY;
        startX = Math.min(startX, endX);
        startY = Math.min(startY, endY);

        switch (shape) {
            case "filledRectangle":
                g2d.fillRect(startX, startY, width, height);
                break;
            case "filledOval":
                g2d.fillOval(startX, startY, width, height);
                break;
            case "Line":
                g2d.drawLine(startXOriginal, startYOriginal, endX, endY);
                break;
            case "Rectangle":
                g2d.drawRect(startX, startY, width, height);
                break;
            case "Oval":
                g2d.drawOval(startX, startY, width, height);
                break;
        }

        g2d.dispose();

        startX = startXOriginal;
        startY = startYOriginal;
        return input;
    }
}
