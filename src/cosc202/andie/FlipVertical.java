package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * ImageOperation to vertically flip an image.
*/
public class FlipVertical implements ImageOperation {
    
    /**
     * Create a new FlipVertical operation.
     */
    FlipVertical(){

    }

    /**
     * Apply vertical flip to an image.
     * 
     * @param input The image to be flipped vertically
     * @return The resulting vertically flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        
        BufferedImage output = new BufferedImage(width, height, input.getType());
        
        for (int y = 0; y < height/2; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = input.getRGB(x, y);
                output.setRGB(x, y, input.getRGB(x, height - y- 1));
                output.setRGB(x, height - y - 1, argb);
            }
        } 

        if (height % 2 == 1) {
            for (int x = 0; x < width; ++x) {
                output.setRGB(x, height/2, input.getRGB(x, height/2));
            }
        }

        
        return output;
    }
    
}
