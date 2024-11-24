package view;

import javax.swing.*;
import java.awt.*;

// Add the settings & logout buttons
// Add logo
// Make the whole UI look nicer (after finishing usecase implementation)
public class RecognitionViewDraft {
        // A draft for visualizing the View Model
        public static void main(String[] args) {
            // Create the ASL Recognition Part (Left Part)
            JPanel ASL_Panel = new JPanel();
            ASL_Panel.setLayout(new BoxLayout(ASL_Panel, BoxLayout.Y_AXIS));


            // Create a temporary placeholder panel for video insert
            JPanel cameraPanel = new JPanel();
            cameraPanel.setPreferredSize(new Dimension(50, 640));
            cameraPanel.setBackground(Color.BLACK);
            cameraPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            //Create the transcribed text part
            JPanel transcriptionPanel = new JPanel();
            JTextField transcriptionField = new JTextField(50);
            // Might consider changing textField to textArea for longer transcription in the future
            transcriptionPanel.add(transcriptionField);

            // Create the text-to-speech button
            JPanel buttonPanel = new JPanel();
            JButton textToSpeechButton = new JButton("Audio");
            // text_to_speech_Button.addActionListener(new ActionListener() {})
            buttonPanel.add(textToSpeechButton);

            // Combine the transcription & text-to-speech button
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
            textPanel.add(transcriptionPanel);
            textPanel.add(buttonPanel);

            ASL_Panel.add(cameraPanel);
            ASL_Panel.add(textPanel);

            // Create the Left Frame to see effect
            // JFrame aslRecognition = new JFrame("ASL Recognition");
            // aslRecognition.setSize(50,640);
            // aslRecognition.setContentPane(ASL_Panel);

            // aslRecognition.pack();
            // aslRecognition.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // aslRecognition.setVisible(true);


            // Create the Text-to-Speech Part (Left Part)
            JPanel speechToTextPanel = new JPanel();
            speechToTextPanel.setLayout(new BoxLayout(speechToTextPanel, BoxLayout.Y_AXIS));

            // Create the transcription part
            JPanel textTranscriptionPanel = new JPanel();
            JTextArea speechToTextArea = new JTextArea("Transcription here");
            speechToTextArea.setRows(40);
            speechToTextArea.setColumns(30);
            textTranscriptionPanel.add(speechToTextArea);

            // Create the recording buttons
            JPanel recordingPanel = new JPanel();
            JButton startButton = new JButton("Start");
            JButton stopButton = new JButton("Stop");
            recordingPanel.add(startButton);
            recordingPanel.add(stopButton);

            speechToTextPanel.add(textTranscriptionPanel);
            speechToTextPanel.add(recordingPanel);

            // Create the frame
            // JFrame userFrame = new JFrame("Transcription");
            // userFrame.setSize(30,640);
            // userFrame.setContentPane(speechToTextPanel);

            // userFrame.pack();
            // userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Piece the two main panels together
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

            // JLabel projectNameLabel = new JLabel("GestureBridge");

            mainPanel.add(ASL_Panel);
            mainPanel.add(speechToTextPanel);

            // Create the main frame to see effect
            JFrame mainFrame = new JFrame("GestureBridge");
            mainFrame.setSize(100, 640);
            mainFrame.setContentPane(mainPanel);

            mainFrame.pack();
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        }
    }
