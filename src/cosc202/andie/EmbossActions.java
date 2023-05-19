package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

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
public class EmbossActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public EmbossActions() {
        actions = new ArrayList<Action>();
        actions.add(new Emboss1Action("WestEmboss", null, "WestEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss2Action("NorthWestEmboss", null, "NorthWestEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss3Action("NorthEmboss", null, "NorthEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss4Action("NorthEastEmboss", null, "NorthEastEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss5Action("EastEmboss", null, "EastEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss6Action("SouthEastEmboss", null, "SouthEastEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss7Action("SouthEmboss", null, "SouthEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss8Action("SouthWestEmboss", null, "SouthWestEmboss", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(
                new SobelHorizontalAction("HorizontalSobel", null, "HorizontalSobel", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new SobelVerticalAction("VerticalSobel", null, "VerticalSobel", Integer.valueOf(KeyEvent.VK_E)));

    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Emboss");

        for (Action action : actions) {
            JMenuItem item = Tools.createMenuItem(action, false, false);
            fileMenu.add(item);
        }

        return fileMenu;
    }

    public class Emboss1Action extends ImageAction {
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
        Emboss1Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss1());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Emboss2Action extends ImageAction {
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
        Emboss2Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss2());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Emboss3Action extends ImageAction {
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
        Emboss3Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss3());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Emboss4Action extends ImageAction {
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
        Emboss4Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss4());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Emboss5Action extends ImageAction {
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
        Emboss5Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss5());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Emboss6Action extends ImageAction {
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
        Emboss6Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss6());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Emboss7Action extends ImageAction {
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
        Emboss7Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss7());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Emboss8Action extends ImageAction {
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
        Emboss8Action(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new Emboss8());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SobelHorizontalAction extends ImageAction {
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
        SobelHorizontalAction(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new SobelHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SobelVerticalAction extends ImageAction {
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
        SobelVerticalAction(String name, ImageIcon icon,
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
            target.getImage().apply(new EmbossClasses().new SobelVertical());
            target.repaint();
            target.getParent().revalidate();
        }
    }

}