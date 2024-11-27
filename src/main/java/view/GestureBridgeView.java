package view;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

import entity.AudioSettings;
import entity.AudioSettingsFactory;
import frameworks_and_drivers.speech_to_text.MicrophoneAudioRecorder;
import frameworks_and_drivers.text_to_speech.LanguageCodeMapper;
import interface_adapter.sign_language_translation.SignLanguageTranslationController;
import interface_adapter.speech_to_text.SpeechToTextController;
import interface_adapter.text_to_speech.TextToSpeechController;

/**
 * GestureBridgeView is a JPanel that represents the graphical user interface (GUI)
 * for the GestureBridge application. It provides components for displaying sign language recognition,
 * transcription, signLanguageTranslationDisplay, and speech synthesis. It interacts with controllers for managing
 * sign language signLanguageTranslationDisplay and speech-to-text conversion.
 */
public class GestureBridgeView extends JPanel {

    private JTextArea signLanguageTextArea;
    private JTextArea transcriptionTextArea;
    private JComboBox<String> languageBox;
    private GlowButton textToSpeechButton, beginTranscriptionButton, endTranscriptionButton, translateButton;

    private SignLanguageTranslationController signLanguageTranslationController;
    private SpeechToTextController speechToTextController;
    private MicrophoneAudioRecorder audioRecorderForTranscription;
    private TextToSpeechController textToSpeechController;

    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color ACCENT_COLOR = new Color(231, 76, 60);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);

    /**
     * Constructs a new GestureBridgeView and initializes the user interface.
     * This constructor sets up the layout, colors, and interactive components of the UI.
     */
    public GestureBridgeView() {
        initializeUI();
    }

    /**
     * Sets the controller responsible for translating sign language to text.
     *
     * @param signLanguageTranslationController the controller that handles sign language signLanguageTranslationDisplay.
     */
    public void setTranslationController(SignLanguageTranslationController signLanguageTranslationController) {
        this.signLanguageTranslationController = signLanguageTranslationController;
    }

    /**
     * Sets the controller responsible for converting speech to text.
     *
     * @param speechToTextController the controller that handles speech-to-text conversion.
     */
    public void setSpeechToTextController(SpeechToTextController speechToTextController) {
        this.speechToTextController = speechToTextController;
        this.audioRecorderForTranscription = new MicrophoneAudioRecorder();
    }

    public void setTextToSpeechController(TextToSpeechController textToSpeechController) {
        this.textToSpeechController = textToSpeechController;

    }

    /**
     * Initializes the user interface (UI) for the GestureBridge application.
     * Sets up the main JFrame window, layouts, panels, and components including
     * text areas, buttons, labels, and other UI elements. This method is responsible
     * for defining the structure and appearance of the application's graphical interface.
     */
    private void initializeUI() {
        JFrame frame = new JFrame("GestureBridge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLocationRelativeTo(null);
        frame.setBackground(BACKGROUND_COLOR);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        signLanguageTextArea = new JTextArea();
        transcriptionTextArea = new JTextArea();
        languageBox = new JComboBox<>(new String[]{"English", "French", "Spanish", "Mandarin"});

        JPanel logoPanel = createLogoPanel();
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel webcamLabel = createWebcamLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 10);
        contentPanel.add(webcamLabel, gbc);

        JPanel signLanguagePanel = createSignLanguagePanel();
        gbc.gridy = 1;
        gbc.weighty = 0.4;
        contentPanel.add(signLanguagePanel, gbc);

        JPanel transcriptionPanel = createTranscriptionPanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 10, 0);
        contentPanel.add(transcriptionPanel, gbc);

        JPanel buttonPanel = createButtonPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(buttonPanel, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    /**
     * Creates and returns a JPanel that contains the application's logo or title.
     * The logo panel uses a left-aligned FlowLayout and displays the application name in a styled JLabel.
     *
     * @return a JPanel containing the application's logo or title.
     */
    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(BACKGROUND_COLOR);

        JLabel logoTextLabel = new JLabel("GestureBridge");
        logoTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoTextLabel.setForeground(PRIMARY_COLOR);

        logoPanel.add(logoTextLabel);

        return logoPanel;
    }

    /**
     * Creates and returns a JLabel representing the webcam feed display.
     * The label has a fixed size, a black background, and a white centered text with a border.
     *
     * @return a JLabel configured to display the webcam feed.
     */
    private JLabel createWebcamLabel() {
        JLabel label = new JLabel("Webcam Feed");
        label.setPreferredSize(new Dimension(640, 480));
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 2));
        return label;
    }

    /**
     * Creates and returns a JPanel for displaying sign language recognition results.
     * The panel contains a title label, a scrollable text area for recognition output, and a language selector.
     *
     * @return a JPanel configured for sign language recognition output.
     */
    private JPanel createSignLanguagePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Sign Language Recognition");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);

        signLanguageTextArea.setEditable(true);
        signLanguageTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        signLanguageTextArea.setLineWrap(true);
        signLanguageTextArea.setWrapStyleWord(true);
        signLanguageTextArea.setBackground(Color.WHITE);
        signLanguageTextArea.setForeground(TEXT_COLOR);

        JScrollPane scrollPane = new JScrollPane(signLanguageTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(languageBox, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates a panel for displaying the transcription results.
     * This panel includes a title and a text area for showing the transcribed text.
     *
     * @return a JPanel for displaying transcription results.
     */
    private JPanel createTranscriptionPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Transcription");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);

        transcriptionTextArea.setEditable(false);
        transcriptionTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        transcriptionTextArea.setLineWrap(true);
        transcriptionTextArea.setWrapStyleWord(true);
        transcriptionTextArea.setBackground(Color.WHITE);
        transcriptionTextArea.setForeground(TEXT_COLOR);

        JScrollPane scrollPane = new JScrollPane(transcriptionTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates a panel containing control buttons for various functionalities
     * such as signLanguageTranslationDisplay, text-to-speech, and transcription management.
     *
     * @return a JPanel containing action buttons.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setBackground(BACKGROUND_COLOR);

        translateButton = new GlowButton("Translate", SECONDARY_COLOR);
        textToSpeechButton = new GlowButton("Text to Speech", SECONDARY_COLOR);
        beginTranscriptionButton = new GlowButton("Begin Transcription", PRIMARY_COLOR);
        endTranscriptionButton = new GlowButton("End Transcription", ACCENT_COLOR);

        translateButton.addActionListener(e -> translationProcess());
        textToSpeechButton.addActionListener(e -> {
            try {
                beginTextToSpeech();
            } catch (LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        beginTranscriptionButton.addActionListener(e -> beginTranscription());
        endTranscriptionButton.addActionListener(e -> {
            try {
                endTranscription();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(translateButton);
        panel.add(textToSpeechButton);
        panel.add(beginTranscriptionButton);
        panel.add(endTranscriptionButton);

        return panel;
    }


    /**
     * Appends the predicted letter from sign language recognition to the display.
     * This method updates the sign language recognition text area by appending the predicted
     * signLanguageTranslationDisplay from the sign language recognition process.
     *
     * @param prediction is the predicted letter to be appended to the sign language recognition display.
     */
    public void signLanguageRecognitionDisplay(String prediction) {
        signLanguageTextArea.append(prediction);
    }

    /**
     * Initializes and starts the transcription process.
     * This method updates the transcription text area to indicate that the transcription process has begun,
     * disables the "Begin Transcription" button, and enables the "End Transcription" button.
     */
    private void beginTranscription() {
        transcriptionTextArea.setText("The transcription process has begun.\n");
        beginTranscriptionButton.setEnabled(false);
        endTranscriptionButton.setEnabled(true);
        beginRecording();
    }

    /**
     * Finalizes and stops the transcription process.
     * This method appends a message to the transcription text area indicating that the transcription process has ended,
     * enables the "Begin Transcription" button, and disables the "End Transcription" button.
     */
    private void endTranscription() throws Exception {
        transcriptionTextArea.append("The transcription process has ended.\n");
        beginTranscriptionButton.setEnabled(true);
        endTranscriptionButton.setEnabled(false);
        endRecordingAndProcess();
    }

    /**
     * Starts the audio recording process.
     * Utilizes the audio recorder instance to begin capturing audio input for transcription.
     */
    private void beginRecording() {
        audioRecorderForTranscription.start();
    }

    /**
     * Stops the audio recording and processes the recorded audio data.
     * Once recording stops, the audio data is fetched from the recorder and sent to the Speech-to-Text controller for transcription.
     *
     * @throws Exception if an error occurs during the recording stop or audio processing.
     */
    private void endRecordingAndProcess() throws Exception {
        audioRecorderForTranscription.stop();
        byte[] audioData = audioRecorderForTranscription.getAudioData();
        speechToTextController.processSpeech(audioData);
    }

    /**
     * Displays the transcription result in the designated text area.
     * Updates the transcription text area with the provided transcription result.
     *
     * @param transcription the transcription result to be displayed in the text area.
     */
    public void signLanguageTranscriptionDisplay(String transcription) {
        transcriptionTextArea.setText("Transcription Result: " + transcription);
    }

    /**
     * Initiates the sign language translation process based on the selected language.
     * This method retrieves the selected language from the language selection box and
     * extracts the current text from the sign language recognition text area.
     * It then triggers the translation process using the signLanguageTranslationController.
     */
    private void translationProcess() {
        String language = (String) languageBox.getSelectedItem();
        String text = signLanguageTextArea.getText();
        signLanguageTranslationController.execute(language, text);
    }

    /**
     * Updates the sign language text area with the translated text.
     * This method replaces the existing content of the sign language text area with
     * the provided translation, effectively showing the translated version of the input.
     *
     * @param translation the translated text to be displayed in the sign language text area
     */
    public void signLanguageTranslationDisplay(String translation) {
        signLanguageTextArea.setText(translation);
    }

    private void beginTextToSpeech() throws LineUnavailableException, IOException {
        String inputText = signLanguageTextArea.getText();
        String language = (String) languageBox.getSelectedItem();
        String languageCode = LanguageCodeMapper.getLanguageCode(language);
        AudioSettingsFactory audioSettingsFactory = new AudioSettingsFactory();
        AudioSettings audioSettings = audioSettingsFactory.create(1.5, 2.0, false, 6.0); // Example settings
        textToSpeechController.execute(inputText, languageCode, audioSettings);

    }

    private static class GlowButton extends JButton {
        private final Color baseColor;

        public GlowButton(String text, Color color) {
            super(text);
            this.baseColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("Segoe UI", Font.BOLD, 16));
            setForeground(Color.WHITE);
            setPreferredSize(new Dimension(200, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            g2d.setColor(baseColor);
            g2d.fill(new RoundRectangle2D.Float(0, 0, width, height, 20, 20));

            g2d.setColor(Color.WHITE);
            FontMetrics fm = g2d.getFontMetrics();
            Rectangle2D r = fm.getStringBounds(getText(), g2d);
            int x = (width - (int) r.getWidth()) / 2;
            int y = (height - (int) r.getHeight()) / 2 + fm.getAscent();
            g2d.drawString(getText(), x, y);
        }
    }
}

