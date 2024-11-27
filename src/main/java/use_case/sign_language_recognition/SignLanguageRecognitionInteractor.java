package use_case.sign_language_recognition;

import interface_adapter.sign_language_recognition.PredictionInterface;

import java.io.IOException;

/**
 * Use case for starting the recognition of sign language gestures.
 */
public class SignLanguageRecognitionInteractor {
    private final PredictionInterface predictor;
//    private final SignLanguagePresenter presenter;
    private final SignLanguageRecognitionOutputBoundary outputBoundary;


    /**
     * Constructor for the SignLanguageRecognitionUseCase.
     *
     * @param predictor the PredictionInterface that will perform the recognition
     * @param outputBoundary the Output Boundary to update the presenter/view
     */
    public SignLanguageRecognitionInteractor(PredictionInterface predictor, SignLanguageRecognitionOutputBoundary outputBoundary) {
        this.predictor = predictor;
        this.outputBoundary = outputBoundary;
//        this.presenter = presenter;
    }

    /**
     * Starts the recognition process.
     *
     * @throws IOException if there is an error with the input/output operation
     * @throws InterruptedException if the thread is interrupted during the operation
     */
    public void startRecognition() throws IOException, InterruptedException {
        predictor.startRecognition(this::predictionToPresenter);  // Delegate to PredictionService
    }

    /**
     * Method to update the view with the prediction result.
     * This method will be called by the Use Case when a prediction is made.
     *
     * @param prediction The prediction result from the recognition process.
     */
    private void predictionToPresenter(String prediction) {
        SignLanguageRecognitionOutputData outputData = new SignLanguageRecognitionOutputData(prediction);
        outputBoundary.updateView(outputData);
    }
}


