package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * <p>
 * Actions provided by the theme menu
 * </p>
 * 
 * The Theme menu contains actions that change the 'theme' of Andie from light
 * mode to dark mode
 * application.
 * These actions do not affect the contents of the imported image, just the rest
 * of the
 * program.
 * 
 * <p>
 * exit
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Shayna Ludwig
 * @version 1.0
 */
public class ThemeActions {

    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of theme menu actions.
     * </p>
     */
    public ThemeActions() {
        actions = new ArrayList<Action>();
        actions.add(new LightModeAction(bundle.getString("lightMode"), null, bundle.getString("lightMode"),
                Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new DarkModeAction(bundle.getString("darkMode"), null, bundle.getString("darkMode"),
                Integer.valueOf(KeyEvent.VK_D)));
    }

    /**
     * <p>
     * Create a menu containing the list of theme actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu themeMenu = new JMenu(bundle.getString("theme"));

        for (Action action : actions) {
            JMenuItem item;
            if (action instanceof LightModeAction || action instanceof DarkModeAction) {
                item = Tools.createMenuItem(action, false, true);
            } else {
                item = Tools.createMenuItem(action, false, false);
            }
            themeMenu.add(item);
        }

        return themeMenu;
    }

    /**
     * <p>
     * Action to change theme to dark mode
     * </p>
     * </p>
     * 
     * @param name     The name of the action (ignored if null).
     * @param icon     An icon to use to represent the action (ignored if null).
     * @param desc     A brief description of the action (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
     */
    public class DarkModeAction extends ImageAction {

        public DarkModeAction(String name, ImageIcon icon, String tooltip, Integer mnemonic) {
            super(name, icon, tooltip, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if (ViewActions.CropAction.isCropping) {
                ViewActions.CropAction.stopCropping();
            }
            if (InsertActions.DrawShapesAction.isDrawing) {
                InsertActions.DrawShapesAction.stopDrawing();
            }

            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(Andie.frame);
                Andie.removeToolBar();
                Andie.createToolBar();
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("src/cosc202/andie/theme_pref.txt"));
                writer.write("dark");
                writer.close();
            } catch (Exception ex) {
                System.err.println("Failed to initialize FlatLaf");
            }
        }
    }

    /**
     * <p>
     * Action to change theme to light mode
     * </p>
     * 
     * @param name     The name of the action (ignored if null).
     * @param icon     An icon to use to represent the action (ignored if null).
     * @param desc     A brief description of the action (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
     */
    public class LightModeAction extends ImageAction {

        public LightModeAction(String name, ImageIcon icon, String tooltip, Integer mnemonic) {
            super(name, icon, tooltip, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if (ViewActions.CropAction.isCropping) {
                ViewActions.CropAction.stopCropping();
            }
            if (InsertActions.DrawShapesAction.isDrawing) {
                InsertActions.DrawShapesAction.stopDrawing();
            }
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(Andie.frame);
                Andie.removeToolBar();
                Andie.createToolBar();
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("src/cosc202/andie/theme_pref.txt"));
                writer.write("light");
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
