package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * ImageOperation to remove the background from an image.
 * Assumes a simple background and removes pixels close to the determined
 * background color.
 * The removed pixels will be made transparent.
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Tristan Kitto
 * @version 1.0
 */
public class RemoveBackground implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Create a new RemoveBackground operation.
     * </p>
     */
    RemoveBackground() {

    }

    /**
     * Apply background removal to an image.
     * 
     * @param input The image to remove the background from
     * @return The resulting image with the background removed
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Map<Integer, Integer> colorCount = new HashMap<>();

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = input.getRGB(x, y);

                if (!colorCount.containsKey(argb)) {
                    colorCount.put(argb, 1);
                } else {
                    int count = colorCount.get(argb);
                    colorCount.put(argb, count + 1);
                }
            }
        }

        int backgroundColor = 0;
        int maxCount = 0;

        for (Map.Entry<Integer, Integer> entry : colorCount.entrySet()) {
            int color = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount) {
                backgroundColor = color;
                maxCount = count;
            }
        }

        int threshold = 100;

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = input.getRGB(x, y);

                if (isSimilarColor(argb, backgroundColor, threshold)
                        && hasSimilarSurroundingPixels(input, x, y, backgroundColor, threshold)) {
                    argb &= 0x00FFFFFF;
                }

                output.setRGB(x, y, argb);
            }
        }

        return output;
    }

    private static boolean isSimilarColor(int color1, int color2, int threshold) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        int diffR = Math.abs(r1 - r2);
        int diffG = Math.abs(g1 - g2);
        int diffB = Math.abs(b1 - b2);

        return (diffR + diffG + diffB) <= threshold;
    }

    private static boolean hasSimilarSurroundingPixels(BufferedImage image, int x, int y, int backgroundColor,
            int threshold) {
        int startX = Math.max(0, x - 1);
        int startY = Math.max(0, y - 1);
        int endX = Math.min(image.getWidth() - 1, x + 1);
        int endY = Math.min(image.getHeight() - 1, y + 1);

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (i != x || j != y) {
                    int argb = image.getRGB(i, j);
                    if (!isSimilarColor(argb, backgroundColor, threshold)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
