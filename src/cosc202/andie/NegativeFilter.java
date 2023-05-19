package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;
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
    public static BufferedImage apply(BufferedImage input, Kernel kernel) {

        int midValue = 128;
        BufferedImage filteredImage = new BufferedImage(input.getWidth(), input.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        float[] kernelData = kernel.getKernelData(null);

        // Shift the output so that zero becomes the mid-value
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                int rgba = input.getRGB(x, y);
                int r = (rgba >> 16) & 0xFF;
                int g = (rgba >> 8) & 0xFF;
                int b = rgba & 0xFF;
                int a = (rgba >> 24) & 0xFF;

                float sumR = 0, sumG = 0, sumB = 0;
                int kernelSize = kernelData.length;
                int halfKernelSize = kernelSize / 2;

                for (int kx = 0; kx < kernelSize; kx++) {
                    int pixelX = x + kx - halfKernelSize;
                    if (pixelX >= 0 && pixelX < input.getWidth()) {
                        Color pixelColor = new Color(input.getRGB(pixelX, y));
                        float kernelValue = kernelData[kx];
                        sumR += kernelValue * pixelColor.getRed();
                        sumG += kernelValue * pixelColor.getGreen();
                        sumB += kernelValue * pixelColor.getBlue();
                    }
                }

                // adds the mid value
                sumR += midValue;
                sumG += midValue;
                sumB += midValue;

                // Update the pixel value in the filtered image
                r = Math.min(Math.max((int) sumR, 0), 255);
                g = Math.min(Math.max((int) sumG, 0), 255);
                b = Math.min(Math.max((int) sumB, 0), 255);
                rgba = new Color(r, g, b, a).getRGB();
                filteredImage.setRGB(x, y, rgba);
            }
        }

        return filteredImage;
    }
}
