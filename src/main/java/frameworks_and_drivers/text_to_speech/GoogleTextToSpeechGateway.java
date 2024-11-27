package frameworks_and_drivers.text_to_speech;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import use_case.text_to_speech.TextToSpeechInputData;
import use_case.text_to_speech.TextToSpeechOutputData;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Implementation of the TextToSpeechGateway using Google Cloud Text-to-Speech API.
 */
public class GoogleTextToSpeechGateway implements TextToSpeechInterface {

    private final String credentialsPath;

    public GoogleTextToSpeechGateway() {
        this.credentialsPath = "src/main/java/data_access/package.json";
    }

    @Override
    public TextToSpeechOutputData convertTextToSpeech(TextToSpeechInputData inputData) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

        try (TextToSpeechClient client = TextToSpeechClient.create(settings)) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(inputData.getText()).build();

            // Determine voice gender
            String genderValue = inputData.getGender() ? "MALE" : "FEMALE";

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode(inputData.getLanguageCode())
                    .setSsmlGender(SsmlVoiceGender.valueOf(genderValue))
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setPitch(inputData.getPitch())
                    .setAudioEncoding(AudioEncoding.LINEAR16)
                    .setSpeakingRate(inputData.getSpeakingRate())
                    .setVolumeGainDb(inputData.getVolume())
                    .build();

            SynthesizeSpeechResponse response = client.synthesizeSpeech(input, voice, audioConfig);

            ByteString audioContents = response.getAudioContent();
            return new TextToSpeechOutputData(audioContents);
        }
    }
}

