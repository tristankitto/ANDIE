package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

/**
 * <p>
 * Actions provided by the Language menu.
 * </p>
 * 
 * <p>
 * The Language menu contains actions that update the language of the UI.
 * This includes English, Maori, Spanish, and more.
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
public class LanguageActions {

        /** A list of actions for the Language menu. */
        protected ArrayList<Action> actions;

        /**
         * <p>
         * Create a set of Language menu actions.
         * </p>
         */
        public LanguageActions() {
                actions = new ArrayList<Action>();
                actions.add(new LanguageChoice(
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("english")
                                                + " (English)",
                                null,
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("englishlanguage")
                                                + " (English Language)",
                                Integer.valueOf(KeyEvent.VK_E), "en"));
                actions.add(new LanguageChoice(
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("maori")
                                                + " (Māori)",
                                null,
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("maorilanguage")
                                                + " (Te reo Māori)",
                                Integer.valueOf(KeyEvent.VK_M), "mi"));
                actions.add(new LanguageChoice(
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("spanish")
                                                + " (Español)",
                                null,
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("spanishlanguage")
                                                + " (Lengua española)",
                                Integer.valueOf(KeyEvent.VK_S), "es"));
                actions.add(new LanguageChoice(
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("french")
                                                + " (Français)",
                                null,
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("frenchlanguage")
                                                + " (Langue française)",
                                Integer.valueOf(KeyEvent.VK_F), "fr"));
                actions.add(new LanguageChoice(
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("japanese")
                                                + " (日本語)",
                                null,
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("japaneselanguage")
                                                + " (日本語)",
                                Integer.valueOf(KeyEvent.VK_J), "jp"));
                actions.add(new LanguageChoice(
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("cantonese")
                                                + " (廣東話)",
                                null,
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("cantoneselanguage")
                                                + " (廣東話語言)",
                                Integer.valueOf(KeyEvent.VK_C), "zh"));
        }

        /**
         * <p>
         * Create a menu contianing the list of Language actions.
         * </p>
         * 
         * @return The Language menu UI element.
         */
        public JMenu createMenu() {
                JMenu languageMenu = new JMenu(
                                ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle")
                                                .getString("language"));

                for (Action action : actions) {
                        languageMenu.add(new JMenuItem(action));
                }

                return languageMenu;
        }

        /**
         * <p>
         * Action to choose a language.
         * </p>
         * 
         */
        public class LanguageChoice extends ImageAction {
                /** A stored value of the selected language */
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
                        Locale.setDefault(new Locale(language));
                        try (BufferedWriter writer = new BufferedWriter(
                                        new FileWriter("src/cosc202/andie/LanguageResources/language_pref.txt"))) {
                                try {
                                        writer.write(language);
                                } catch (IOException e1) {
                                        e1.printStackTrace();
                                }

                                try {
                                        writer.close();
                                } catch (IOException e1) {
                                        e1.printStackTrace();
                                }
                        } catch (IOException e1) {
                                e1.printStackTrace();
                        }

                        Andie.createMenuBar();
                }

        }
}
