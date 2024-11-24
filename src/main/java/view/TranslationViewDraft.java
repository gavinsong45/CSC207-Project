package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TranslationViewDraft {
    // add field viewName that corresponds with the name in the view model
    // This viewName would be used by the ViewManager if there needs to be another pop up
    // of the view after a property change event
    public static void main(String[]args) {
        SwingUtilities.invokeLater(() -> {
            // Create the language selection part
            JPanel languagePanel = new JPanel();
            //Try out the dropdown bar
            // Create the list of language options
            String[] languages = {"English", "French", "Spanish", "Mandarin"};
            JComboBox<String> languageBox = new JComboBox<>(languages);
            languageBox.setSelectedIndex(0);
            languageBox.setEditable(false);

            languageBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Update the selected labels
                    String selectedLanguage = (String) languageBox.getSelectedItem();
                    languageBox.setSelectedItem(selectedLanguage);
                }
            });

            JLabel languageLabel = new JLabel("Select Language");

            JTextField inputField = new JTextField();
            inputField.setSize(30,20);

            JTextField translationField = new JTextField();
            translationField.setSize(30,20);

            JButton translateButton = new JButton("Translate");

            // Create the main panel
            // JPanel mainPanel = new JPanel();

            // Create testing frame
            JFrame frame = new JFrame("Settings");
            frame.setContentPane(languagePanel);
            frame.setSize(300,300);
            frame.setLayout(new BoxLayout(languagePanel, BoxLayout.Y_AXIS));

            languagePanel.add(languageLabel);
            languagePanel.add(languageBox);
            languagePanel.add(inputField);
            languagePanel.add(translationField);
            languagePanel.add(translateButton);

            // This area needs another text field to take in the sign language transcriptions
            // Remember to change the names of entities & package names to make them make sense
            // e.g. select_language -> lTranslate (as in language translate)



            // frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // Try out JSlider
        });
    }
}
