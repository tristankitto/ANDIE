package cosc202.andie;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Insert menu.
 * </p>
 * 
 * <p>
 * The insert menu contains operations that insert something onto the image.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Tristan Kitto
 * @version 1.0
 */
public class InsertActions {

    /** A list of actions for the View menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of Insert menu actions.
     * </p>
     */
    public InsertActions() {
        actions = new ArrayList<Action>();
        actions.add(new DrawShapesAction(bundle.getString("drawShapes"), null, bundle.getString("drawShapes"),
                Integer.valueOf(KeyEvent.VK_D)));
        actions.add(new TextAction(bundle.getString("text"), null, bundle.getString("text"),
                Integer.valueOf(KeyEvent.VK_T)));
    }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu insertMenu = new JMenu(bundle.getString("insert"));

        for (Action action : actions) {
            JMenuItem item = new JMenuItem();
            if (action instanceof DrawShapesAction || action instanceof TextAction) {
                item = Tools.createMenuItem(action, true, false);
            } else {
                item = Tools.createMenuItem(action, false, false);
            }
            insertMenu.add(item);
        }

        return insertMenu;
    }

    /**
     * <p>
     * Action to draw shapes or a line over a region seleted by the user.
     * </p>
     */
    public class DrawShapesAction extends ImageAction {

        static int startX = 0;
        static int startY = 0;
        static int x = 0;
        static int y = 0;
        static int endX = target.getWidth();
        static int endY = target.getHeight();
        static boolean drawShape = false;
        static boolean isDrawing = false;
        EditableImage image = target.getImage();
        public static String shape;
        public static Color colour;
        public static BasicStroke strokeSize;

        /**
         * <p>
         * Create a new draw shapes action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawShapesAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the draw shapes action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawShapesAction is triggered.
         * It prompts the user for a draw area, then draws the shape.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isDrawing || image.getCurrentImage() == null) {
                return;
            }
            isDrawing = true;
            EditableImage imageCopy = EditableImage.copyImage(image);

            double scale = target.getZoom() / 100;

            target.setImage(imageCopy);

            target.repaint();

            shape = "filledRectangle";
            colour = Color.BLACK;
            strokeSize = new BasicStroke(1);

            JToolBar toolbar = new JToolBar();

            ImageIcon rectangleIcon = new ImageIcon(
                    ViewActions.class.getClassLoader().getResource("icons/rectangle_icon.png"));
            ImageIcon filledRectangleIcon = new ImageIcon(
                    ViewActions.class.getClassLoader().getResource("icons/filledRectangle_icon.png"));
            ImageIcon ovalIcon = new ImageIcon(ViewActions.class.getClassLoader().getResource("icons/oval_icon.png"));
            ImageIcon filledOvalIcon = new ImageIcon(
                    ViewActions.class.getClassLoader().getResource("icons/filledOval_icon.png"));
            ImageIcon lineIcon = new ImageIcon(ViewActions.class.getClassLoader().getResource("icons/line_icon.png"));
            ImageIcon lineWidthIcon = new ImageIcon(
                    ViewActions.class.getClassLoader().getResource("icons/lineWidth_icon.png"));
            ImageIcon colourChooseIcon = new ImageIcon(
                    ViewActions.class.getClassLoader().getResource("icons/colourChoose_icon.png"));

            JButton colourButton = new JButton(bundle.getString("colour"));
            colourButton.setIcon(colourChooseIcon);
            colourButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    colour = JColorChooser.showDialog(Andie.frame, bundle.getString("selectColour"), Color.BLACK);
                }
            });

            JMenuItem rectangle = new JMenuItem(bundle.getString("rectangle"));
            rectangle.setIcon(rectangleIcon);
            rectangle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shape = "Rectangle";
                }
            });
            JMenuItem filledRectangle = new JMenuItem(bundle.getString("filledRectangle"));
            filledRectangle.setIcon(filledRectangleIcon);
            filledRectangle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shape = "filledRectangle";
                }
            });

            JButton rectangleButton = new JButton(bundle.getString("rectangle"));
            rectangleButton.setIcon(rectangleIcon);
            rectangleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JPopupMenu rectangleMenu = new JPopupMenu();
                    rectangleMenu.add(rectangle);
                    rectangleMenu.add(filledRectangle);
                    rectangleMenu.show(rectangleButton, 0, rectangleButton.getHeight());
                }
            });

            JMenuItem oval = new JMenuItem(bundle.getString("oval"));
            oval.setIcon(ovalIcon);
            oval.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shape = "Oval";
                }
            });
            JMenuItem filledOval = new JMenuItem(bundle.getString("filledOval"));
            filledOval.setIcon(filledOvalIcon);
            filledOval.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shape = "filledOval";
                }
            });

            JButton ovalButton = new JButton(bundle.getString("oval"));
            ovalButton.setIcon(ovalIcon);
            ovalButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JPopupMenu ovalMenu = new JPopupMenu();
                    ovalMenu.add(oval);
                    ovalMenu.add(filledOval);
                    ovalMenu.show(ovalButton, 0, ovalButton.getHeight());
                }
            });

            JButton lineButton = new JButton(bundle.getString("line"));
            lineButton.setIcon(lineIcon);
            lineButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shape = "Line";
                }
            });

            JButton strokeSizeButton = new JButton(bundle.getString("lineWidth"));
            strokeSizeButton.setIcon(lineWidthIcon);
            strokeSizeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Integer[] sizes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
                    JComboBox<Integer> comboBox = new JComboBox<>(sizes);

                    int option = JOptionPane.showOptionDialog(toolbar, comboBox, bundle.getString("lineWidth"),
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (option == JOptionPane.OK_OPTION) {
                        Integer size = (Integer) comboBox.getSelectedItem();
                        strokeSize = new BasicStroke(size);
                    }
                }
            });

            if (!isDrawing)
                return;

            startX = 0;
            startY = 0;
            endX = target.getWidth();
            endY = target.getHeight();

            target.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            MouseListener mouseListener = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    drawShape = true;
                    startX = e.getX();
                    startY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    drawShape = false;
                    image.apply(
                            new DrawShapes((int) (startX / scale), (int) (startY / scale), (int) (endX / scale),
                                    (int) (endY / scale), shape, colour, strokeSize));

                    target.repaint();

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
                    Andie.frame.remove(toolbar);
                    Andie.createToolBar();
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(mouseListener);
                    target.removeMouseMotionListener(mouseMotionListener);
                    target.setCursor(Cursor.getDefaultCursor());
                    isDrawing = false;
                    return;
                }
            };
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
            Andie.imagePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "keyAction");
            Andie.imagePanel.getActionMap().put("keyAction", keyAction);

            JButton cancelButton = new JButton(bundle.getString("cancel"));
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Andie.frame.remove(toolbar);
                    Andie.createToolBar();
                    target.setImage(image);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(mouseListener);
                    target.removeMouseMotionListener(mouseMotionListener);
                    target.setCursor(Cursor.getDefaultCursor());
                    isDrawing = false;
                    return;
                }
            });

            toolbar.add(rectangleButton);
            toolbar.add(ovalButton);
            toolbar.add(lineButton);
            toolbar.add(colourButton);
            toolbar.add(strokeSizeButton);
            toolbar.add(cancelButton);
            Andie.removeToolBar();
            Andie.frame.add(toolbar, BorderLayout.PAGE_START);
            Andie.frame.setVisible(true);
        }

    }

    /**
     * <p>
     * Action to add text.
     * </p>
     * 
     * @see Text#apply()
     */
    public class TextAction extends ImageAction {

        static int startX = target.getWidth() / 2;
        static int startY = target.getHeight() / 2;
        static int x = 0;
        static int y = 0;
        static int endX = target.getWidth();
        static int endY = target.getHeight();
        static boolean text = false;
        static boolean isTexting = false;
        EditableImage image = target.getImage();

        /**
         * <p>
         * Create a new text action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        TextAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the text action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the TextAction is triggered.
         * It adds a text box.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isTexting || image.getCurrentImage() == null) {
                return;
            }

            isTexting = true;

            startX = 0;
            startY = 0;
            endX = target.getWidth();
            endY = target.getHeight();

            target.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            MouseListener mouseListener = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    text = true;
                    startX = e.getX();
                    startY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    text = false;
                    isTexting = false;

                    // Perform the crop operation
                    image.apply(new Text(startX, startY, endX, endY));
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
                    return;
                }
            };
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
            Andie.imagePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "keyAction");
            Andie.imagePanel.getActionMap().put("keyAction", keyAction);
        }
    }

}
