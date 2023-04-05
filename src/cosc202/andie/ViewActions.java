package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the View menu.
 * </p>
 * 
 * <p>
 * The View menu contains actions that affect how the image is displayed in the
 * application.
 * These actions do not affect the contents of the image itself, just the way it
 * is displayed.
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
public class ViewActions {

    /** A list of actions for the View menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of View menu actions.
     * </p>
     */
    public ViewActions() {
        actions = new ArrayList<Action>();
        actions.add(new ZoomInAction(bundle.getString("zoomIn"), null, bundle.getString("zoomIn"),
                Integer.valueOf(KeyEvent.VK_PLUS)));
        actions.add(new ZoomOutAction(bundle.getString("zoomOut"), null, bundle.getString("zoomOut"),
                Integer.valueOf(KeyEvent.VK_MINUS)));
        actions.add(new ZoomFullAction(bundle.getString("zoomFull"), null, bundle.getString("zoomFull"),
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new RotateClockwiseAction(bundle.getString("rotateClockwise"), null,
                bundle.getString("rotateClockwise"),
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new RotateAnticlockwiseAction(bundle.getString("rotateAnticlockwise"), null,
                bundle.getString("rotateAnticlockwise"),
                Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new Rotate180Action(bundle.getString("rotate180"), null, bundle.getString("rotate180"),
                Integer.valueOf(KeyEvent.VK_1)));
        actions.add(
                new FlipHorizontalAction(bundle.getString("flipHorizontal"), null, bundle.getString("flipHorizontal"),
                        Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new FlipVerticalAction(bundle.getString("flipVertical"), null, bundle.getString("flipVertical"),
                Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new ResizeAction(bundle.getString("resize"), null, bundle.getString("resize"),
                Integer.valueOf(KeyEvent.VK_V)));
    }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu viewMenu = new JMenu(bundle.getString("view"));

        for (Action action : actions) {
            viewMenu.add(new JMenuItem(action));
        }

        return viewMenu;
    }

    /**
     * <p>
     * Action to zoom in on an image.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its
     * actual contents.
     * </p>
     */
    public class ZoomInAction extends ImageAction {

        /**
         * <p>
         * Create a new zoom-in action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ZoomInAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-in action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomInAction is triggered.
         * It increases the zoom level by 10%, to a maximum of 200%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.setZoom(target.getZoom() + 10);
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to zoom out of an image.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its
     * actual contents.
     * </p>
     */
    public class ZoomOutAction extends ImageAction {

        /**
         * <p>
         * Create a new zoom-out action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ZoomOutAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-iout action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomOutAction is triggered.
         * It decreases the zoom level by 10%, to a minimum of 50%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.setZoom(target.getZoom() - 10);
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to reset the zoom level to actual size.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its
     * actual contents.
     * </p>
     */
    public class ZoomFullAction extends ImageAction {

        /**
         * <p>
         * Create a new zoom-full action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ZoomFullAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-full action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomFullAction is triggered.
         * It resets the Zoom level to 100%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.setZoom(100);
            target.revalidate();
            target.getParent().revalidate();
        }

    }

    /**
     * Action to rotate an image clockwise
     */
    public class RotateClockwiseAction extends ImageAction {
        RotateClockwiseAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new RotateClockwise());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Action to rotate an image anticlockwise
     */
    public class RotateAnticlockwiseAction extends ImageAction {
        RotateAnticlockwiseAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new RotateAnticlockwise());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Action to flip an image horizontally
     */
    public class FlipHorizontalAction extends ImageAction {
        FlipHorizontalAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new FlipHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Action to flip an image vertically
     */
    public class FlipVerticalAction extends ImageAction {
        FlipVerticalAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new FlipVertical());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Action to rotate an image 180 degrees
     */
    public class Rotate180Action extends ImageAction {
        Rotate180Action(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new Rotate180());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action resize an image with a percentage given by the user.
     * </p>
     * 
     * @see Resize
     */
    public class ResizeAction extends ImageAction {

        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeAction is triggered.
         * It prompts the user for a resize percentage, then resizes the image
         * {@link Resize}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the percentage - ask the user.
            int percentage = 100;

            // Pop-up dialog box to ask for the percentage value.
            SpinnerNumberModel percentageModel = new SpinnerNumberModel(100, 1, 200, 1);
            JSpinner percentageSpinner = new JSpinner(percentageModel);

            Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };

            int option = JOptionPane.showOptionDialog(null, percentageSpinner, bundle.getString("enterPercentage"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            // Check the return value from the dialog box.
            if (option == 1) {
                return;
            } else {
                percentage = percentageModel.getNumber().intValue();

                // Create and apply the filter
                target.getImage().apply(new Resize(percentage));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }
}