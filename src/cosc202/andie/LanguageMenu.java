package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class LanguageMenu {
    private JMenuBar menuBar;
    private JMenu languageMenu;
    private JMenuItem englishItem;
    private JMenuItem spanishItem;
    private JLabel label;

    public LanguageMenu() {
        menuBar = new JMenuBar();
        languageMenu = new JMenu("Language");

        englishItem = new JMenuItem("English");
        englishItem.addActionListener(new LanguageActionListener(Locale.ENGLISH));

        spanishItem = new JMenuItem("Spanish");
        spanishItem.addActionListener(new LanguageActionListener(new Locale("es", "ES")));

        languageMenu.add(englishItem);
        languageMenu.add(spanishItem);
        menuBar.add(languageMenu);

        label = new JLabel("Hello world!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        JFrame frame = new JFrame();
        frame.setJMenuBar(menuBar);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    private class LanguageActionListener implements ActionListener {
        private Locale locale;

        public LanguageActionListener(Locale locale) {
            this.locale = locale;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Set the locale to the selected language
            Locale.setDefault(locale);

            // Update the text of the UI elements
            englishItem.setText(ResourceBundle.getBundle("cosc202.andie.LanguageMenuBundle").getString("english"));
            spanishItem.setText(ResourceBundle.getBundle("cosc202.andie.LanguageMenuBundle").getString("spanish"));
            label.setText(ResourceBundle.getBundle("cosc202.andie.LanguageMenuBundle").getString("message"));
        }
    }

    public static void main(String[] args) {
        new LanguageMenu();
    }
}

