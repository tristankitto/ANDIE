package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;

/**
 * <p>
 * ImageOperation to apply the Emboss and sobel filters.
 * </p>
 * 
 * <p>
 * Emboss filters work by using kernels dependant on the direction of embossing.
 * sobel filters are another type of edge detection filter which have kernels
 * for vertical
 * and horizontal edge detection.
 * There are 10 deifferent kernels in this class that are used to apply these
 * filters
 * to an image, each kernel relating to a different emboss/ sobel filter.
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

public class EmbossClasses {
        /**
         * <p>
         * Construct the EmbossClasses filter.
         * </p>
         * 
         */
        EmbossClasses() {

        }

        /**
         * <p>
         * Apply a west emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the west emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss1 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { 0, 0, 0,
                                        1, 0, -1,
                                        0, 0, 0 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a north west emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the north west emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss2 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { 1, 0, 0,
                                        0, 0, 0,
                                        0, 0, -1 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a north emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the north emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss3 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { 0, 1, 0,
                                        0, 0, 0,
                                        0, -1, 0 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a north east emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the north east emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss4 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { 0, 0, 1,
                                        0, 0, 0,
                                        -1, 0, 0 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply an east emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the east emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss5 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { 0, 0, 0,
                                        -1, 0, 1,
                                        0, 0, 0 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a south east emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the south east emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss6 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { -1, 0, 0,
                                        0, 0, 0,
                                        0, 0, 1 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a south emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the south emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss7 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { 0, -1, 0,
                                        0, 0, 0,
                                        0, 1, 0 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a south west emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the south west emboss filter to.
         * @return The resulting Embossed image.
         */
        public class Emboss8 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {
                        Andie.imagePanel.getImage().tempApply(new ConvertToGrey());

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { 0, 0, -1,
                                        0, 0, 0,
                                        1, 0, 0 };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a horizontal sobel filter to an image.
         * </p>
         * 
         * @param input The image to apply the horizontal sobel filter to.
         * @return The resulting filtered image.
         */
        public class SobelHorizontal implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { -0.5f, 0, 0.5f,
                                        -1, 0, 1,
                                        -0.5f, 0, 0.5f };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

        /**
         * <p>
         * Apply a vertical sobel filter to an image.
         * </p>
         * 
         * @param input The image to apply the vertical sobel filter to.
         * @return The resulting filtered image.
         */
        public class SobelVertical implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

                        int radius = 1;

                        // The values for the kernel as a 9-element array
                        float[] array = { -0.5f, -1, -0.5f,
                                        0, 0, 0,
                                        0.5f, 1, 0.5f };
                        // Make a 3x3 filter from the array
                        Kernel kernel = new Kernel(3, 3, array);

                        // Apply the kernel with border padding
                        BufferedImage paddedInput = new BufferedImage(input.getWidth() + 2 * radius,
                                        input.getHeight() + 2 * radius,
                                        input.getType());
                        for (int y = 0; y < input.getHeight(); y++) {
                                for (int x = 0; x < input.getWidth(); x++) {
                                        paddedInput.setRGB(x + radius, y + radius, input.getRGB(x, y));
                                }
                        }

                        BufferedImage output = NegativeFilter.apply(paddedInput, kernel);
                        return output;
                }
        }

}
