package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * ImageOperation to horizontally flip an image.
*/
public class FlipHorizontal implements ImageOperation {
    
    /**
     * Create a new FlipHorizontal operation.
     */
    FlipHorizontal(){

    }

    /**
     * Apply horizontal flip to an image.
     * 
     * @param input The image to be flipped horizontally
     * @return The resulting horizontally flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        
        BufferedImage output = new BufferedImage(width, height, input.getType());
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width/2; ++x) {
                int argb = input.getRGB(x, y);
                output.setRGB(x, y, input.getRGB(width - x - 1, y));
                output.setRGB(width - x - 1, y, argb);
            }
        } 

        if (width % 2 == 1) {
            for (int y = 0; y < height; ++y) {
                output.setRGB(y, width/2, input.getRGB(width/2, y));
            }
        }
        
        return output;
    }
    
}
