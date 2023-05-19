package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Emboss Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains am emboss menu with each of the emboss and sobel
 * filters.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Ada Mazengarb
 * @version 1.0
 */
public class EmbossActions {

    /** A list of actions for the Emboss Filter menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of Emboss menu actions.
     * </p>
     */
    public EmbossActions() {
        actions = new ArrayList<Action>();
        actions.add(new Emboss3Action(bundle.getString("emboss3"), null, bundle.getString("emboss3"),
                Integer.valueOf(KeyEvent.VK_N)));
        actions.add(new Emboss4Action(bundle.getString("emboss4"), null, bundle.getString("emboss4"),
                Integer.valueOf(KeyEvent.VK_N)));
        actions.add(new Emboss5Action(bundle.getString("emboss5"), null, bundle.getString("emboss5"),
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new Emboss6Action(bundle.getString("emboss6"), null, bundle.getString("emboss6"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new Emboss7Action(bundle.getString("emboss7"), null, bundle.getString("emboss7"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new Emboss8Action(bundle.getString("emboss8"), null, bundle.getString("emboss8"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new Emboss1Action(bundle.getString("emboss1"), null, bundle.getString("emboss1"),
                Integer.valueOf(KeyEvent.VK_W)));
        actions.add(new Emboss2Action(bundle.getString("emboss2"), null, bundle.getString("emboss2"),
                Integer.valueOf(KeyEvent.VK_N)));
        actions.add(new SobelHorizontalAction(bundle.getString("sobelHorizontal"), null,
                bundle.getString("sobelHorizontal"), Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new SobelVerticalAction(bundle.getString("sobelVertical"), null,
                bundle.getString("sobelVertical"), Integer.valueOf(KeyEvent.VK_V)));

    }

    /**
     * <p>
     * Create a menu contianing the list of Emboss actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Emboss");

        for (Action action : actions) {
            JMenuItem item;
            if (action instanceof Emboss2Action || action instanceof Emboss4Action || action instanceof Emboss6Action
                    || action instanceof Emboss8Action) {
                item = new JMenuItem(action);
            } else {
                item = Tools.createMenuItem(action, false, true);
            }
            fileMenu.add(item);
        }

        return fileMenu;
    }

    public class Emboss1Action extends ImageAction {
        /**
         * <p>
         * Create a new Emboss1 action.
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
         * Callback for when the Emboss1 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss1Action is triggered.
         * It applys a {@link Emboss1}.
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
         * Create a new Emboss2 action.
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
         * Callback for when the Emboss2 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss2Action is triggered.
         * It applys a {@link Emboss2}.
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
         * Create a new Emboss3 action.
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
         * Callback for when the Emboss3 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss3Action is triggered.
         * It applys a {@link Emboss3}.
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
         * Create a new Emboss4 action.
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
         * Callback for when the Emboss4 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss4Action is triggered.
         * It applys a {@link Emboss4}.
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
         * Create a new Emboss5 action.
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
         * Callback for when the Emboss5 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss5Action is triggered.
         * It applys a {@link Emboss5}.
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
         * Create a new Emboss6 action.
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
         * Callback for when the Emboss6 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss6Action is triggered.
         * It applys a {@link Emboss6}.
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
         * Create a new Emboss7 action.
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
         * Callback for when the Emboss7 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss7Action is triggered.
         * It applys a {@link Emboss7}.
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
         * Create a new Emboss8 action.
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
         * Callback for when the Emboss8 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Emboss8Action is triggered.
         * It applys a {@link Emboss8}.
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
         * Create a new SobelHorizontal action.
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
         * Callback for when the SobelHorizontal action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelHorizontalAction is triggered.
         * It applys a {@link SobelHorizontal}.
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
         * Create a new SobelVertical action.
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
         * Callback for when the SobelVertical action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelVerticalAction is triggered.
         * It applys a {@link SobelVertical}.
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