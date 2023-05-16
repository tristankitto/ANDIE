package cosc202.andie;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import com.formdev.flatlaf.FlatLightLaf;

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
 * @author Steven Mills and Tristan Kitto
 * @version 1.0
 */
public class Andie {
    protected static JMenuBar menuBar;
    protected static JFrame frame;
    protected static JToolBar toolBar;

    /** Boolean value to keep track of if an image has unsaved changes or not */
    public static boolean saved = true;

    /** String to store the file path of an image when it is first opened */
    public static String imageFilepath;

    public static int x;
    public static int y;
    public static int x2;
    public static int y2;
    public static boolean repaint;

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

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        createMenuBar();
        createToolBar();
        frame.pack();
        imagePanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                x = me.getX() - 10;
                y = me.getY() - 10;
                System.out.println(x + " " + y);
            }

            public void mouseReleased(MouseEvent me) {
                x2 = me.getX() - 10;
                y2 = me.getY() - 10;
                System.out.println(x2 + " " + y2);
                imagePanel.repaint();
            }
        });

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

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Settings menu which contains the menus for theme and language
        JMenu settingsMenu = new JMenu(bundle.getString("settings"));
        ThemeActions themeActions = new ThemeActions();
        LanguageActions languageActions = new LanguageActions();
        settingsMenu.add(themeActions.createMenu());
        settingsMenu.add(languageActions.createMenu());
        menuBar.add(settingsMenu);

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
     * {@code ImageAction}s grouped by their general purpose into menus.
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

        ImageIcon open = new ImageIcon(Andie.class.getClassLoader().getResource("folder-open.png"));
        JButton button1 = new JButton();
        button1.setIcon(open);
        button1.addActionListener(fileActions.createMenu().getItem(0).getAction());
        button1.setToolTipText(bundle.getString("open"));
        toolBar.add(button1);

        ImageIcon save = new ImageIcon(Andie.class.getClassLoader().getResource("disk.png"));
        JButton button2 = new JButton();
        button2.setIcon(save);
        button2.addActionListener(fileActions.createMenu().getItem(1).getAction());
        button2.setToolTipText(bundle.getString("save"));
        toolBar.add(button2);

        ImageIcon undo = new ImageIcon(Andie.class.getClassLoader().getResource("undo-alt.png"));
        JButton button3 = new JButton();
        button3.setIcon(undo);
        button3.addActionListener(editActions.createMenu().getItem(0).getAction());
        button3.setToolTipText(bundle.getString("undo"));
        toolBar.add(button3);

        ImageIcon redo = new ImageIcon(Andie.class.getClassLoader().getResource("redo-alt.png"));
        JButton button4 = new JButton();
        button4.setIcon(redo);
        button4.addActionListener(editActions.createMenu().getItem(1).getAction());
        button4.setToolTipText(bundle.getString("redo"));
        toolBar.add(button4);

        ImageIcon zoom = new ImageIcon(Andie.class.getClassLoader().getResource("search.png"));
        JButton button5 = new JButton();
        button5.setIcon(zoom);
        button5.addActionListener(viewActions.createMenu().getItem(0).getAction());
        button5.setToolTipText(bundle.getString("changeZoom"));
        toolBar.add(button5);

        ImageIcon exit = new ImageIcon(Andie.class.getClassLoader().getResource("exit.png"));
        JButton button6 = new JButton();
        button6.setIcon(exit);
        button6.addActionListener(fileActions.createMenu().getItem(4).getAction());
        button6.setToolTipText(bundle.getString("exit"));
        toolBar.add(button6);

        ImageIcon language = new ImageIcon(Andie.class.getClassLoader().getResource("language_icon.png"));
        JButton button7 = new JButton();
        button7.setIcon(language);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languagePopupMenu(button7);
            }
        });
        button7.setToolTipText(bundle.getString("language"));
        toolBar.add(button7);

        /**
         * ImageIcon crop= new
         * ImageIcon(Andie.class.getClassLoader().getResource("crop.png"));
         * JButton button7 = new JButton();
         * button7.setIcon(crop);
         * button7.addActionListener(///.createMenu().getItem(4).getAction());
         * toolBar.add(button7);
         */

        frame.add(toolBar, BorderLayout.PAGE_START);
        frame.setVisible(true);
    }

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
