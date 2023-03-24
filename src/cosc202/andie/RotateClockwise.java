package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * ImageOperation to rotate an image by 90 degrees clockwise.
*/
public class RotateClockwise implements ImageOperation {
    
    /**
     * Create a new RotateClockwise operation.
     */
    RotateClockwise(){

    }

    /**
     * Apply clockwise rotation to an image.
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