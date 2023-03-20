package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class LanguageActions {

    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Language menu actions.
     * </p>
     */
    public LanguageActions() {
        actions = new ArrayList<Action>();
        actions.add(new LanguageChoice(ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("english"),
                null, ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("englishlanguage"),
                Integer.valueOf(KeyEvent.VK_E), "en"));
        actions.add(new LanguageChoice(ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("maori"),
                null, ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("maorilanguage"),
                Integer.valueOf(KeyEvent.VK_M), "mi"));
        actions.add(new LanguageChoice(ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("spanish"),
                null, ResourceBundle.getBundle("cosc202.andie.LanguageBundle").getString("spanishlanguage"),
                Integer.valueOf(KeyEvent.VK_S), "es"));
    }

    /**
     * <p>
     * Create a menu contianing the list of Language actions.
     * </p>
     * 
     * @return The Language menu UI element.
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
     * Action to choose a language.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class LanguageChoice extends ImageAction {
        private String language;

        /**
         * <p>
         * Create a new language choice action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param code     A 2 letter code used to represent the language selected.
         */
        LanguageChoice(String name, ImageIcon icon, String desc, Integer mnemonic, String code) {
            super(name, icon, desc, mnemonic);
            this.language = code;
        }

        /**
         * <p>
         * Callback for when the language choice action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the LanguageChoice is triggered.
         * It changes the language of the GUI.
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
