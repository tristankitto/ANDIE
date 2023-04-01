package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to rotate an image by 90 degrees anticlockwise.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Matthew Yi
 * @version 1.0
 */
public class RotateAnticlockwise implements ImageOperation {
    
    /**
     * <p>
     * Perform an anticlockwise rotation of 90 degrees.
     * </p>
     *
     */
    RotateAnticlockwise(){

    }

    /**
     * <p>
     * Apply anticlockwise rotation to an image.
     * </p>
     * 
     * @param input The image to be rotated anticlockwise
     * @return The resulting anticlockwise rotated image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        
        BufferedImage output = new BufferedImage(height, width, input.getType());
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = input.getRGB(x, y);
                output.setRGB(y, x, argb);
            }
        }

        for (int y = 0; y < output.getHeight()/2; ++y) {
            for (int x = 0; x < output.getWidth(); ++x) {
                int argb = output.getRGB(x, y);
                output.setRGB(x, y, output.getRGB(x, output.getHeight() - y - 1));
                output.setRGB(x, output.getHeight() - y - 1, argb);
            }
        } 

        return output;
    }
    
}