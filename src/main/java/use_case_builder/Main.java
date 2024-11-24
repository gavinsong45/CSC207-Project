package use_case_builder;

import javax.swing.*;

/**
 * Putting the test pieces together
 */
public class Main {
    public static void main(String[] args) {
        final Builder testBuilder = new Builder();
        final JFrame test = testBuilder.addSelectLanguageView().addSelectLanguageUseCase().build();
        test.setSize(300,300);
        test.setVisible(true);
        // Note for translation: the translation API seems to have trouble generating punctuation marks
        // e.g. "I'm" would be I - random nonsense in between - m, though I think it might be the textField's problem
        // Another problem is targetLanguage requires language codes. For a limited number of languages,
        // We can put an if-statement check, but for a much longer language list in the future, we might need map/file

        // Note for use case injection:
        // The translation input currently takes in a string that is hand-typed, if to link it with
        // the ASL recognition use case, the output of ASL use case would be the input of translation use case
        // need to - make sure the output and input object type match up
        // See how to update/modify view model and view to listen for separate actions when sharing a text box
        // The same case applies to the output of translation use case being used as input for text-to-speech use case

        // Note for testing:
        // Need to write more unit tests
        // Need to think what are the scenarios that this use case fails, and what error messages would it throw out
    }
}
