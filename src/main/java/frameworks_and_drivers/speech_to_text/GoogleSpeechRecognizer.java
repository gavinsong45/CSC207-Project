package frameworks_and_drivers.speech_to_text;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.util.List;

/**
 * Concrete implementation of the SpeechRecognizer interface using Google Cloud's Speech-to-Text API.
 */
public class GoogleSpeechRecognizer implements SpeechRecognizer {

    /**
     * Recognizes speech from the given audio data using Google Cloud's Speech-to-Text API.
     *
     * @param audioData The audio data in byte array format.
     * @return A transcription of the audio as a String.
     * @throws Exception If an error occurs during the recognition process.
     */
    @Override
    public String recognize(byte[] audioData) throws Exception {
        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new FileInputStream("src/main/java/data_access/package.json"));
        SpeechSettings speechSettings = SpeechSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

        try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setSampleRateHertz(16000)
                    .setLanguageCode("en-US")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioData))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            StringBuilder transcription = new StringBuilder();
            for (SpeechRecognitionResult result : results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcription.append(alternative.getTranscript());
            }

            return transcription.toString();
        }
    }
}

