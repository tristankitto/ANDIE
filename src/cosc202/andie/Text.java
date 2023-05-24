package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private int endX;
    private int endY;
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
    Text(int startX, int startY, int endX, int endY, Color colour, String font, int fontSize, String userText) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.colour = colour;
        this.font = font;
        this.fontSize = fontSize;
        this.userText = userText;
    }

    /**
     * <p>
     * Add a text box to the image.
     * </p>
     * 
     * @param input The image to add the text box to.
     * @return The resulting (blurred) image.
     */
    // public BufferedImage apply(BufferedImage input) {
    // Font font = new Font("Arial", Font.BOLD, 36);
    // Graphics g = input.getGraphics();
    // g.setFont(font);
    // g.setColor(Color.BLACK);
    // g.drawString("Test", 10, 10);
    // return input;
    // }

    public BufferedImage apply(BufferedImage input) {
        // JTextField textField = new JTextField("This is a test. Please remain calm and
        // head to the nearest exit.");
        // JPanel panel = new JPanel();
        // panel.setLayout(null);
        // panel.setBounds(startX, startY, endX, endY);
        // panel.add(textField);
        // Andie.frame.add(textField);
        // Andie.frame.setVisible(true);

        // All fonts
        // String[] fonts =
        // GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        Font fontFull = new Font(font, 0, fontSize);

        // Graphics g = input.getGraphics();
        // g.setFont(fontFull);
        // g.setColor(Color.BLACK);
        // g.drawString("Test", startX, startY);

        // return input;

        Graphics2D g = input.createGraphics();
        g.setColor(colour);
        g.setFont(fontFull);

        FontMetrics fontM = g.getFontMetrics();
        int textHeight = fontM.getHeight();

        g.drawString(userText, startX, startY + textHeight);
        g.dispose();

        // Might be helpful with text wrapping in bounds
        // https://stackoverflow.com/questions/41111870/swing-drawstring-text-bounds-and-line-wrapping

        return input;
    }
}
