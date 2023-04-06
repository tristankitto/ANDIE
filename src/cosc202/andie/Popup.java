package cosc202.andie;

import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

/**
 * <p>
 * Class for generating pop-up boxes to display to the user.
 * </p>
 * 
 * <p>
 * This class has methods that are used as templates for pop-up boxes to be
 * displayed to the user for uses such as error handling or getting an input from
 * the user.
 * </p>
 * 
 * <p>
 * exit
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Tristan Kitto
 * @version 1.0
 */
public class Popup {

    /**
     * <p>
     * Method to display a pop-up box with an error message when an exception in
     * the program occurs.
     * </p>
     * 
     * @param e         Exception thrown by try/catch method. Used to display error
     *                  in the terminal.
     * @param errorType The type of error to be displayed when the exception occurs
     */
    public static void errorMessage(Exception e, String errorType) {
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");
        System.out.println("Error message: " + e);
        Object[] options = { bundle.getString("ok") };
        int n = JOptionPane.showOptionDialog(null,
                bundle.getString(errorType + "Message"),
                bundle.getString(errorType), JOptionPane.OK_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        if (n == 0) {
            return;
        }
    }

    /**
     * <p>
     * Method to display a pop-up box with an area for the user to
     * input a value to be used for an image operation.
     * </p>
     * 
     * @param value The current value of the model.
     * @param minimum The first number of the sequence.
     * @param maximum The last number of the sequence.
     * @param stepSize The difference between elements of the sequence.
     * @param inputType The type of input pop-up to be displayed, shown as the title of the pop-up.
     * @param inputMessage A message to display to the user with information inside the pop-up.
     * 
     * @return Returns the integer value entered by the user into the pop-up box.
     */
    public static int getInput(int value, int minimum, int maximum, int stepSize, String inputType,
            String inputMessage) {
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");
        SpinnerNumberModel numModel = new SpinnerNumberModel(value, minimum, maximum, stepSize);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JSpinner spinner = new JSpinner(numModel);
        panel.add(new JLabel(bundle.getString(inputMessage) + " " + value));
        panel.add(spinner);
        Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };
        int radius = value;
        int option = JOptionPane.showOptionDialog(null,
                panel, bundle.getString(inputType),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        // Check the return value from the dialog box.
        if (option == 1) {
            return -1000;
        } else if (option == 0) {
            radius = numModel.getNumber().intValue();
        }
        return radius;
    }

}
