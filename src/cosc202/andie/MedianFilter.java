package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.Color;

/**
 * <p>
 * ImageOperation to apply a Median filter.
 * </p>
 * 
 * <p>
 * A Median filter blurs an image by replacing each pixel by the median of the
 * pixels in a surrounding neighbourhood.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Matthew Yi
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the area considered.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p>
     * 
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter(int)
     */
    MedianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred) image.
     */
    public BufferedImage apply(BufferedImage input) {
        // Set up arrays to store nearby RGB values

        int neighboursConsidered = (int) Math.pow(2 * radius + 1, 2);
        int[] nearbyA = new int[neighboursConsidered];
        int[] nearbyR = new int[neighboursConsidered];
        int[] nearbyG = new int[neighboursConsidered];
        int[] nearbyB = new int[neighboursConsidered];

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        // Iterate through each pixel
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                // Declare and initialise counter for filling arrays
                int i = 0;
                //
                for (int y1 = y - radius; y1 <= y + radius; y1++) {
                    for (int x1 = x - radius; x1 <= x + radius; x1++) {
                        
                        //ensure x1 and y1 are pixels actually contained within the image
                        if (x1 >= 0 && x1 < input.getWidth() && y1 >= 0 && y1 < input.getHeight()) {
                            int argb = input.getRGB(x1, y1);
                            nearbyA[i] = (argb >> 24) & 0xff;
                            nearbyR[i] = (argb >> 16) & 0xff;
                            nearbyG[i] = (argb >> 8) & 0xff;
                            nearbyB[i] = argb & 0xff;
                            i++;
                        }
                    }
                }
                Arrays.sort(nearbyA);
                Arrays.sort(nearbyR);
                Arrays.sort(nearbyG);
                Arrays.sort(nearbyB);

                output.setRGB(x, y, new Color(nearbyR[(int) i / 2], nearbyG[(int) i / 2], nearbyB[(int) i / 2],
                        nearbyA[(int) i / 2]).getRGB());
            }
        }
        return output;
    }

}
