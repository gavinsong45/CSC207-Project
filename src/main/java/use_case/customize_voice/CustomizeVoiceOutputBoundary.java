package use_case.customize_voice;

/**
 * The output boundary for the Customize Voice Use Case.
 */
public interface CustomizeVoiceOutputBoundary {
    /**
     * Prepares the success view for the Customize Voice Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CustomizeVoiceOutputData outputData);

    /**
     * Prepares the failure view for the Customize Voice Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
