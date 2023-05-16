package cosc202.andie;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur), Soft blur filter, Sharpen
 * filter, and more.
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
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(
                new MeanFilterAction(bundle.getString("meanFilter"), null, bundle.getString("applyAMeanFilter"),
                        Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SoftBlurAction(bundle.getString("softBlur"), null, bundle.getString("applyASoftBlur"),
                Integer.valueOf(KeyEvent.VK_T)));
        actions.add(new SharpenFilterAction(bundle.getString("sharpenFilter"), null,
                bundle.getString("applyASharpenFilter"), Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new GaussianBlurAction(
                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle").getString("gaussianBlur"),
                null, bundle.getString("applyAGaussianBlur"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new MedianFilterAction(
                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle").getString("medianFilter"),
                null, bundle.getString("applyAMedianFilter"), Integer.valueOf(KeyEvent.VK_D)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("filter"));

        for (Action action : actions) {
            JMenuItem item = Tools.createMenuItem(action, false, false);
            fileMenu.add(item);
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        private static int radius;
        boolean applied = false;

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the mean filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            EditableImage image = target.getImage();

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 100));
            panel.setLayout(new GridLayout(2, 1));

            // Create a JSlider
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
            slider.setMajorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            panel.add(new JLabel(bundle.getString("chooseFilterRadiusMessage")));
            panel.add(slider);

            // Add a ChangeListener to the JSlider
            ChangeListener CL = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider) e.getSource();
                    if (!source.getValueIsAdjusting()) {
                        // The slider has finished being dragged
                        EditableImage imageCopy = EditableImage.copyImage(image);
                        target.setImage(imageCopy);
                        // Get the value from the JSlider
                        radius = source.getValue();
                        // Update the image with the percentage value
                        try {
                            if (radius != 0) {
                                source.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                                target.getImage().apply(new MeanFilter(radius));
                                applied = true;
                                source.setCursor(Cursor.getDefaultCursor());
                            }
                        } catch (Exception ex) {
                            Tools.errorMessage(ex, "fileApplyError");
                        }
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }
            };

            slider.addChangeListener(CL);

            Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };
            int option = JOptionPane.showOptionDialog(null,
                    panel, bundle.getString("applyAMeanFilter"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            // Check the return value from the dialog box.
            if (option == 1) {
                target.setImage(image);
                if (applied) {
                    image.removeLastAction();
                    applied = false;
                }
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == 0) {
                target.repaint();
                target.getParent().revalidate();
            }
        }

    }

    /**
     * <p>
     * Action to blur an image with a soft blur filter.
     * </p>
     * 
     * @see SoftBlur
     */
    public class SoftBlurAction extends ImageAction {
        /**
         * <p>
         * Create a new soft blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SoftBlurAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the soft blur action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * It applys a {@link SoftBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to sharpen an image with a sharpen filter.
     * </p>
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {
        /**
         * <p>
         * Create a new sharpen filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the sharpen filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * It applys a {@link SharpenFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a Gaussian blur.
     * </p>
     * 
     * @see GaussianBlur
     */
    public class GaussianBlurAction extends ImageAction {
        private static int radius;
        boolean applied = false;

        /**
         * <p>
         * Create a new Gaussian blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianBlurAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Gaussian blur action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianBlurAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link GaussianBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            EditableImage image = target.getImage();

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 100));
            panel.setLayout(new GridLayout(2, 1));

            // Create a JSlider
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
            slider.setMajorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            panel.add(new JLabel(bundle.getString("chooseFilterRadiusMessage")));
            panel.add(slider);

            // Add a ChangeListener to the JSlider
            ChangeListener CL = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider) e.getSource();
                    if (!source.getValueIsAdjusting()) {
                        // The slider has finished being dragged
                        EditableImage imageCopy = EditableImage.copyImage(image);
                        target.setImage(imageCopy);
                        // Get the value from the JSlider
                        radius = source.getValue();
                        // Update the image with the percentage value
                        try {
                            if (radius != 0) {
                                source.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                                target.getImage().apply(new GaussianBlur(radius));
                                applied = true;
                                source.setCursor(Cursor.getDefaultCursor());
                            }
                        } catch (Exception ex) {
                            Tools.errorMessage(ex, "fileApplyError");
                        }
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }
            };

            slider.addChangeListener(CL);

            Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };
            int option = JOptionPane.showOptionDialog(null,
                    panel, bundle.getString("applyAGaussianBlur"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            // Check the return value from the dialog box.
            if (option == 1) {
                target.setImage(image);
                if (applied) {
                    image.removeLastAction();
                    applied = false;
                }
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == 0) {
                target.setImage(image);
                if (radius != 0) {
                    target.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    target.getImage().apply(new GaussianBlur(radius));
                    target.setCursor(Cursor.getDefaultCursor());
                }
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to blur an image with a median filter.
     * </p>
     * 
     * @see MedianFilter
     */
    public class MedianFilterAction extends ImageAction {
        private static int radius;
        boolean applied = false;

        /**
         * <p>
         * Create a new median filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the median filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            EditableImage image = target.getImage();

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 100));
            panel.setLayout(new GridLayout(2, 1));

            // Create a JSlider
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
            slider.setMajorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            panel.add(new JLabel(bundle.getString("chooseFilterRadiusMessage")));
            panel.add(slider);

            // Add a ChangeListener to the JSlider
            ChangeListener CL = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider) e.getSource();
                    if (!source.getValueIsAdjusting()) {
                        // The slider has finished being dragged
                        EditableImage imageCopy = EditableImage.copyImage(image);
                        target.setImage(imageCopy);
                        // Get the value from the JSlider
                        radius = source.getValue();
                        // Update the image with the percentage value
                        try {
                            if (radius != 0) {
                                source.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                                target.getImage().apply(new MedianFilter(radius));
                                applied = true;
                                source.setCursor(Cursor.getDefaultCursor());
                            }
                        } catch (Exception ex) {
                            Tools.errorMessage(ex, "fileApplyError");
                        }
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }
            };

            slider.addChangeListener(CL);

            Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };
            int option = JOptionPane.showOptionDialog(null,
                    panel, bundle.getString("applyAMedianFilter"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            // Check the return value from the dialog box.
            if (option == 1) {
                target.setImage(image);
                if (applied) {
                    image.removeLastAction();
                    applied = false;
                }
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == 0) {
                target.setImage(image);
                if (radius != 0) {
                    target.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    target.getImage().apply(new MedianFilter(radius));
                    target.setCursor(Cursor.getDefaultCursor());
                }
                target.repaint();
                target.getParent().revalidate();
            }
        }

    }
}
