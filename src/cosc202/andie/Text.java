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

    /**
     * <p>
     * Add a text box to the image.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    Text(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
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
    //     Font font = new Font("Arial", Font.BOLD, 36);
    //     Graphics g = input.getGraphics();
    //     g.setFont(font);
    //     g.setColor(Color.BLACK);
    //     g.drawString("Test", 10, 10);
    //     return input;
    // }

    public BufferedImage apply(BufferedImage input) {
        // JTextField textField = new JTextField("This is a test. Please remain calm and head to the nearest exit.");
        // JPanel panel = new JPanel();
        // panel.setLayout(null);
        // panel.setBounds(startX, startY, endX, endY);
        // panel.add(textField);
        // Andie.frame.add(textField);
        // Andie.frame.setVisible(true);

        // All fonts
        //String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        // Some fonts
        // Move this stuff to EditActions, use Filter Actions as template

        String[] fonts = {"Comic Sans", "Impact", "Times New Roman"};
        Font font = new Font ("Arial", Font.BOLD, 24);
        JComboBox fontSelection = new JComboBox(fonts);
        fontSelection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Font font = new Font((String) fontSelection.getItemAt(fontSelection.getSelectedIndex()), Font.BOLD, 24);
            }
        }
        );
        

        // Graphics g = input.getGraphics();
        // g.setFont(font);
        // g.setColor(Color.BLACK);
        // g.drawString("Test", startX, startY);

        // return input;
        String userText = JOptionPane.showInputDialog("Enter text:");
        if (userText == null || userText.isEmpty()) {
            return input;
        }

        Graphics2D g = input.createGraphics();
        g.setColor(Color.BLACK);
        g.setFont(fontSelection.getFont());

        FontMetrics fontM = g.getFontMetrics();
        int textHeight = fontM.getHeight();

        g.drawString(userText, startX, startY + textHeight);
        g.dispose();

        return input;
    }
}
