package cosc202.andie;

import java.awt.image.*;

/**
 * ImageOperation to apply a Gaussian blur filter.
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {

    /**
     * The radius of the Gaussian function, in pixels.
     */
    private int radius;

    /**
     * Construct a Gaussian blur filter with the given radius.
     * 
     * @param radius The radius of the Gaussian function.
     */
    GaussianBlur(int radius) {
        this.radius = radius;
    }

    /**
     * Construct a Gaussian blur filter with default radius of 1.
     */
    GaussianBlur() {
        this(1);
    }

    /**
     * Apply a Gaussian blur filter to an image.
     * 
     * @param input The image to apply the filter to.
     * @return The resulting blurred image.
     */
    public BufferedImage apply(BufferedImage input) {

        float sigma = radius/3.0f;

        // Create the kernel
        int size = 2*radius+1;
        float[] array = new float[size * size];
        float sum = 0.0f;
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                float value = (float) ((1/(2*Math.PI*sigma*sigma))*(Math.exp(-(x * x + y * y) / (2 * sigma * sigma))));
                array[(y + radius) * size + x + radius] = value;
                sum += value;
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] /= sum;
        }

        // Apply the kernel
        Kernel kernel = new Kernel(size, size, array);
        ConvolveOp convolveOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convolveOp.filter(input, output);

        return output;
    }
}
