package interface_adapter.text_to_speech;

import frameworks_and_drivers.text_to_speech.AudioPlayer;
import use_case.text_to_speech.TextToSpeechOutputBoundary;
import use_case.text_to_speech.TextToSpeechOutputData;

/**
 * Responsible for presenting the output of the Text-to-Speech use case.
 */
public class TextToSpeechPresenter implements TextToSpeechOutputBoundary {
    private final AudioPlayer audioPlayer;

    public TextToSpeechPresenter(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }
    @Override
    public void prepareOutput(TextToSpeechOutputData outputData) {
        // Delegate the actual audio playback to the AudioPlayer
        try {
            System.out.println("Audio content is ready, now playing audio...");
            audioPlayer.playAudio(outputData.getAudioContents());
        } catch (Exception e) {
            System.err.println("Error during audio playback: " + e.getMessage());
        }
    }
}
