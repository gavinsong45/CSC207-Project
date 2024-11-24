package use_case.translation;

/**
 * The select_language Interactor.
 */
public class TranslationInteractor implements TranslationInputBoundary {
    private final TranslationDataAccessInterface languageDataAccessObject;
    private final TranslationOutputBoundary selectLanguagePresenter;

    public TranslationInteractor(TranslationDataAccessInterface translationDataAccessInterface,
                                 TranslationOutputBoundary translationOutputBoundary) {
        this.languageDataAccessObject = translationDataAccessInterface;
        this.selectLanguagePresenter = translationOutputBoundary;
        // This implementation is temporarily not finished
    }

    @Override
    public void execute(TranslationInputData translationInputData) {
        // The interactions between the classes should be:
        // Takes in the input (language - Combobox + text - TextField)
        final String language = translationInputData.getLanguage();
        final String text = translationInputData.getText();
        // Import the DAO (which contains a method for API call), use getters to retrieve input data
        // Output the String of translated text
        final String translatedText = languageDataAccessObject.translate(language, text);
        final TranslationOutputData translationOutputData = new TranslationOutputData(translatedText, false);
        // Calls the presenter to update changes
        selectLanguagePresenter.prepareSuccessView(translationOutputData);
        // Throws the output data into the ViewModel
        // View updates the translation
    }
}
