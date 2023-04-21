package cosc202.andie;

import java.util.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(bundle.getString("greyscale"), null, bundle.getString("convertToGreyscale"),
                Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new InvertColourAction(bundle.getString("inverted"), null, bundle.getString("invertImageColours"),
                Integer.valueOf(KeyEvent.VK_I)));
        actions.add(new BrightnessContrastAction(bundle.getString("brightness/Contrast"), null,
                bundle.getString("adjustBrightness/Contrast"), Integer.valueOf(KeyEvent.VK_B)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("colour"));

        for (Action action : actions) {
            JMenuItem item = new JMenuItem(action);
            if (action.getValue(Action.MNEMONIC_KEY) != null) {
                if (action instanceof ConvertToGreyAction) {
                    KeyStroke key = KeyStroke.getKeyStroke(
                            (char) ((Integer) action.getValue(Action.MNEMONIC_KEY)).intValue(),
                            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | KeyEvent.SHIFT_DOWN_MASK);
                    item.setAccelerator(key);
                } else {
                    KeyStroke key = KeyStroke.getKeyStroke(
                            (char) ((Integer) action.getValue(Action.MNEMONIC_KEY)).intValue(),
                            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
                    item.setAccelerator(key);
                }
            }
            fileMenu.add(item);
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * Action to invert an image's colour.
     */
    public class InvertColourAction extends ImageAction {

        /**
         * <p>
         * Create a new invert colour action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        InvertColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the invert colour action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the InvertColourAction is triggered.
         * It changes the image to inverted colours.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new InvertColour());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class BrightnessContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new brightness/contrast adjustment
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BrightnessContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the brightness/contrast action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BrightnessContrastAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link BrightnessContrast}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the adjustment to brightness /contrast - ask the user.
            int brightness = 0;
            int contrast = 0;

            // Pop-up dialog box to ask for the radius value.
            brightness = Popup.getInput(0, -100, 100, 1, "enterPercentage", "enterPercentageMessage");
            if(brightness == -1000){
                return;
            }
            contrast = Popup.getInput(0, -100, 100, 1, "enterPercentage", "enterPercentageMessage");
            if(contrast == -1000){
                return;
            }
            // Create and apply the filter
            target.getImage().apply(new BrightnessContrast(brightness, contrast));
            target.repaint();
            target.getParent().revalidate();
        }

    }
}
