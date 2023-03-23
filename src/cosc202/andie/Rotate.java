package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * ImageOperation to rotate an image by 90 degrees.
*/
public class Rotate implements ImageOperation {
    
    /**
     * Create a new Rotate operation.
     */
    Rotate(){

    }

    /**
     * Apply rotation to an image.
     * 
     * @param input The image to be rotated
     * @return The resulting rotated image.
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

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = output.getRGB(x, y);
                output.setRGB(height - y - 1, x, argb);
            }
        }
        
        return output;
    }
    
}
