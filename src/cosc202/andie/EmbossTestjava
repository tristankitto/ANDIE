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

public class Emboss implements ImageOperation, java.io.Serializable {

    /**
     * @param input The image to apply the emboss and edge detection filter to.
     * @return The resulting image.
     */

    public BufferedImage apply(BufferedImage input) {

        BufferedImage embossFilter1 = applyFilter(input, new float[] {
                0, 0, 0,
                1, 0, -1,
                0, 0, 0
        });

        BufferedImage embossFilter2 = applyFilter(input, new float[] {
                1, 0, 0,
                0, 0, 0,
                0, 0, -1
        });

        BufferedImage embossFilter3 = applyFilter(input, new float[] {
                0, 1, 0,
                0, 0, 0,
                0, -1, 0
        });

        BufferedImage embossFilter4 = applyFilter(input, new float[] {
                0, 0, 1,
                0, 0, 0,
                -1, 0, 0
        });

        BufferedImage embossFilter5 = applyFilter(input, new float[] {
                0, 0, 0,
                -1, 0, 1,
                0, 0, 0
        });

        BufferedImage embossFilter6 = applyFilter(input, new float[] {
                -1, 0, 0,
                0, 0, 0,
                0, 0, 1
        });

        BufferedImage embossFilter7 = applyFilter(input, new float[] {
                0, -1, 0,
                0, 0, 0,
                0, 1, 0
        });

        BufferedImage embossFilter8 = applyFilter(input, new float[] {
                0, 0, -1,
                0, 0, 0,
                1, 0, 0
        });

        // Apply Sobel filters
        BufferedImage sobelHorizontal = applyFilter(input, new float[] {
                -0.5f, 0, 0.5f,
                -1, 0, 1,
                -0.5f, 0, 0.5f
        });

        BufferedImage sobelVertical = applyFilter(input, new float[] {
                -0.5f, -1, -0.5f,
                0, 0, 0,
                0.5f, 1, 0.5f
        });
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        return output;
    }

    public static BufferedImage applyFilter(BufferedImage input, float[] kernelData) {

        Kernel kernel = new Kernel(3, 3, kernelData);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = convolveOp.filter(input, null);
        return output;
    }
}
