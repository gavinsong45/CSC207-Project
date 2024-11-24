package use_case.translation;

/**
 * The output boundary for the select_language usecase.
 */
public interface TranslationOutputBoundary {
    /**
     * Prepares the success view for the select_language usecase.
     * @param translationOutputData the output data
     */
    void prepareSuccessView(TranslationOutputData translationOutputData);

    /**
     * Prepares the failed view for the select_language usecase.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
