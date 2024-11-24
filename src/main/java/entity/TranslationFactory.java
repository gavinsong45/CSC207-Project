package entity;
import java.util.List;

/**
 * A factory for creating translations.
 */
public interface TranslationFactory {
    /**
     * Creates a new translation object
     * @param text The text that needs to be translated
     * @param targetLanguage the desired language to be translated into
     * @param translationHistory the previous translated texts
     * @return a new Translation object
     */
    Translation create(String text, String targetLanguage, List<String> translationHistory);
}
