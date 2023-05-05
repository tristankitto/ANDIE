package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * <p>
 * ImageOperations for optionally applying an offset to account for both
 * positive and negative numbers
 * in the result of a convolution.
 * </p>
 * 
 * <p>
 * Changes are made by editing the RGB value
 * of each pixel of an image so that it accounts for negative values.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Ada Mazengarb
 * @version 1.0
 */

public class NegativeFilter {
    public static BufferedImage apply(BufferedImage input, Kernel kernel, int offset) {
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = convolveOp.filter(input, null);

        int midValue = 128;

        // Shift the output so that zero becomes the mid-value
        for (int x = 0; x < output.getWidth(); x++) {
            for (int y = 0; y < output.getHeight(); y++) {
                int rgb = output.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // adds the mid value
                r += midValue;
                g += midValue;
                b += midValue;

                // Add the offset to the shifted result (if we want user to input an offset)
                r += offset;
                g += offset;
                b += offset;

                // Clip the result to [0, 255]
                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));

                output.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }

        return output;
    }
}
