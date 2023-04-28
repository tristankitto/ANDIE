package cosc202.andie;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
     * <p>
     * Action to invert an image's colour.
     * </p>
     * 
     * @see InvertColour
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

    /**
     * <p>
     * Action to change the brightness and contrast of an image.
     * </p>
     * 
     * @see BrightnessContrast
     */
    public class BrightnessContrastAction extends ImageAction {

        /** The brightness selected by the user to change the brightness of the image */
        private static int brightness;

        /** The contrast selected by the user to change the contrast of the image */
        private static int contrast;

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
         * It prompts the user with a slider for brightness and another one for contrast
         * then applies the appropriate {@link BrightnessContrast}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            EditableImage image = target.getImage();

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 200));
            panel.setLayout(new GridLayout(4, 1));

            // Create a JSlider
            JSlider sliderBrightness = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
            sliderBrightness.setMajorTickSpacing(20);
            sliderBrightness.setPaintTicks(true);
            sliderBrightness.setPaintLabels(true);
            panel.add(new JLabel(bundle.getString("enterBrightness")));
            panel.add(sliderBrightness);

            JSlider sliderContrast = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
            sliderContrast.setMajorTickSpacing(20);
            sliderContrast.setPaintTicks(true);
            sliderContrast.setPaintLabels(true);
            panel.add(new JLabel(bundle.getString("enterContrast")));
            panel.add(sliderContrast);

            // Add a ChangeListener to the JSlider
            ChangeListener CL = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    EditableImage imageCopy = EditableImage.copyImage(image);
                    target.setImage(imageCopy);
                    // Get the value from the JSlider
                    brightness = sliderBrightness.getValue();
                    contrast = sliderContrast.getValue();
                    // Update the image with the brightness value
                    try {
                        target.getImage().tempApply(new BrightnessContrast(brightness, contrast));
                    } catch (Exception ex) {
                        Popup.errorMessage(ex, "fileApplyError");
                    }
                    target.repaint();
                    target.getParent().revalidate();
                }
            };

            sliderBrightness.addChangeListener(CL);
            sliderContrast.addChangeListener(CL);

            Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };
            int option = JOptionPane.showOptionDialog(null,
                    panel, bundle.getString("brightness/Contrast"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            // Check the return value from the dialog box.
            if (option == 1) {
                target.setImage(image);
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == 0) {
                target.setImage(image);
                target.getImage().apply(new BrightnessContrast(brightness, contrast));
                target.repaint();
                target.getParent().revalidate();
            }
        }

    }
}
