package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw on an image by dragging the mouse.
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
public class FreeDraw implements ImageOperation, java.io.Serializable {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Color colour;
    private BasicStroke strokeSize;

    /**
     * <p>
     * Create a new free draw operation.
     * </p>
     * 
     * @param startX     Starting pixel for the crop on the x axis
     * @param startY     Starting pixel for the crop on the y axis
     * @param endX       Ending pixel for the crop on the x axis
     * @param endY       Ending pixel for the crop on the y axis
     * @param colour     Colour of the brush stroke
     * @param strokeSize Width of the brush stroke
     */
    FreeDraw(int startX, int startY, int endX, int endY, Color colour, BasicStroke strokeSize) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.colour = colour;
        this.strokeSize = strokeSize;
    }

    /**
     * <p>
     * Draw on an image.
     * </p>
     * 
     * <p>
     * The free draw operation takes a starting point and ending point on an
     * image and draws a line on the image corresponding to that start and end
     * point. This is continuously done to form a freely drawn line.
     * </p>
     * 
     * @param input The image to draw on.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage input) {

        Graphics2D g2d = input.createGraphics();
        g2d.setColor(colour);
        g2d.setStroke(strokeSize);

        g2d.drawLine(startX, startY, endX, endY);

        g2d.dispose();
        return input;
    }
}
