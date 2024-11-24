package frameworks_and_drivers.Speech2Text;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.cloud.speech.v1.SpeechSettings;
import interface_adapter.Speech2Text.SpeechRecognizer;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.util.List;

/**
 * An implementation of the {@link SpeechRecognizer} interface
 * that uses Google Cloud's Speech-to-Text API to transcribe audio data.
 */
public class GoogleSpeechRecognizer implements SpeechRecognizer {
    /**
     * Recognizes speech from the given audio data using Google Cloud's
     * Speech-to-Text API.
     *
     * @param audioData The audio data in byte array format.
     * @return A transcription of the audio as a String.
     * @throws Exception If an error occurs during the recognition process,
     *                   such as invalid credentials or API errors.
     */
    @Override
    public String recognize(byte[] audioData) throws Exception {
        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new FileInputStream("/Users/yibinwang/Desktop/summer-ranger-441414-f8-0a863841dab9.json"));
        SpeechSettings speechSettings = SpeechSettings.newBuilder()
                .setCredentialsProvider(
                        FixedCredentialsProvider.create(credentials))
                .build();

        try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding
                            .LINEAR16)
                    .setSampleRateHertz(16000)
                    .setLanguageCode("en-US")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioData))
                    .build();

            RecognizeResponse response = speechClient.recognize(
                    config, audio);
            List<SpeechRecognitionResult> results =
                    response.getResultsList();

            StringBuilder transcription = new StringBuilder();
            for (SpeechRecognitionResult result : results) {
                SpeechRecognitionAlternative alternative =
                        result.getAlternativesList().get(0);
                transcription.append(alternative.getTranscript());
            }

            return transcription.toString();
        }
    }
}
