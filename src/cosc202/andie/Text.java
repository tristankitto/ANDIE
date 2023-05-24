package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>
 * ImageOperation to add text.
 * </p>
 * 
 * <p>
 * Add a text box to the image.
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
public class Text implements ImageOperation, java.io.Serializable {

    /**
     * The coordinates of the text
     */
    private int startX;
    private int startY;
    private Color colour;
    private String font;
    private int fontSize;
    private String userText;

    /**
     * <p>
     * Create a new add text operation
     * </p>
     * 
     * @param startX   Starting pixel for the text box on the x axis
     * @param startY   Starting pixel for the text box on the y axis
     * @param endX     Ending pixel for the text box on the x axis
     * @param endY     Ending pixel for the text box on the y axis
     * @param colour   The colour of the text
     * @param font     The font of the text
     * @param fontSize The font size of the text
     */
    Text(int startX, int startY, Color colour, String font, int fontSize, String userText) {
        this.startX = startX;
        this.startY = startY;
        this.colour = colour;
        this.font = font;
        this.fontSize = fontSize;
        this.userText = userText;
    }

    public BufferedImage apply(BufferedImage input) {
        Font fontFull = new Font(font, 0, fontSize);

        Graphics2D g = input.createGraphics();
        g.setColor(colour);
        g.setFont(fontFull);

        FontMetrics fontM = g.getFontMetrics();
        int textHeight = fontM.getHeight();

        g.drawString(userText, startX, startY + textHeight / 2);
        g.dispose();

        return input;
    }
}
