package app;

import data_access.SignLanguageTranslationDataAccessObject;
import frameworks_and_drivers.speech_to_text.GoogleSpeechRecognizer;
import frameworks_and_drivers.text_to_speech.AudioPlayer;
import frameworks_and_drivers.text_to_speech.GoogleTextToSpeechGateway;
import frameworks_and_drivers.text_to_speech.TextToSpeechInterface;
import interface_adapter.sign_language_recognition.SignLanguageController;
import interface_adapter.sign_language_recognition.SignLanguagePresenter;
import interface_adapter.sign_language_translation.SignLanguageTranslationController;
import interface_adapter.sign_language_translation.SignLanguageTranslationPresenter;
import interface_adapter.speech_to_text.SpeechToTextController;
import interface_adapter.speech_to_text.SpeechToTextPresenter;
import interface_adapter.text_to_speech.TextToSpeechController;
import interface_adapter.text_to_speech.TextToSpeechPresenter;
import use_case.sign_language_recognition.PredictionService;
import use_case.sign_language_recognition.SignLanguageRecognitionInteractor;
import use_case.sign_language_translation.SignLanguageTranslationInputBoundary;
import use_case.sign_language_translation.SignLanguageTranslationInteractor;
import use_case.sign_language_translation.SignLanguageTranslationOutputBoundary;
import use_case.speech_to_text.SpeechToTextInteractor;
import use_case.text_to_speech.TextToSpeechInputBoundary;
import use_case.text_to_speech.TextToSpeechInteractor;
import use_case.text_to_speech.TextToSpeechOutputBoundary;
import view.GestureBridgeView;

import java.io.IOException;

/**
 * Entry point for the Gesture Bridge application.
 * Initializes all components and starts the application workflow.
 */
public class RunGestureBridgeApp {

    /**
     * Main method to initialize and start the Gesture Bridge application.
     *
     * @param args command-line arguments (not used).
     * @throws IOException if there is an I/O error during initialization.
     * @throws InterruptedException if interrupted during execution.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // Initialize the view
        GestureBridgeView gestureBridgeView = initializeView();

        // Initialize components for various modules
        TextToSpeechController textToSpeechController = initializeTextToSpeech(gestureBridgeView);
        SpeechToTextController speechToTextController = initializeSpeechToText(gestureBridgeView);
        SignLanguageTranslationController translationController = initializeTranslation(gestureBridgeView);
        SignLanguageController signLanguageController = initializeSignLanguageRecognition(gestureBridgeView);

        // Set controllers in the view
        setControllers(gestureBridgeView, textToSpeechController, speechToTextController, translationController);

        // Start Sign Language Recognition
        signLanguageController.startRecognition();
    }

    /**
     * Initializes the main view for the application.
     *
     * @return the initialized GestureBridgeView instance.
     */
    private static GestureBridgeView initializeView() {
        return new GestureBridgeView();
    }

    /**
     * Initializes the Text-to-Speech module.
     *
     * @param gestureBridgeView the main application view for communication with presenters.
     * @return the TextToSpeechController instance.
     */
    private static TextToSpeechController initializeTextToSpeech(GestureBridgeView gestureBridgeView) {
        AudioPlayer audioPlayer = new AudioPlayer();
        TextToSpeechInterface textToSpeechService = new GoogleTextToSpeechGateway();
        TextToSpeechOutputBoundary outputBoundary = new TextToSpeechPresenter(audioPlayer);
        TextToSpeechInputBoundary interactor = new TextToSpeechInteractor(outputBoundary, textToSpeechService);
        return new TextToSpeechController(interactor);
    }

    /**
     * Initializes the Speech-to-Text module.
     *
     * @param gestureBridgeView the main application view for communication with presenters.
     * @return the SpeechToTextController instance.
     */
    private static SpeechToTextController initializeSpeechToText(GestureBridgeView gestureBridgeView) {
        GoogleSpeechRecognizer speechRecognizer = new GoogleSpeechRecognizer();
        SpeechToTextPresenter presenter = new SpeechToTextPresenter(gestureBridgeView);
        SpeechToTextInteractor interactor = new SpeechToTextInteractor(speechRecognizer, presenter);
        return new SpeechToTextController(interactor);
    }

    /**
     * Initializes the Sign Language Translation module.
     *
     * @param gestureBridgeView the main application view for communication with presenters.
     * @return the SignLanguageTranslationController instance.
     */
    private static SignLanguageTranslationController initializeTranslation(GestureBridgeView gestureBridgeView) {
        SignLanguageTranslationDataAccessObject dataAccessObject = new SignLanguageTranslationDataAccessObject();
        SignLanguageTranslationOutputBoundary outputBoundary = new SignLanguageTranslationPresenter(gestureBridgeView);
        SignLanguageTranslationInputBoundary interactor = new SignLanguageTranslationInteractor(dataAccessObject, outputBoundary);
        return new SignLanguageTranslationController(interactor);
    }

    /**
     * Initializes the Sign Language Recognition module.
     *
     * @param gestureBridgeView the main application view for communication with presenters.
     * @return the SignLanguageController instance.
     */
    private static SignLanguageController initializeSignLanguageRecognition(GestureBridgeView gestureBridgeView) {
        String pythonInterpreter = "python3";
        String scriptPath = "src/main/python/hand_gesture_recognition.py";
        PredictionService predictor = new PredictionService(pythonInterpreter, scriptPath);
        SignLanguagePresenter presenter = new SignLanguagePresenter(gestureBridgeView);
        SignLanguageRecognitionInteractor interactor = new SignLanguageRecognitionInteractor(predictor, presenter);
        return new SignLanguageController(interactor);
    }

    /**
     * Sets the controllers in the main application view.
     *
     * @param view the main application view.
     * @param textToSpeechController the controller for the Text-to-Speech module.
     * @param speechToTextController the controller for the Speech-to-Text module.
     * @param translationController the controller for the Sign Language Translation module.
     */
    private static void setControllers(
            GestureBridgeView view,
            TextToSpeechController textToSpeechController,
            SpeechToTextController speechToTextController,
            SignLanguageTranslationController translationController
    ) {
        view.setTextToSpeechController(textToSpeechController);
        view.setSpeechToTextController(speechToTextController);
        view.setTranslationController(translationController);
    }
}
