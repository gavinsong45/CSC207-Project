package interface_adapter.speech_to_text;

/**
 * Interface for audio recording functionality.
 * Classes implementing this interface should handle audio recording,
 * including starting, stopping, and providing the recorded audio data.
 */
public interface AudioRecorderInterface {
    /**
     * Starts recording audio.
     */

    void start();
    /**
     * Stops recording audio.
     */

    void stop();

    /**
     * Retrieves the recorded audio data.
     *
     * @return A byte array containing the recorded audio data.
     */
    byte[] getAudioData();
}
