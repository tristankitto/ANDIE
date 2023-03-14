package cosc202.andie;

import java.awt.image.*;

/**
 * ImageOperation to apply a sharpen filter.
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {
    SharpenFilter() {
        // Any construction code goes here
    }

    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as a 9-element array
        float[] array = { 0, -0.5f, 0,
                -0.5f, 3, -0.5f,
                0, -0.5f, 0 };
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter and SoftBlur
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        return output;
    }
}