package use_case.translation;

public class TranslationOutputData {

    private final String translatedText;
    private final boolean useCaseFailed;

    public TranslationOutputData(String translatedText, boolean useCaseFailed) {
        this.translatedText = translatedText;
        this.useCaseFailed = useCaseFailed;
        // In the future, if the output text is long (e.g. a conversation), might want to
        // turn this into a file
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
    // Is this the correct way to set up a failing case?
    // Is the message description detailed enough?
}
