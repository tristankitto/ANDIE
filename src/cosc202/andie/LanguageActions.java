package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
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
public class LanguageActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public LanguageActions() {
        actions = new ArrayList<Action>();
        actions.add(new LanguageChoice(ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("english"),
                null, ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("englishlanguage"),
                Integer.valueOf(KeyEvent.VK_E), "en"));
        actions.add(new LanguageChoice(ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("spanish"),
                null, ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("spanishlanguage"),
                Integer.valueOf(KeyEvent.VK_S), "es"));
        actions.add(new LanguageChoice(ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("maori"),
                null, ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("maorilanguage"),
                Integer.valueOf(KeyEvent.VK_M), "mi"));
    }

    /**
     * <p>
     * Create a menu contianing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("language"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class LanguageChoice extends ImageAction {
        private String language;

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        LanguageChoice(String name, ImageIcon icon, String desc, Integer mnemonic, String code) {
            super(name, icon, desc, mnemonic);
            this.language = code;
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (this.language.equals("en")) {
                Locale.setDefault(Locale.ENGLISH);
            }
            if (this.language.equals("es")) {
                Locale.setDefault(new Locale("es", "ES"));
            }
            if (this.language.equals("mi")) {
                Locale.setDefault(new Locale("mi", "NZ"));
            }
            Andie.createMenuBar();
        }

    }
}
