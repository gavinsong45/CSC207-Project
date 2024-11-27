//package use_case.speech_to_text;
//
//import frameworks_and_drivers.SpeechRecognizer;
//import entity.AudioTranscription;
//
///**
// * Use case for processing speech input and converting it into text.
// * This class implements the {@link SpeechToTextInputBoundary} interface and is responsible for
// * converting audio data into a transcription using a speech recognition system.
// */
//public class SpeechToTextInteractor implements SpeechToTextInputBoundary {
//    /**
//     * The speech recognizer used to process the audio data.
//     */
//    private final SpeechRecognizer speechRecognizer;
//
//    /**
//     * Constructs a new instance of {@code SpeechToTextInteractor}.
//     *
//     * @param speechRecognizer The {@link SpeechRecognizer} responsible for recognizing speech
//     *                         from audio data.
//     */
//    public SpeechToTextInteractor(SpeechRecognizer speechRecognizer) {
//        this.speechRecognizer = speechRecognizer;
//    }
//
//    /**
//     * Processes the given audio data to produce a transcription.
//     *
//     * @param audioData the audio data in byte array format.
//     * @return an {@link AudioTranscription} object containing the transcribed text.
//     * @throws Exception if an error occurs during the speech recognition process.
//     */
//    @Override
//    public AudioTranscription processSpeech(byte[] audioData) throws Exception {
//        String text = speechRecognizer.recognize(audioData);
//        return new AudioTranscription(text);
//    }
//}
//
//

package use_case.speech_to_text;

import frameworks_and_drivers.speech_to_text.SpeechRecognizer;

import java.io.IOException;

/**
 * The use case for processing speech input and transcribing it into text.
 * This implements the SpeechInputBoundary and uses a SpeechRecognizer to do the actual work.
 */
public class SpeechToTextInteractor implements SpeechToTextInputBoundary {

    private final SpeechRecognizer speechRecognizer;
    private final SpeechToTextOutputBoundary outputBoundary;

    /**
     * Constructs a new ProcessSpeechInputUseCase.
     *
     * @param speechRecognizer the recognizer to convert audio into text.
     * @param outputBoundary   the boundary to deliver the transcription result.
     */
    public SpeechToTextInteractor(SpeechRecognizer speechRecognizer, SpeechToTextOutputBoundary outputBoundary) {
        this.speechRecognizer = speechRecognizer;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Starts the speech recognition process.
     *
     * @param inputData The input data (audio data) to recognize.
     * @throws IOException if there is an error with the input/output operation.
     * @throws InterruptedException if the thread is interrupted during the recognition process.
     */
    public void processSpeech(SpeechToTextInputData inputData) throws Exception {
        String onSpeechRecognition = speechRecognizer.recognize(inputData.getAudioData());
        SpeechToTextOutputData outputData = new SpeechToTextOutputData(onSpeechRecognition);
        outputBoundary.deliverTranscription(outputData);
    }
}
