package interface_adapter.sign_language_translation;

import use_case.sign_language_translation.SignLanguageTranslationOutputBoundary;
import use_case.sign_language_translation.SignLanguageTranslationOutputData;
import view.GestureBridgeView;

/**
 * Presenter for handling the presentation logic of the sign language signLanguageTranslationDisplay use case.
 * Implements the Output Boundary to receive and process the signLanguageTranslationDisplay results.
 */
public class SignLanguageTranslationPresenter implements SignLanguageTranslationOutputBoundary {

    private final GestureBridgeView view;

    /**
     * Constructor for the TranslationPresenter.
     *
     * @param view the view that will display the signLanguageTranslationDisplay results
     */
    public SignLanguageTranslationPresenter(GestureBridgeView view) {
        this.view = view;
    }

    /**
     * Prepare and present the success view for signLanguageTranslationDisplay results.
     * Updates the SignLanguageView with the translated text.
     *
     * @param outputData the data containing the signLanguageTranslationDisplay result
     */
    @Override
    public void prepareSuccessView(SignLanguageTranslationOutputData outputData) {
        String translatedText = outputData.getTranslatedText();
        view.signLanguageTranslationDisplay(translatedText);
    }

    /**
     * Prepare and present the failure view in case of an error during signLanguageTranslationDisplay.
     * Updates the SignLanguageView with an error message.
     *
     * @param error the error message to display
     */
    @Override
    public void prepareFailView(String error) {
        view.signLanguageTranslationDisplay("Error: " + error);
    }
}

