package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * ImageOperation to invert an image's colour.
*/
public class InvertColour implements ImageOperation, java.io.Serializable {
    
    /**
     * Create a new InvertColour operation.
     */
    InvertColour(){

    }

    /**
     * Apply colour inversion to an image.
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
