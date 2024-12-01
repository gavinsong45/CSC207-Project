package view;

import entity.AudioSettings;

/**
 * An interface for the GestureBridgeView that is for dependency inversion (will be passed into the presenter
 * in place of the view model.
 */
public interface ViewInterface {

    /**
     * Displays the translation onto the view
     * @param translation the translation that needs to be displayed
     */
    void signLanguageTranslationDisplay(String translation);

    /**
     * Display the speech transcription on the view
     * @param transcription the transcribed text that needs to be displayed
     */
    void signLanguageTranscriptionDisplay(String transcription);

    /**
     * Update the view with the recognition result (prediction). Appends each predicted letter to the text area
     * @param prediction the output data containing the result (prediction)
     */
    void signLanguageRecognitionDisplay(String prediction);

    /**
     * Update the text to speech audio settings to the user-customized settings
     * @param settings the audio settings data to be set
     */
    void setAudioSettings(AudioSettings settings);
}
