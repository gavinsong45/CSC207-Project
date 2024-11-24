package interface_adapter.translation;

import use_case.translation.TranslationInputBoundary;
import use_case.translation.TranslationInputData;

/**
 * The controller for the select language use case.
 */
public class TranslationController {

    private final TranslationInputBoundary selectLanguageInteractor;

    public TranslationController(TranslationInputBoundary selectLanguageInteractor) {
        this.selectLanguageInteractor = selectLanguageInteractor;
    }

    /**
     * Executes the select language use case
     */
    public void execute(String language, String text) {
        final TranslationInputData translationInputData = new TranslationInputData(language, text);
        selectLanguageInteractor.execute(translationInputData);
    }
}
