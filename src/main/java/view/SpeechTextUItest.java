package view;

import controllers.SpeechtoTextController;
import frameworks_and_drivers.MicrophoneAudioRecorder;

import javax.swing.*;
import java.awt.*;


public class SpeechTextUItest {
    private final SpeechtoTextController controller;
    private JTextArea transcriptionArea;

    public SpeechTextUItest(SpeechtoTextController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Sign Language Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JButton startRecordingButton = new JButton("Start Recording");
        JButton stopRecordingButton = new JButton("Stop Recording");
        transcriptionArea = new JTextArea();
        transcriptionArea.setLineWrap(true);

        MicrophoneAudioRecorder audioRecorder =
                new MicrophoneAudioRecorder();

        startRecordingButton.addActionListener(e -> {
            audioRecorder.start();
        });

        stopRecordingButton.addActionListener(e -> {
            audioRecorder.stop();
            byte[] audioData = audioRecorder.getAudioData();
            String transcription = controller
                    .handleSpeechInput(audioData);
            transcriptionArea.setText(transcription);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startRecordingButton);
        buttonPanel.add(stopRecordingButton);

        frame.getContentPane().add(BorderLayout.NORTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.CENTER,
                new JScrollPane(transcriptionArea));

        frame.setVisible(true);
    }
}