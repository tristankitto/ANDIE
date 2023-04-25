package cosc202.andie;

import java.util.*;
import java.awt.Toolkit;
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
public class FileActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {
        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction(bundle.getString("open"), null, bundle.getString("openAFile"),
                Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction(bundle.getString("save"), null, bundle.getString("saveTheFile"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction(bundle.getString("saveAs"), null, bundle.getString("saveACopy"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new imageExportAction(bundle.getString("export"), null, bundle.getString("exportImage"),
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new FileExitAction(bundle.getString("exit"), null, bundle.getString("exitTheProgram"),
                Integer.valueOf(KeyEvent.VK_Q)));
    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("file"));

        for (Action action : actions) {
            JMenuItem item = new JMenuItem(action);
            if (action.getValue(Action.MNEMONIC_KEY) != null) {
                if (action instanceof FileSaveAsAction) {
                    KeyStroke key = KeyStroke.getKeyStroke(
                            (char) ((Integer) action.getValue(Action.MNEMONIC_KEY)).intValue(),
                            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | KeyEvent.SHIFT_DOWN_MASK);
                    item.setAccelerator(key);
                } else {
                    KeyStroke key = KeyStroke.getKeyStroke(
                            (char) ((Integer) action.getValue(Action.MNEMONIC_KEY)).intValue(),
                            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
                    item.setAccelerator(key);
                }
            }
            fileMenu.add(item);
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
    public class FileOpenAction extends ImageAction {

        boolean shift;

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
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            if (Andie.saved == true) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(target);

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        Andie.imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        EditableImage.clearStacks();
                        target.getImage().open(Andie.imageFilepath);
                    } catch (Exception ex) {
                        Popup.errorMessage(ex, "fileOpenError");
                    }
                }

                target.repaint();
                target.getParent().revalidate();
            } else {
                Object[] options = { bundle.getString("yes"),
                        bundle.getString("no"),
                        bundle.getString("cancel") };
                int n = JOptionPane.showOptionDialog(null, bundle.getString("unsavedImageQuestion"),
                        bundle.getString("unsavedImage"), JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options, options[2]);
                if (n == 0) {

                    try {
                        target.getImage().save();
                    } catch (Exception e1) {
                        Popup.errorMessage(e1, "fileOpenError");
                    }
                    Andie.saved = true;
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(target);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            Andie.imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                            EditableImage.clearStacks();
                            target.getImage().open(Andie.imageFilepath);
                        } catch (Exception ex) {
                            Popup.errorMessage(ex, "fileOpenError");
                        }
                    }
                    target.repaint();
                    target.getParent().revalidate();
                }
                if (n == 1) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(target);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            Andie.imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                            EditableImage.clearStacks();
                            target.getImage().open(Andie.imageFilepath);
                        } catch (Exception ex) {
                            Popup.errorMessage(ex, "fileOpenError");
                        }
                    }

                    target.repaint();
                    target.getParent().revalidate();
                    Andie.saved = true;
                }
            }
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();
            } catch (Exception ex) {
                Popup.errorMessage(ex, "fileSaveError");
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
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
                    target.getImage().saveAs(imageFilepath);
                } catch (Exception ex) {
                    Popup.errorMessage(ex, "fileSaveError");
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * It will prompt the user to save if their image is unsaved.
     * </p>
     */
    public class FileExitAction extends ImageAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (Andie.saved == true) {
                System.exit(0);
            } else {
                Object[] options = { bundle.getString("yes"),
                        bundle.getString("no"),
                        bundle.getString("cancel") };
                int n = JOptionPane.showOptionDialog(null, bundle.getString("unsavedImageQuestion"),
                        bundle.getString("unsavedImage"), JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options, options[2]);
                if (n == 0) {
                    try {
                        target.getImage().save();
                        System.exit(0);
                    } catch (Exception e1) {
                        Popup.errorMessage(e1, "fileSaveError");
                    }
                }
                if (n == 1) {
                    System.exit(0);
                }
            }
        }

    }

    /**
     * <p>
     * Action to save a copy of the edited image to a new file location.
     * </p>
     * 
     * @see EditableImage#exportImage(String)
     */
    public class imageExportAction extends ImageAction {

        /**
         * <p>
         * Create a new file-Export action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        imageExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the export action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the imageExportAction is triggered.
         * It prompts the user to select a file and saves a copy of the edited image to
         * it.
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
                    String extension = Andie.imageFilepath.substring(Andie.imageFilepath.lastIndexOf(".") + 1).trim();
                    File file = new File(imageFilepath + "." + extension);
                    if (file.exists()) {
                        Object[] options = { bundle.getString("yes"), bundle.getString("cancel") };
                        int n = JOptionPane.showOptionDialog(null, bundle.getString("fileAlreadyExistsQuestion"),
                                bundle.getString("fileAlreadyExists"), JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (n == 0) {
                            target.getImage().exportImage(imageFilepath);
                        }
                    } else {
                        target.getImage().exportImage(imageFilepath);
                    }
                } catch (Exception ex) {
                    Popup.errorMessage(ex, "fileUnopenedError");
                }
            }
        }
    }

}
