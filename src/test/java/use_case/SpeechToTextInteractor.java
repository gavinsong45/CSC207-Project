import frameworks_and_drivers.speech_to_text.SpeechRecognizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.speech_to_text.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SpeechToTextInteractorTest {

    private SpeechRecognizer mockRecognizer;
    private SpeechToTextOutputBoundary mockOutputBoundary;
    private SpeechToTextInteractor interactor;

    @BeforeEach
    void setUp() {
        mockRecognizer = mock(SpeechRecognizer.class);
        mockOutputBoundary = mock(SpeechToTextOutputBoundary.class);
        interactor = new SpeechToTextInteractor(mockRecognizer, mockOutputBoundary);
    }

    @Test
    void testProcessSpeech_Success() throws Exception {
        // Arrange
        byte[] audioData = "test audio".getBytes();
        SpeechToTextInputData inputData = new SpeechToTextInputData(audioData);
        String recognizedText = "Recognized text";
        when(mockRecognizer.recognize(audioData)).thenReturn(recognizedText);

        // Act
        interactor.processSpeech(inputData);

        // Assert
        verify(mockRecognizer, times(1)).recognize(audioData);
        verify(mockOutputBoundary, times(1))
                .deliverTranscription(new SpeechToTextOutputData(recognizedText));
    }

    @Test
    void testProcessSpeech_IOException() throws Exception {
        // Arrange
        byte[] audioData = "test audio".getBytes();
        SpeechToTextInputData inputData = new SpeechToTextInputData(audioData);
        when(mockRecognizer.recognize(audioData)).thenThrow(new IOException("IO error"));

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IOException.class, () -> {
            interactor.processSpeech(inputData);
        });

        assertEquals("IO error", exception.getMessage());
        verify(mockRecognizer, times(1)).recognize(audioData);
        verifyNoInteractions(mockOutputBoundary);
    }

    @Test
    void testProcessSpeech_InterruptedException() throws Exception {
        // Arrange
        byte[] audioData = "test audio".getBytes();
        SpeechToTextInputData inputData = new SpeechToTextInputData(audioData);
        when(mockRecognizer.recognize(audioData)).thenThrow(new InterruptedException("Interrupted"));

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(InterruptedException.class, () -> {
            interactor.processSpeech(inputData);
        });

        assertEquals("Interrupted", exception.getMessage());
        verify(mockRecognizer, times(1)).recognize(audioData);
        verifyNoInteractions(mockOutputBoundary);
    }
}
