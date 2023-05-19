package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be
 * added.
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
public class EditActions {

    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of Edit menu actions.
     * </p>
     */
    public EditActions() {
        actions = new ArrayList<Action>();
        actions.add(new UndoAction(bundle.getString("undo"), null, bundle.getString("undo"),
                Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new RedoAction(bundle.getString("redo"), null, bundle.getString("redo"),
                Integer.valueOf(KeyEvent.VK_Y)));
        actions.add(new TextAction(bundle.getString("text"), null, bundle.getString("text"),
                Integer.valueOf(KeyEvent.VK_T)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu(bundle.getString("edit"));

        for (Action action : actions) {
            JMenuItem item = Tools.createMenuItem(action, false, false);
            editMenu.add(item);
        }

        return editMenu;
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().undo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().redo();
            target.repaint();
            target.getParent().revalidate();
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

        static int startX = target.getWidth()/2;
        static int startY = target.getHeight()/2;
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
