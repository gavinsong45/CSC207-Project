package use_case.translation;

/**
 * Input boundaries for actions related to select_language.
 */
public interface TranslationInputBoundary {

    /**
     * Executed the select_language usecase.
     * @param translationInputData the input data
     */
    void execute(TranslationInputData translationInputData);

}
