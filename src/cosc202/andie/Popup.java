package cosc202.andie;

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

}
