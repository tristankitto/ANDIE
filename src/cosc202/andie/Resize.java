package cosc202.andie;

import java.awt.image.BufferedImage;

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
        int newWidth = ((width * percentage) / 100);
        int newHeight = ((height * percentage) / 100);

        BufferedImage output = new BufferedImage(newHeight, newWidth, input.getType());

        return output;
    }
}
