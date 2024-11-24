package interface_adapter.translation;

import use_case.translation.TranslationOutputBoundary;
import use_case.translation.TranslationOutputData;

public class TranslationPresenter implements TranslationOutputBoundary {

    private final TranslationViewModel translationViewModel;

    public TranslationPresenter(TranslationViewModel translationViewModel) {
        this.translationViewModel = translationViewModel;
        // In the future, if there needs to be a pop-up page for the settings,
        // There might be a viewManagerModel added here. For now, it's just the use case view model
    }

    @Override
    public void prepareSuccessView (TranslationOutputData translatedText) {
    // Implement this so that the presenter successfully presents the translation
    // Switches to the success view - Display the translated
        final TranslationState translationState = this.translationViewModel.getState();
        // This part displays the translation on the UI
        translationState.setTranslation(translatedText.getTranslatedText());
        // Should I fire a property change here?
        translationViewModel.setState(translationState);
        this.translationViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // Leave this part empty for now
    }
}
