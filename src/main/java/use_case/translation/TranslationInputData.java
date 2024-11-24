package use_case.translation;

public class TranslationInputData {

    private final String language;
    private final String text;

    public TranslationInputData(String language, String signTranslation) {
        // The language takes in whatever is in the input data
        this.language = language;
        this.text = signTranslation;
    }

    public String getLanguage() {
        return language;
    }
    public String getText() {
        return text;
    }
}
