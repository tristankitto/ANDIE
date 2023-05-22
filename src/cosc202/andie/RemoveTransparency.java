package cosc202.andie;

import java.awt.image.BufferedImage;

public class RemoveTransparency implements ImageOperation {

    RemoveTransparency() {

    }

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        // Create a new BufferedImage with opaque type
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Iterate over each pixel and replace transparent pixels with white
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = input.getRGB(x, y);
                int alpha = (argb >> 24) & 0xFF;

                // If the pixel is transparent, replace with white
                if (alpha == 0) {
                    argb = 0xFFFFFFFF; // White color
                }

                result.setRGB(x, y, argb);
            }
        }

        return result;
    }
}
