package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.Color;

/**
 * <p>
 * ImageOperation to apply a Median (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Median filter blurs an image by replacing each pixel by the median of the
 * pixels in a surrounding neighbourhood, and can be implemented by a _____?.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Shayna Ludwig
 * @version 1.0
 */
public class BrightnessContrast implements ImageOperation, java.io.Serializable {

    /**
     * The size of adjustment. Each value is a percentage required for each colour adjustment. 
     */
    private int brightness;
    private int contrast;

    /**
     * <p>
     * Construct a brightness and contrast colour adjustment with 
     * given percentage
     * </p>
     * 
     * <p>
     * The percentage for the adjustment is the required can be negative of positive.
     *An input of 25 is a 25% increase in the brightness or contrast
     * An input of -25 is a -25% decrease in the brightness of contrast
     * </p>
     * 
     * @param brightness The percentage to adjust brightness by
     * @param contrast The percentage to adjust contrast by
     */
    BrightnessContrast(int brightness, int contrast) {
        this.brightness = brightness;
        this.contrast = contrast;
    }

    /**
     * <p>
     * Construct a Brightness and Contrast adjustment with the default size
     * </p
     * >
     * <p>
     * By default, both brightness and contrast have a default percentage of 0.
     * </p>
     * 
     * @see BrightnessContrast(int, int)
     */
    BrightnessContrast() {
        this.brightness = 0;
        this.contrast = 0;
    }

    /**
     * <p>
     * Apply a brightness and contrast adjustment to an image.
     * </p>
     * 
     * <p>
     * Brightness and contrast adjustment to each pixel within the range [0,255]
     * </p>
     * 
     * @param input The image to apply the brightness and contrast filter to.
     * @return The resulting adjusted image.
     */
    public BufferedImage apply(BufferedImage input) {
 
        
        int a;
        int r;
        int g; 
        int b;

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        // Iterate through each pixel
        
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                            
                            int argb = input.getRGB(x, y);
                            a = (argb >> 24) & 0xff;
                            r = (argb >> 16) & 0xff;
                            g = (argb >> 8) & 0xff;
                            b = argb & 0xff;

                            /**if(a > 0 && a < 255){
                                a = (int) ((1 + ((contrast/100)))*(a - 127.5) + (127.5 * (1 + (brightness/100))));
                            }*/
                            if(r > 0 && r < 255){
                                r = (int) ((1 + ((contrast/100)))*(r - 127.5) + (127.5 * (1 + (brightness/100))));
                            }
                            if(g > 0 && g < 255){
                                g = (int) ((1 + ((contrast/100)))*(g - 127.5) + (127.5 * (1 + (brightness/100))));
                            }
                            if(b > 0 || b < 255){
                                b = (int) ((1 + ((contrast/100)))*(b - 127.5) + (127.5 * (1 + (brightness/100))));
                            }
                    
                            
                            output.setRGB(x, y, new Color(r,g,b,a).getRGB());

            }
        }
        return output;
    }

}
