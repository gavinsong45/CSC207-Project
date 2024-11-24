package view;

import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationState;
import interface_adapter.translation.TranslationViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for translation of ASL text
 */
public class TranslationLanguageView extends JPanel implements ActionListener, PropertyChangeListener {
    // add field viewName that corresponds with the name in the view model
    // This viewName would be used by the ViewManager if there needs to be another pop up
    // of the view after a property change event
    private final String viewName = "translate";
    private final TranslationViewModel selectLanguageviewModel;

    // The drop-down menu
    private final JLabel languageLabel = new JLabel();
    // Would it be better if the ComboBox is implemented inside the class? - Just constructed this
    // inside the class

    // The sign language transcription (input part)
    private final JTextField inputField = new JTextField(); // Do I set the size here or later?

    // The translation output part
    private final JTextField translationField = new JTextField();

    // The translation button
    private final JButton translateButton;

    private TranslationController translationController;

    public TranslationLanguageView(TranslationViewModel selectLanguageviewModel) {
        this.selectLanguageviewModel = selectLanguageviewModel;
        // What does this step do?
        this.selectLanguageviewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.translateButton = new JButton("Translate");

        languageLabel.setText("Select Language");
        String[] languages = {"English", "French", "Spanish", "Mandarin"};
        final JComboBox<String> languageBox = new JComboBox<>(languages);
        languageBox.setSelectedIndex(0);
        languageBox.setEditable(false);
        // Should I add an actionListener for the languageBox?
        // not needed, comboBox can function without the actionListener in the draft

        // The state right now is only the output data state
        // The input data doesn't need a state, just use .getText() from the textfield and pass it into the controller
        // For the combobox, the .getSelectedItem() will do the trick

        translateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(translateButton)) {
                            String language = (String) languageBox.getSelectedItem();
                            String text = inputField.getText();
                            translationController.execute(language, text);
                        }
                    }
                }
        );
        // Finalizing the view
        inputField.setSize(30, 20);
        translationField.setSize(30, 20);

        this.add(languageLabel);
        this.add(languageBox);
        this.add(inputField);
        this.add(translationField);
        this.add(translateButton);
    }

    /**
     * React to a button click that results in event
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println(evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Sth. to do with the firepropertychange()
        final TranslationState state = (TranslationState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(TranslationState state) {
        // translationField.setText(state.getTranslation());
        inputField.setText(state.getTranslation());
        // Set it so that the input and output are in the same box - fits the UI and links to text-to-speech
    }

    public String getViewName() {
        return viewName;
    }

    public void setSelectLanguageController(TranslationController translationController) {
        this.translationController = translationController;
    }
}
