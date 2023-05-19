package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * <p>
 * ImageOperation to apply a Emboss and Edge Detection filters.
 * </p>
 * 
 * <p>
 * Emboss filters work by using kernels depndant on the direction of embossing.
 * They detect edges
 * using sobel filter which have kernels for vertical and horizontal edge
 * stection. these kernels are
 * used to apply the emboss filters to an inmage.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Ada Mazengarb
 * @version 1.0
 */

public class EmbossEdgeDetection implements ImageOperation, java.io.Serializable {

    /**
     * @param input The image to apply the emboss and edge detection filter to.
     * @return The resulting image.
     */

    public BufferedImage apply(BufferedImage input) {

        BufferedImage emboss1 = applyFilter(input, new Kernel(3, 3, new float[] { 0, 0, 0, 1, 0, -1, 0, 0, 0 }), 0);
        BufferedImage emboss2 = applyFilter(input, new Kernel(3, 3, new float[] { 1, 0, 0, 0, 0, 0, 0, 0, -1 }), 0);
        BufferedImage emboss3 = applyFilter(input, new Kernel(3, 3, new float[] { 0, 1, 0, 0, 0, 0, 0, -1, 0 }), 0);
        BufferedImage emboss4 = applyFilter(input, new Kernel(3, 3, new float[] { 0, 0, 1, 0, 0, 0, -1, 0, 0 }), 0);
        BufferedImage emboss5 = applyFilter(input, new Kernel(3, 3, new float[] { 0, 0, 0, -1, 0, 1, 0, 0, 0 }), 0);
        BufferedImage emboss6 = applyFilter(input, new Kernel(3, 3, new float[] { -1, 0, 0, 0, 0, 0, 0, 0, 1 }), 0);
        BufferedImage emboss7 = applyFilter(input, new Kernel(3, 3, new float[] { 0, -1, 0, 0, 0, 0, 0, 1, 0 }), 0);
        BufferedImage emboss8 = applyFilter(input, new Kernel(3, 3, new float[] { 0, 0, -1, 0, 0, 0, 1, 0, 0 }), 0);

        // Apply Sobel filters
        BufferedImage sobelHorizontal = applyFilter(input,
                new Kernel(3, 3, new float[] { -0.5f, 0, 0.5f, -1, 0, 1, -0.5f, 0, 0.5f }), 0);
        BufferedImage sobelVertical = applyFilter(input,
                new Kernel(3, 3, new float[] { -0.5f, -1, -0.5f, 0, 0, 0, 0.5f, 1, 0.5f }), 0);
        
        
        Kernel kernel= new Kernel(3, 3, kernelData);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);        
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        return output;
    }

    public static BufferedImage applyFilter(BufferedImage input, Kernel kernel, int offset) {
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
