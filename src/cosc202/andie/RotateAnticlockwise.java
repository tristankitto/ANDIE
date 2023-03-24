package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * ImageOperation to rotate an image by 90 degrees anticlockwise.
*/
public class RotateAnticlockwise implements ImageOperation {
    
    /**
     * Create a new RotateAnticlockwise operation.
     */
    RotateAnticlockwise(){

    }

    /**
     * Apply anticlockwise rotation to an image.
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