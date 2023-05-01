package cosc202.andie;

import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

/**
 * <p>
 * Class for generating pop-up boxes to display to the user.
 * </p>
 * 
 * <p>
 * This class has methods that are used as templates for pop-up boxes to be
 * displayed to the user for uses such as error handling or getting an input
 * from
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
public class Tools {

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
     * Method to create an item for a JMenu and assign it a keyboard shortcut.
     * </p>
     * 
     * @param action The ImageAction to be made into a menu item.
     * @param shift  Boolean for if the shortcut should include the shift key
     * @param alt    Boolean for if the shortcut should include the alt key (option
     *               on MacOS)
     * @return The JMenuItem to be added to the JMenu.
     */
    public static JMenuItem createMenuItem(Action action, boolean shift, boolean alt) {
        JMenuItem item = new JMenuItem(action);

        int mnemonic = ((Integer) action.getValue(Action.MNEMONIC_KEY)).intValue();
        Integer ctrlKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        Integer shiftKey = KeyEvent.SHIFT_DOWN_MASK;
        Integer altKey = (UIManager.getLookAndFeel().getID().equals("MacOS") ? InputEvent.META_DOWN_MASK
                : InputEvent.ALT_DOWN_MASK);

        if (action.getValue(Action.MNEMONIC_KEY) != null) {
            if (shift && alt) {
                KeyStroke key = KeyStroke.getKeyStroke(mnemonic, ctrlKey | altKey | shiftKey);
                item.setAccelerator(key);
            } else if (shift) {
                KeyStroke key = KeyStroke.getKeyStroke(mnemonic, ctrlKey | shiftKey);
                item.setAccelerator(key);
            } else if (alt) {
                KeyStroke key = KeyStroke.getKeyStroke(mnemonic, ctrlKey | altKey);
                item.setAccelerator(key);
            } else {
                KeyStroke key = KeyStroke.getKeyStroke(mnemonic, ctrlKey);
                item.setAccelerator(key);
            }
        }
        return item;
    }

}
