package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.File;
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
public class MacroActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public MacroActions() {
        actions = new ArrayList<Action>();
        actions.add(new FileRecordMacroAction(bundle.getString("record"), null, bundle.getString("recordAMacro"),
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new FileExportMacroAction(bundle.getString("exportMacro"), null, bundle.getString("exportAMacro"),
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new FileApplyMacroAction(bundle.getString("applyMacro"), null, bundle.getString("applyAMacro"),
                Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new FileResetMacroAction(bundle.getString("resetMacro"), null, bundle.getString("resetAMacro"),
                Integer.valueOf(KeyEvent.VK_Q)));
    }

    /**
     * <p>
     * Create a menu containing the list of Macro actions.
     * </p>
     * 
     * @return The Macro menu UI element.
     */
    public JMenu createMenu() {
        JMenu macroMenu = new JMenu(bundle.getString("macro"));

        for (Action action : actions) {
            JMenuItem item = new JMenuItem();
            if (action instanceof FileRecordMacroAction || action instanceof FileExportMacroAction
                    || action instanceof FileApplyMacroAction || action instanceof FileResetMacroAction) {
                item = Tools.createMenuItem(action, true, true);
            } else {
                item = Tools.createMenuItem(action, false, false);
            }
            macroMenu.add(item);
        }

        return macroMenu;
    }

    /**
     * <p>
     * Action to start recording a macro.
     * </p>
     * 
     * @see EditableImage#recordMacro()
     */
    public class FileRecordMacroAction extends ImageAction {

        /**
         * <p>
         * Create a new file-record-macro action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileRecordMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-record-macro action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileRecordMacroAction is triggered.
         * It causes a recording symbol to appear and operations will be saved to a
         * stack.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().recordMacro();
            } catch (Exception ex) {
                Tools.errorMessage(ex, "fileRecordMacroError");
            }
        }
    }

    /**
     * <p>
     * Action to export a recorded macro.
     * </p>
     * 
     * @see EditableImage#exportMacro(String)
     */
    public class FileExportMacroAction extends ImageAction {

        /**
         * <p>
         * Create a new file-export-macro action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExportMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-export-macro action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExportMacroAction is triggered.
         * It prompts the user to select a file and saves the macro to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();

                    File file = new File(imageFilepath + ".ops");

                    if (file.exists()) {
                        Object[] options = { bundle.getString("yes"), bundle.getString("cancel") };
                        int n = JOptionPane.showOptionDialog(null, bundle.getString("fileAlreadyExistsQuestion"),
                                bundle.getString("fileAlreadyExists"), JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (n == 0) {
                            target.getImage().exportMacro(imageFilepath);
                        }
                    } else {
                        target.getImage().exportMacro(imageFilepath);
                    }
                } catch (Exception ex) {
                    Tools.errorMessage(ex, "fileSaveError");
                }
            }
        }
    }

    /**
     * <p>
     * Action to apply a macro.
     * </p>
     * 
     * @see EditableImage#applyMacro(String)
     */
    public class FileApplyMacroAction extends ImageAction {

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
        FileApplyMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * If a file is already open it will prompt the user to save first if
         * their image is unsaved.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String macroPath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().applyMacro(macroPath);
                } catch (Exception ex) {
                    Tools.errorMessage(ex, "fileOpenError");
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to reset a macro stack.
     * </p>
     * 
     * @see EditableImage#resetMacro()
     */
    public class FileResetMacroAction extends ImageAction {
        /**
         * <p>
         * Create a new reset macro action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileResetMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the reset macro action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileResetMacroAction is triggered.
         * It clears the macro stack, allowing the user to restart their macro from
         * scratch.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            Object[] options = { bundle.getString("yes"), bundle.getString("cancel") };
            int n = JOptionPane.showOptionDialog(null, bundle.getString("resetMacroQuestion"),
                    bundle.getString("resetMacro"), JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == 0) {
                target.getImage().resetMacro();
            }
        }

    }
}
