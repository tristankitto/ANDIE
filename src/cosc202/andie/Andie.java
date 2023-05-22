package cosc202.andie;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import com.formdev.flatlaf.FlatLightLaf;
import cosc202.andie.FileActions.FileExitAction;

import javax.swing.JFrame;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
 * </p>
 * 
 * <p>
 * exit
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * Andie uses FlatLaf version 3.1.1 by FormDev Software GmbH
 * https://www.formdev.com/flatlaf/
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Andie {
    protected static JMenuBar menuBar;
    protected static JFrame frame;
    protected static JToolBar toolBar;
    protected static ImagePanel imagePanel;

    /** Boolean value to keep track of if an image has unsaved changes or not */
    public static boolean saved = true;

    /** String to store the file path of an image when it is first opened */
    public static String imageFilepath;

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface with an active image (an
     * {@code ImagePanel}).
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {
        File file = new File("src/cosc202/andie/LanguageResources/language_pref.txt");
        Scanner scanner = new Scanner(file);
        Locale.setDefault(new Locale(scanner.nextLine()));
        scanner.close();
        // Set up the main GUI frame
        FlatLightLaf.setup();
        frame = new JFrame("ANDIE");

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icons/icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FileActions fileActions = new FileActions();
                FileExitAction fileExitAction = fileActions.new FileExitAction("Exit", null, "Exit the application",
                        null);
                fileExitAction.actionPerformed(null);
            }
        });

        // The main content area is an ImagePanel
        imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        createMenuBar();
        createToolBar();
        frame.pack();

    }

    /**
     * <p>
     * Creates the Menu bar and menu items in the GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up various menus which can be used to trigger operations to
     * load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     */
    public static void createMenuBar() {
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");
        frame.setJMenuBar(null);
        // Add in menus for various types of action the user may perform.
        menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        InsertActions insertActions = new InsertActions();
        menuBar.add(insertActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        JMenu filterMenu = new FilterActions().createMenu();
        EmbossActions embossActions = new EmbossActions();
        filterMenu.add(embossActions.createMenu());
        menuBar.add(filterMenu);

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Settings menu which contains the menus for theme and language
        JMenu settingsMenu = new JMenu(bundle.getString("settings"));

        LanguageActions languageActions = new LanguageActions();
        ThemeActions themeActions = new ThemeActions();
        settingsMenu.add(themeActions.createMenu());
        settingsMenu.add(languageActions.createMenu());
        menuBar.add(settingsMenu);

        ActionListener menuItemListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ViewActions.CropAction.isCropping) {
                    ViewActions.CropAction.stopCropping();
                }
                if (InsertActions.DrawShapesAction.isDrawing) {
                    InsertActions.DrawShapesAction.stopDrawing();
                }
            }
        };

        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            for (Component component : menuBar.getMenu(i).getMenuComponents()) {
                if (component instanceof JMenuItem) {
                    JMenuItem item = (JMenuItem) component;
                    item.addActionListener(menuItemListener);
                } else if (component instanceof JMenu) {
                    JMenu menu = (JMenu) component;
                    for (Component menuComponent : menu.getMenuComponents()) {
                        JMenuItem menuItem = (JMenuItem) menuComponent;
                        menuItem.addActionListener(menuItemListener);
                    }
                }
            }
        }

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    /**
     * <p>
     * Creates a Tool bar the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up a tool bar with commonly used operations such as
     * Open, save,
     * exit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s.
     * </p>
     * 
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * 
     */
    public static void createToolBar() {
        toolBar = new JToolBar();
        FileActions fileActions = new FileActions();
        EditActions editActions = new EditActions();
        ViewActions viewActions = new ViewActions();
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");

        LookAndFeel currentTheme = UIManager.getLookAndFeel();

        // select the appropriate icons for the current theme
        ImageIcon openIcon;
        ImageIcon saveIcon;
        ImageIcon undoIcon;
        ImageIcon redoIcon;
        ImageIcon cropIcon;
        ImageIcon zoomIcon;
        ImageIcon macroIcon;
        ImageIcon languageIcon;
        ImageIcon exitIcon;

        if (currentTheme.getName().equals("FlatLaf Light")) {
            openIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/folder-open.png"));
            saveIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/disk.png"));
            undoIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/undo-alt.png"));
            redoIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/redo-alt.png"));
            cropIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/tool-crop.png"));
            zoomIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/search.png"));
            macroIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/circle-video.png"));
            languageIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/language_icon.png"));
            exitIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/exit.png"));
        } else {
            openIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/folder-openINVERT.png"));
            saveIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/diskINVERT.png"));
            undoIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/undo-altINVERT.png"));
            redoIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/redo-altINVERT.png"));
            cropIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/tool-cropINVERT.png"));
            zoomIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/searchINVERT.png"));
            macroIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/circle-videoINVERT.png"));
            languageIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/language_iconINVERT.png"));
            exitIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/exitINVERT.png"));
        }

        JButton button1 = new JButton();
        button1.setIcon(openIcon);
        button1.addActionListener(fileActions.createMenu().getItem(0).getAction());
        button1.setToolTipText(bundle.getString("open"));
        toolBar.add(button1);

        JButton button2 = new JButton();
        button2.setIcon(saveIcon);
        button2.addActionListener(fileActions.createMenu().getItem(1).getAction());
        button2.setToolTipText(bundle.getString("save"));
        toolBar.add(button2);

        JButton button3 = new JButton();
        button3.setIcon(undoIcon);
        button3.addActionListener(editActions.createMenu().getItem(0).getAction());
        button3.setToolTipText(bundle.getString("undo"));
        toolBar.add(button3);

        JButton button4 = new JButton();
        button4.setIcon(redoIcon);
        button4.addActionListener(editActions.createMenu().getItem(1).getAction());
        button4.setToolTipText(bundle.getString("redo"));
        toolBar.add(button4);

        JButton button9 = new JButton();
        button9.setIcon(cropIcon);
        button9.addActionListener(viewActions.createMenu().getItem(8).getAction());
        button9.setToolTipText(bundle.getString("crop"));
        toolBar.add(button9);

        JButton button5 = new JButton();
        button5.setIcon(zoomIcon);
        button5.addActionListener(viewActions.createMenu().getItem(0).getAction());
        button5.setToolTipText(bundle.getString("changeZoom"));
        toolBar.add(button5);

        JButton button8 = new JButton();
        button8.setIcon(macroIcon);
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                macroPopupMenu(button8);
            }
        });
        button8.setToolTipText(bundle.getString("language"));
        toolBar.add(button8);

        JButton button7 = new JButton();
        button7.setIcon(languageIcon);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languagePopupMenu(button7);
            }
        });
        button7.setToolTipText(bundle.getString("language"));
        toolBar.add(button7);

        JButton button6 = new JButton();
        button6.setIcon(exitIcon);
        button6.addActionListener(fileActions.createMenu().getItem(8).getAction());
        button6.setToolTipText(bundle.getString("exit"));
        toolBar.add(button6);

        ActionListener toolbarItemListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ViewActions.CropAction.isCropping) {
                    ViewActions.CropAction.stopCropping();
                }
                if (InsertActions.DrawShapesAction.isDrawing) {
                    InsertActions.DrawShapesAction.stopDrawing();
                }
            }
        };

        for (Component component : toolBar.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(toolbarItemListener);
            } else if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                for (Component menuComponent : menu.getMenuComponents()) {
                    JMenuItem menuItem = (JMenuItem) menuComponent;
                    menuItem.addActionListener(toolbarItemListener);
                }
            }
        }

        frame.add(toolBar, BorderLayout.PAGE_START);
        frame.setVisible(true);
    }

    /**
     * <p>
     * Creates a popup menu for the language options so they can be displayed beside
     * their toolbar button.
     * </p>
     * 
     * @param button The toolbar button that will display the popup menu.
     */
    private static void languagePopupMenu(JButton button) {
        JPopupMenu popupMenu = new JPopupMenu();
        LanguageActions languageAction = new LanguageActions();
        for (Action language : languageAction.actions) {
            popupMenu.add(language);
        }
        // Show the popup menu relative to the button
        popupMenu.show(button, 0, button.getHeight());
    }

    /**
     * <p>
     * Creates a popup menu for the macri options so they can be displayed beside
     * their toolbar button.
     * </p>
     * 
     * @param button The toolbar button that will display the popup menu.
     */
    private static void macroPopupMenu(JButton button) {
        JPopupMenu popupMenu = new JPopupMenu();
        FileActions macroActions = new FileActions();
        popupMenu.add(macroActions.createMenu().getItem(4).getAction());
        popupMenu.add(macroActions.createMenu().getItem(5).getAction());
        popupMenu.add(macroActions.createMenu().getItem(6).getAction());
        popupMenu.add(macroActions.createMenu().getItem(7).getAction());
        // Show the popup menu relative to the button
        popupMenu.show(button, 0, button.getHeight());
    }

    /**
     * <p>
     * Removes a tool bar from the JFrame.
     * </p>
     * 
     * <p>
     * This method will remove the tool bar from ANDIE. The main purpose for this is
     * to allow for the toolbar to be refreshed when the language is changed.
     * </p>
     */
    public static void removeToolBar() {
        frame.remove(toolBar);
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    Tools.errorMessage(ex, "programLaunchError");
                    System.exit(1);
                }
            }
        });

    }
}
