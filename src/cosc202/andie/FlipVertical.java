package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to apply a vertical flip to an image.
 * </p>
 * 
 * <p>
 * A vertical flip mirrors an image along a horizontal axis, effectively reversing the order of its rows.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class FlipVertical implements ImageOperation, java.io.Serializable {
    
    /**
     * <p>
     * Create a new FlipVertical operation.
     * </p>
     * 
     */
    FlipVertical(){

    }

    /**
     * <p>
     * Apply vertical flip to an image.
     * </p>
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
