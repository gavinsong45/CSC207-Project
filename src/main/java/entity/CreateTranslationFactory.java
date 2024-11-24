package entity;

import java.util.List;

/**
 * Factory for creating Translation Objects.
 */
public class CreateTranslationFactory implements TranslationFactory {

    @Override
    public Translation create(String text, String targetLanguage, List<String> translationHistory) {
        return new Translation(text, targetLanguage, translationHistory);
    }
}
