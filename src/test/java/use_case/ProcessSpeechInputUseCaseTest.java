//package use_case;
//
//import entity.AudioTranscription;
//import use_case.speech_to_text.SpeechToTextInputBoundary;
//import frameworks_and_drivers.speech_to_text.SpeechRecognizer;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import use_case.speech_to_text.SpeechToTextInteractor;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProcessSpeechInputUseCaseTest {
//
//    private SpeechRecognizer mockSpeechRecognizer;
//    private SpeechToTextInputBoundary useCase;
//
//    @BeforeEach
//    public void setUp() {
//        mockSpeechRecognizer = new SpeechRecognizer() {
//            @Override
//            public String recognize(byte[] audioData) throws Exception {
//                if (audioData == null) {
//                    throw new IllegalArgumentException("audioData is null");
//                }
//                return "demo transcribed text";
//            }
//        };
//
//        useCase = new SpeechToTextInteractor(mockSpeechRecognizer);
//    }
//
//    @Test
//    public void testProcessSpeech_Success() throws Exception {
//        byte[] audioData = new byte[]{1, 2, 3};
//
//        AudioTranscription result = useCase.processSpeech(audioData);
//
//        assertNotNull(result, "it should not be null");
//        assertEquals("demo transcribed text", result.getText(), "it is same");
//    }
//
//    @Test
//    public void testProcessSpeech_NullAudioData() {
//        byte[] audioData = null;
//
//        Exception exception = assertThrows(Exception.class, () -> {
//            useCase.processSpeech(audioData);
//        });
//
//        String expectedMessage = "audioData is null";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage), "audio cannot be null");
//    }
//
//    @Test
//    public void testProcessSpeech_RecognizerThrowsException() throws Exception {
//        SpeechRecognizer exceptionThrowingRecognizer = new SpeechRecognizer() {
//            @Override
//            public String recognize(byte[] audioData) throws Exception {
//                throw new Exception("error");
//            }
//        };
//
//        useCase = new SpeechToTextInteractor(exceptionThrowingRecognizer);
//
//        byte[] audioData = new byte[]{1, 2, 3};
//
//        Exception exception = assertThrows(Exception.class, () -> {
//            useCase.processSpeech(audioData);
//        });
//
//        String expectedMessage = "error";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage), "");
//    }
//}