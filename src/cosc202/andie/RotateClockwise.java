package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to rotate an image by 90 degrees clockwise.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Matthew Yi
 * @version 1.0
 */
public class RotateClockwise implements ImageOperation {
    
    /**
     * <p>
     * Perform a clockwise rotation of 90 degrees.
     * </p>
     *
     */
    RotateClockwise(){

    }

    /**
     * <p>
     * Apply clockwise rotation to an image.
     * </p>
     * 
     * @param input The image to be rotated clockwise
     * @return The resulting clockwise rotated image.
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

        for (int y = 0; y < output.getHeight(); ++y) {
            for (int x = 0; x < output.getWidth()/2; ++x) {
                int argb = output.getRGB(x, y);
                output.setRGB(x, y, output.getRGB(output.getWidth() - x - 1, y));
                output.setRGB(output.getWidth() - x - 1, y, argb);
            }
        } 

        return output;
    }
    
}