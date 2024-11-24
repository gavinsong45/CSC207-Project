package entity;

import java.util.List;

public class Translation {
    private final String text;
    // Is this the translated text or the original transcription of ASL? - For now assuming this is original transcription
    private String targetLanguage;
    private final List<String> translationHistory;

    Translation (String text, String targetLanguage, List<String> translationHistory) {
        this.text = text;
        this.targetLanguage = targetLanguage;
        this.translationHistory = translationHistory;
    }

    public List<String> getTranslationHistory() {
        return translationHistory;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public String getText() {
        return text;
    }
}
