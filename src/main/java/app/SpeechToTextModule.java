package app;

import frameworks_and_drivers.speech_to_text.GoogleSpeechRecognizer;
import interface_adapter.ViewInterface;
import interface_adapter.speech_to_text.SpeechToTextController;
import interface_adapter.speech_to_text.SpeechToTextPresenter;
import use_case.speech_to_text.SpeechToTextInputBoundary;
import use_case.speech_to_text.SpeechToTextInteractor;
import use_case.speech_to_text.SpeechToTextOutputBoundary;

/**
 * This module initializes the Speech-to-Text functionality, including the controller and related components.
 */
public class SpeechToTextModule {

    /**
     * Initializes the Speech-to-Text module, including the controller and services.
     *
     * @param gestureBridgeView the main application view for communication with presenters.
     * @return the SpeechToTextController instance.
     */
    public static SpeechToTextController initialize(ViewInterface gestureBridgeView) {
        final GoogleSpeechRecognizer speechRecognizer = new GoogleSpeechRecognizer();
        final SpeechToTextOutputBoundary outputBoundary = new SpeechToTextPresenter(gestureBridgeView);
        final SpeechToTextInputBoundary interactor = new SpeechToTextInteractor(speechRecognizer, outputBoundary);
        return new SpeechToTextController(interactor);
    }
}
