package cosc202.andie;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        actions.add(new ZoomAction(bundle.getString("changeZoom"), null, bundle.getString("changeZoom"),
                Integer.valueOf(KeyEvent.VK_Z)));
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
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new CropAction(bundle.getString("crop"), null, bundle.getString("cropImage"),
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new DrawRectangleAction(("Rectangle"), null, ("drawRectangle"),
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new DrawOvalAction(("Oval"), null, ("drawOval"),
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new DrawLineAction(("Line"), null, ("drawLine"),
                Integer.valueOf(KeyEvent.VK_C)));
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
            JMenuItem item = new JMenuItem();
            if (action instanceof ZoomAction || action instanceof CropAction) {
                item = Tools.createMenuItem(action, true, false);
            } else {
                item = Tools.createMenuItem(action, false, false);
            }
            viewMenu.add(item);
        }

        return viewMenu;
    }

    /**
     * <p>
     * Action to change zoom on an image.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its
     * actual contents.
     * </p>
     */
    public class ZoomAction extends ImageAction {

        /** int variable to store the change in zoom selected by the user */
        private static int zoom;

        /**
         * <p>
         * Create a new zoom action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ZoomAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomAction is triggered.
         * It changes the zoom of the image from anywhere between 0% and 200%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            double originalZoom = target.getZoom();

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 100));
            panel.setLayout(new GridLayout(2, 1));

            // Create a JSlider
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, (int) originalZoom);
            slider.setMajorTickSpacing(20);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            panel.add(new JLabel(bundle.getString("changeZoomMessage")));
            panel.add(slider);

            // Add a ChangeListener to the JSlider
            ChangeListener CL = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    // Get the value from the JSlider
                    zoom = slider.getValue();
                    // Update the image with the zoom value
                    target.setZoom(zoom);
                    target.repaint();
                    target.getParent().revalidate();
                }
            };

            slider.addChangeListener(CL);

            Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };
            int option = JOptionPane.showOptionDialog(null,
                    panel, bundle.getString("changeZoom"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            // Check the return value from the dialog box.
            if (option == 1 || option == JOptionPane.CLOSED_OPTION) {
                target.setZoom(originalZoom);
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == 0) {
                target.setZoom(zoom);
                target.repaint();
                target.getParent().revalidate();
            }

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
     * <p>
     * Action to rotate an image clockwise
     * </p>
     */
    public class RotateClockwiseAction extends ImageAction {
        /**
         * <p>
         * Create a new rotate clockwise action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateClockwiseAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate clockwise action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateClockwiseAction is triggered.
         * It rotates the image 90 degrees clockwise.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new RotateClockwise());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image anti-clockwise
     * </p>
     */
    public class RotateAnticlockwiseAction extends ImageAction {
        /**
         * <p>
         * Create a new rotate anti-clockwise action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateAnticlockwiseAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate anti-clockwise action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateAnticlockwiseAction is triggered.
         * It rotates the image 90 degrees anti-clockwise.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new RotateAnticlockwise());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to flip an image horizontally
     * </p>
     */
    public class FlipHorizontalAction extends ImageAction {
        /**
         * <p>
         * Create a new flip horizontal action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipHorizontalAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the flip horizontally action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipHorizontalAction is triggered.
         * It flips the image on its vertical axis.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new FlipHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to flip an image vertically
     * </p>
     */
    public class FlipVerticalAction extends ImageAction {
        /**
         * <p>
         * Create a new flip vertical action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipVerticalAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the flip vertically action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipVerticalAction is triggered.
         * It flips the image on its horizontal axis.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new FlipVertical());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image 180 degrees
     * </p>
     */
    public class Rotate180Action extends ImageAction {
        /**
         * <p>
         * Create a new rotate 180 action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        Rotate180Action(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate 180 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate180Action is triggered.
         * It rotates the image by 180 degrees by flipping along the horizontal and
         * vertical axis.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new Rotate180());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to resize an image with a percentage given by the user.
     * </p>
     */
    public class ResizeAction extends ImageAction {

        /** int variable to store the size percentage change of the image to resize */
        private static int percentage;

        boolean applied = false;

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
         * It prompts the user for a resize percentage, then scales the image
         * size based on the percentage
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
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
            slider.setMajorTickSpacing(20);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            panel.add(new JLabel(bundle.getString("enterPercentageMessage")));
            panel.add(slider);

            // Add a ChangeListener to the JSlider
            ChangeListener CL = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    EditableImage imageCopy = EditableImage.copyImage(image);
                    target.setImage(imageCopy);
                    // Get the value from the JSlider
                    percentage = slider.getValue();
                    // Update the image with the percentage value
                    try {
                        target.getImage().tempApply(new Resize(percentage));
                    } catch (Exception ex) {
                        Tools.errorMessage(ex, "fileApplyError");
                        Andie.frame.setCursor(Cursor.getDefaultCursor());
                    }
                    target.repaint();
                    target.getParent().revalidate();
                }
            };

            slider.addChangeListener(CL);

            Object[] options = { bundle.getString("ok"), bundle.getString("cancel") };
            int option = JOptionPane.showOptionDialog(null,
                    panel, bundle.getString("enterPercentage"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            // Check the return value from the dialog box.
            if (option == 1 || option == JOptionPane.CLOSED_OPTION) {
                target.setImage(image);
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == 0) {
                target.setImage(image);
                target.getImage().apply(new Resize(percentage));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to crop an image with an area given by the user.
     * </p>
     */
    public class CropAction extends ImageAction {

        static int startX = 0;
        static int startY = 0;
        static int x = 0;
        static int y = 0;
        static int endX = target.getWidth();
        static int endY = target.getHeight();
        static boolean crop = false;
        static boolean isCropping = false;
        EditableImage image = target.getImage();

        /**
         * <p>
         * Create a new crop action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the crop action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the CropAction is triggered.
         * It prompts the user for a crop area, then crops the image based on the area.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isCropping || image.getCurrentImage() == null) {
                return;
            }
            isCropping = true;
            EditableImage imageCopy = EditableImage.copyImage(image);

            double scale = target.getZoom() / 100;

            imageCopy.tempApply(new BrightnessContrast(-50, 0));
            target.setImage(imageCopy);

            target.repaint();

            startX = 0;
            startY = 0;
            endX = target.getWidth();
            endY = target.getHeight();

            target.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            MouseListener mouseListener = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    crop = true;
                    startX = e.getX();
                    startY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    crop = false;
                    isCropping = false;

                    // Perform the crop operation
                    image.apply(new Crop((int) (startX / scale), (int) (startY / scale),
                            (int) (endX / scale), (int) (endY / scale)));
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();

                    // Remove the mouse listeners after the crop is done
                    target.removeMouseListener(this);
                    target.setCursor(Cursor.getDefaultCursor());

                }
            };

            target.addMouseListener(mouseListener);

            MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    target.repaint();
                }
            };

            target.addMouseMotionListener(mouseMotionListener);

            Action keyAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(mouseListener);
                    target.removeMouseMotionListener(mouseMotionListener);
                    target.setCursor(Cursor.getDefaultCursor());
                    isCropping = false;
                    return;
                }
            };
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
            Andie.imagePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "keyAction");
            Andie.imagePanel.getActionMap().put("keyAction", keyAction);
        }

    }

    /**
     * <p>
     * Action to draw a rectangle with an area given by the user.
     * </p>
     */
    public class DrawRectangleAction extends ImageAction {

        static int startX = 0;
        static int startY = 0;
        static int x = 0;
        static int y = 0;
        static int endX = target.getWidth();
        static int endY = target.getHeight();
        static boolean drawRectangle = false;
        static boolean isDrawingRectangle = false;
        EditableImage image = target.getImage();

        /**
         * <p>
         * Create a new draw rectangle action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the draw rectangle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawRectangleAction is triggered.
         * It prompts the user for a draw area, then draws the rectangle.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isDrawingRectangle || image.getCurrentImage() == null) {
                return;
            }
            isDrawingRectangle = true;
            EditableImage imageCopy = EditableImage.copyImage(image);

            double scale = target.getZoom() / 100;

            target.setImage(imageCopy);

            target.repaint();

            startX = 0;
            startY = 0;
            endX = target.getWidth();
            endY = target.getHeight();

            target.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            MouseListener mouseListener = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    drawRectangle = true;
                    startX = e.getX();
                    startY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    drawRectangle = false;
                    isDrawingRectangle = false;

                    // Perform the Rectangle operation
                    image.apply(new DrawRectangle((int) (startX / scale), (int) (startY / scale), (int) (endX / scale), (int) (endY / scale)));
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    
                    // Remove the mouse listeners after the Drawing is done
                    target.removeMouseListener(this);
                    target.setCursor(Cursor.getDefaultCursor());

                }
            };

            target.addMouseListener(mouseListener);

            MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    target.repaint();
                }
            };

            target.addMouseMotionListener(mouseMotionListener);

            Action keyAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(mouseListener);
                    target.removeMouseMotionListener(mouseMotionListener);
                    target.setCursor(Cursor.getDefaultCursor());
                    isDrawingRectangle = false;
                    return;
                }
            };
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
            Andie.imagePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "keyAction");
            Andie.imagePanel.getActionMap().put("keyAction", keyAction);
        }

    }

    /**
     * <p>
     * Action to draw a oval with an area given by the user.
     * </p>
     */
    public class DrawOvalAction extends ImageAction {

        static int startX = 0;
        static int startY = 0;
        static int x = 0;
        static int y = 0;
        static int endX = target.getWidth();
        static int endY = target.getHeight();
        static boolean drawOval = false;
        static boolean isDrawingOval = false;
        EditableImage image = target.getImage();

        /**
         * <p>
         * Create a new draw oval action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawOvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the draw oval action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawOvalAction is triggered.
         * It prompts the user for a draw area, then draws the oval.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isDrawingOval || image.getCurrentImage() == null) {
                return;
            }
            isDrawingOval = true;
            EditableImage imageCopy = EditableImage.copyImage(image);

            double scale = target.getZoom() / 100;

            target.setImage(imageCopy);

            target.repaint();

            startX = 0;
            startY = 0;
            endX = target.getWidth();
            endY = target.getHeight();

            target.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            MouseListener mouseListener = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    drawOval = true;
                    startX = e.getX();
                    startY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    drawOval = false;
                    isDrawingOval = false;

                    // Perform the Oval operation
                    image.apply(new DrawOval((int) (startX / scale), (int) (startY / scale), (int) (endX / scale), (int) (endY / scale)));
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    
                    // Remove the mouse listeners after the Drawing is done
                    target.removeMouseListener(this);
                    target.setCursor(Cursor.getDefaultCursor());

                }
            };

            target.addMouseListener(mouseListener);

            MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    target.repaint();
                }
            };

            target.addMouseMotionListener(mouseMotionListener);

            Action keyAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(mouseListener);
                    target.removeMouseMotionListener(mouseMotionListener);
                    target.setCursor(Cursor.getDefaultCursor());
                    isDrawingOval = false;
                    return;
                }
            };
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
            Andie.imagePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "keyAction");
            Andie.imagePanel.getActionMap().put("keyAction", keyAction);
        }

    }

    /**
     * <p>
     * Action to draw a Line with coordinants given by the user.
     * </p>
     */
    public class DrawLineAction extends ImageAction {

        static int startX = 0;
        static int startY = 0;
        static int x = 0;
        static int y = 0;
        static int endX = target.getWidth();
        static int endY = target.getHeight();
        static boolean drawLine = false;
        static boolean isDrawingLine = false;
        EditableImage image = target.getImage();

        /**
         * <p>
         * Create a new draw Line action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the draw Line action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawLineAction is triggered.
         * It prompts the user for a draw area, then draws the Line.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isDrawingLine || image.getCurrentImage() == null) {
                return;
            }
            isDrawingLine = true;
            EditableImage imageCopy = EditableImage.copyImage(image);

            double scale = target.getZoom() / 100;

            target.setImage(imageCopy);

            target.repaint();

            startX = 0;
            startY = 0;
            endX = target.getWidth();
            endY = target.getHeight();

            target.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            MouseListener mouseListener = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    drawLine = true;
                    startX = e.getX();
                    startY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    drawLine = false;
                    isDrawingLine = false;

                    // Perform the Line operation
                    image.apply(new DrawLine((int) (startX / scale), (int) (startY / scale), (int) (endX / scale), (int) (endY / scale)));
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    
                    // Remove the mouse listeners after the Drawing is done
                    target.removeMouseListener(this);
                    target.setCursor(Cursor.getDefaultCursor());

                }
            };

            target.addMouseListener(mouseListener);

            MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    target.repaint();
                }
            };

            target.addMouseMotionListener(mouseMotionListener);

            Action keyAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(mouseListener);
                    target.removeMouseMotionListener(mouseMotionListener);
                    target.setCursor(Cursor.getDefaultCursor());
                    isDrawingLine = false;
                    return;
                }
            };
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
            Andie.imagePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "keyAction");
            Andie.imagePanel.getActionMap().put("keyAction", keyAction);
        }

    }
}