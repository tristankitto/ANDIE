package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to invert an images colours.
 * </p>
 * 
 * <p>
 * The images produced by this operation have each of their pixel colours inverted,
 * so each colour from RGB range 0-255 has a new colour of 255-RGB, where RGB is the original colour.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Tristan Kitto
 * @version 1.0
 */
public class InvertColour implements ImageOperation, java.io.Serializable {
    
    /**
     * <p>
     * Create a new InvertColour operation.
     * </p>
     */
    InvertColour(){

    }

    /**
     * <p>
     * Apply colour inversion to an image.
     * </p>
     * 
     * @param input The image to have its colour inverted
     * @return The resulting inverted image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        
        BufferedImage output = new BufferedImage(width, height, input.getType());
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = input.getRGB(x, y);
                
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = (argb) & 0xFF;
                
                Color colour = new Color(255 - r, 255 - g, 255 - b, a);
                int invertedArgb = colour.getRGB();
                
                output.setRGB(x, y, invertedArgb);
            }
        }
        
        return output;
    }
    
}
