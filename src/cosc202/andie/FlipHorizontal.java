package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * ImageOperation to flip an image.
*/
public class FlipHorizontal implements ImageOperation {
    
    /**
     * Create a new Flip operation.
     */
    FlipHorizontal(){

    }

    /**
     * Apply flip to an image.
     * 
     * @param input The image to be flipped
     * @return The resulting flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        
        BufferedImage output = new BufferedImage(height, width, input.getType());
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = input.getRGB(x, y);
                output.setRGB(width - x, y, argb);
            }
        }

        
        return output;
    }
    
}
