package cosc202.andie;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
/**
 * <p>
 * Actions proided by the theme menu 
 * </p>
 * 
 * The Theme menu contains actions that change the 'theme' of Andie from light mode to dark mode
 * application.
 * These actions do not affect the contents of the imported image, just the rest of the
 * program. 
 * 
 * <p>
 * exit
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 *@author Shayna Ludwig
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
            JMenuItem item = Tools.createMenuItem(action, false, false);
            themeMenu.add(item);
        }

        return themeMenu;
    }

    /**
     * <p>
     * Action to change theme to dark mode
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class DarkModeAction extends ImageAction{
        
        public DarkModeAction(String name, ImageIcon icon, String tooltip, Integer mnemonic) {
            super(name, icon, tooltip, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e) {
          
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(Andie.frame);
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
     * 
     */
    public class LightModeAction extends ImageAction{
        
        public LightModeAction(String name, ImageIcon icon, String tooltip, Integer mnemonic) {
            super(name, icon, tooltip, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e) {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(Andie.frame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}




    




