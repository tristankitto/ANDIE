package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to rotate an image by 180 degrees.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Matthew Yi
 * @version 1.0
 */
public class Rotate180 implements ImageOperation, java.io.Serializable {
    
    /**
     * <p>
     * Perform a rotation of 180 degrees.
     * </p>
     *
     */
    Rotate180(){

    }

    /**
     * <p>
     * Apply 180 degrees rotation to an image.
     * </p>
     * 
     * @param input The image to be rotated
     * @return The resulting rotated image.
     */
    public BufferedImage apply(BufferedImage input) {
        FlipHorizontal h = new FlipHorizontal();
        FlipVertical v = new FlipVertical();

        BufferedImage output = v.apply(h.apply(input));

        return output;
    }
}