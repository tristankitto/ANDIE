package cosc202.andie;


import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * <p>
 * ImageOperation to resize an image by an amount specified by the user.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Ada Mazengarb
 * @version 1.0
 */
public class Resize implements ImageOperation, java.io.Serializable {

    /**
     * The percentage of the resize to apply.
     */
    private int percentage;

    /**
     * <p>
     * apply a resize with the given size.
     * </p>
     * 
     * @param percentage The percentage of the resize.
     */
    Resize(int percentage) {
        this.percentage = percentage;
    }

    /**
     * <p>
     * Resize an image.
     * </p>
     * 
     * @param input The image to be rotated
     * @return The resulting resized image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        int newWidth = (width * percentage) / 100;
        int newHeight = (height * percentage) / 100;

        Image scaledImage = input.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return output;
    }

}
