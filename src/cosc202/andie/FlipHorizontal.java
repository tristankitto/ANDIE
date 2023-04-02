package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to apply a horizontal flip to an image.
 * </p>
 * 
 * <p>
 * A horizontal flip mirrors an image along a vertical axis, effectively reversing the order of its columns.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Matthew Yi
 * @version 1.0
 */
public class FlipHorizontal implements ImageOperation, java.io.Serializable {
    
    /**
     * <p>
     * Create a new FlipHorizontal operation.
     * </p>
     * 
     */
    FlipHorizontal(){

    }

    /**
     * <p>
     * Apply horizontal flip to an image.
     * </p>
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
                output.setRGB(width/2, y, input.getRGB(width/2, y));
            }
        }
        
        return output;
    }
    
}
