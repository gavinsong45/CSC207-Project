package frameworks_and_drivers.speech_to_text;

import javax.sound.sampled.*;
        import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A concrete implementation of the AudioRecorder interface that records audio via the system's microphone.
 */
public class MicrophoneAudioRecorder implements AudioRecorder {
    private TargetDataLine targetDataLine;
    private AudioFormat audioFormat;
    private ByteArrayOutputStream out;
    private Thread captureThread;
    private boolean isRecording;

    /**
     * Starts the audio recording process.
     */
    @Override
    public void start() {
        if (isRecording) {
            return;
        }
        isRecording = true;

        audioFormat = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line not supported");
            return;
        }

        try {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            out = new ByteArrayOutputStream();
            captureThread = new Thread(() -> {
                byte[] buffer = new byte[4096];
                int bytesRead;

                while (isRecording && !Thread.currentThread().isInterrupted()) {
                    bytesRead = targetDataLine.read(buffer, 0, buffer.length);
                    out.write(buffer, 0, bytesRead);
                }

                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            captureThread.start();
            System.out.println("Recording started...");
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the audio recording process.
     */
    @Override
    public void stop() {
        if (!isRecording) {
            return;
        }
        isRecording = false;

        if (captureThread != null && captureThread.isAlive()) {
            captureThread.interrupt();
            try {
                captureThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (targetDataLine != null) {
            targetDataLine.stop();
            targetDataLine.close();
        }
        System.out.println("Recording stopped.");
    }

    /**
     * Retrieves the recorded audio data.
     *
     * @return A byte array containing the recorded audio data.
     */
    @Override
    public byte[] getAudioData() {
        return out != null ? out.toByteArray() : new byte[0];
    }

    /**
     * Configures and returns the audio format to be used for recording.
     *
     * @return The AudioFormat for the recording.
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }
}
