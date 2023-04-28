package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian blur.
 * </p>
 * 
 * <p>
 * A Gaussian blur blurs an image by creating a kernel using a Gaussian formula
 * and applying it to a buffered image.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Tristan Kitto
 * @version 1.0
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Gaussian blur with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianBlur
     */
    GaussianBlur(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Gaussian blur with the default size.
     * </p>
     * 
     * <p>
     * By default, a Gaussian blur has radius 1.
     * </p>
     * 
     * @see GaussianBlur(int)
     */
    GaussianBlur() {
        this(1);
    }

    /**
     * <p>
     * Apply a Gaussian blur to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Gaussian blur is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Gaussian blur to.
     * @return The resulting (blurred) image.
     */
    public BufferedImage apply(BufferedImage input) {

        float sigma = radius / 3.0f;

        // Create the kernel
        int size = 2 * radius + 1;
        float[] array = new float[size * size];
        float sum = 0.0f;
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                float value = (float) ((1 / (2 * Math.PI * sigma * sigma))
                        * (Math.exp(-(x * x + y * y) / (2 * sigma * sigma))));
                array[(y + radius) * size + x + radius] = value;
                sum += value;
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] /= sum;
        }

        // Apply the kernel with border padding
        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius, input.getHeight() + 2 * radius,
                input.getType());
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
            }
        }

        Kernel kernel = new Kernel(size, size, array);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        convolveOp.filter(paddedInput, output);

        return output;
    }
}
