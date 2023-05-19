package cosc202.andie;

import java.awt.image.BufferedImage;
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
 * used to apply the emboss filters to an image.
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

public class EmbossClasses {
        /**
         * <p>
         * Construct a Soft blur filter.
         * </p>
         * 
         */
        EmbossClasses() {

        }

        /**
         * <p>
         * Apply an Emboss filter to an image.
         * </p>
         * 
         * @param input The image to apply the Soft blur filter to.
         * @return The resulting Embossed image.
         */

        public class Emboss1 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

        public class Emboss2 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

        public class Emboss3 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

        public class Emboss4 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

        public class Emboss5 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

        public class Emboss6 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

        public class Emboss7 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

        public class Emboss8 implements ImageOperation, java.io.Serializable {
                public BufferedImage apply(BufferedImage input) {

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

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

                        BufferedImage test = NegativeFilter.apply(paddedInput, kernel, 0);
                        return test;
                }
        }

}
