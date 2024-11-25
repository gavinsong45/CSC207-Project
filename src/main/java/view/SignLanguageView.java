package view;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import controller.SpeechtoTextController;
import entity.AudioParam;
import entity.AudioParamFactory;
import frameworks_and_drivers.Speech2Text.MicrophoneAudioRecorder;
import interface_adapter.Text2Speech.Text2SpeechController;
import interface_adapter.translation.TranslationState;
import use_case.text2speech.Text2SpeechInteractor;

import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationState;
import interface_adapter.translation.TranslationViewModel;

// Added action listener & propertychange listener
public class SignLanguageView extends JPanel implements ActionListener, PropertyChangeListener {

    private JFrame frame;
    private JTextArea signLanguageTextArea;
    private JTextArea transcriptionTextArea;
    private JLabel webcamLabel;
    private GlowButton textToSpeechButton;
    private GlowButton beginTranscriptionButton;
    private GlowButton endTranscriptionButton;
    // Add translate button
    private GlowButton translateButton;
    private JPanel mainPanel;

    // Add drop-down menu for translation
    private JComboBox<String> languageBox;

    private Text2SpeechInteractor text2SpeechInteractor;
    private Text2SpeechController text2SpeechController;

    private SpeechtoTextController speechToTextController;
    private MicrophoneAudioRecorder audioRecorder;

    private TranslationController translationController;
    private TranslationViewModel translationViewModel;


    // Define a color scheme
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color ACCENT_COLOR = new Color(231, 76, 60);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);

    // added translation view model
    public SignLanguageView(SpeechtoTextController speechToTextController, TranslationViewModel translationViewModel) {
        this.speechToTextController = speechToTextController;
        this.audioRecorder = new MicrophoneAudioRecorder();

        this.translationViewModel = translationViewModel;
        translationViewModel.addPropertyChangeListener(this);

        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("GestureBridge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLocationRelativeTo(null);
        frame.setBackground(BACKGROUND_COLOR);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));


        JPanel logoPanel = createLogoPanel();
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        // Webcam feed
        webcamLabel = createWebcamLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 10);
        contentPanel.add(webcamLabel, gbc);

        // Sign language text area
        JPanel signLanguagePanel = createSignLanguagePanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 10);
        contentPanel.add(signLanguagePanel, gbc);

        // Transcription panel
        JPanel transcriptionPanel = createTranscriptionPanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 0);
        contentPanel.add(transcriptionPanel, gbc);

        // Button panel
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

        // Add action listeners
        textToSpeechButton.addActionListener(e -> {
            try {
                performTextToSpeech();
            } catch (IOException | LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });
        beginTranscriptionButton.addActionListener(e -> beginTranscription());
        endTranscriptionButton.addActionListener(e -> endTranscription());

        translateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(translateButton)) {
                            String language = (String) languageBox.getSelectedItem();
                            String text = signLanguageTextArea.getText();
                            translationController.execute(language, text);
                        }
                    }
                }
        );
    }

    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(BACKGROUND_COLOR);

        ImageIcon logoIcon = new ImageIcon("src/main/python/model/resources/gesture_bridge_logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoImageLabel = new JLabel(new ImageIcon(scaledImage));

        JLabel logoTextLabel = new JLabel("GestureBridge");
        logoTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoTextLabel.setForeground(PRIMARY_COLOR);

        logoPanel.add(logoImageLabel);
        logoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        logoPanel.add(logoTextLabel);

        return logoPanel;
    }

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

    private JPanel createSignLanguagePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Sign Language Recognition");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);

        signLanguageTextArea = new JTextArea();
        signLanguageTextArea.setEditable(true);
        signLanguageTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        signLanguageTextArea.setLineWrap(true);
        signLanguageTextArea.setWrapStyleWord(true);
        signLanguageTextArea.setBackground(Color.WHITE);
        signLanguageTextArea.setForeground(TEXT_COLOR);

        JScrollPane scrollPane = new JScrollPane(signLanguageTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR));

        // Add language selection drop-down menu
        String[] languages = {"English", "French", "Spanish", "Mandarin"};
        languageBox = new JComboBox<>(languages);
        languageBox.setSize(5, 10);
        languageBox.setSelectedIndex(0);
        languageBox.setEditable(false);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(languageBox, BorderLayout.EAST);

        return panel;
    }

    private JPanel createTranscriptionPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Transcription");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);

        transcriptionTextArea = new JTextArea();
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

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setBackground(BACKGROUND_COLOR);

        textToSpeechButton = new GlowButton("Text to Speech", SECONDARY_COLOR);
        beginTranscriptionButton = new GlowButton("Begin Transcription", PRIMARY_COLOR);
        endTranscriptionButton = new GlowButton("End Transcription", ACCENT_COLOR);
        // Add new button for translation
        translateButton = new GlowButton("Translate", SECONDARY_COLOR);

        panel.add(translateButton);
        panel.add(textToSpeechButton);
        panel.add(beginTranscriptionButton);
        panel.add(endTranscriptionButton);

        return panel;
    }

    // with audio from sign language text area
    /*private void performTextToSpeech() throws IOException, LineUnavailableException {
        String inputText = signLanguageTextArea.getText();
        AudioParamFactory audioParamFactory = new AudioParamFactory();
        final AudioParam audioParam = audioParamFactory.create(1, 1, false, 1);
        text2SpeechInteractor = new Text2SpeechInteractor();
        text2SpeechController = new Text2SpeechController(text2SpeechInteractor);
        text2SpeechController.execute(inputText, "en-US", audioParam);
    }*/

    // with audio from sign language text area
    private void performTextToSpeech() throws IOException, LineUnavailableException {
        String inputText = transcriptionTextArea.getText();
        AudioParamFactory audioParamFactory = new AudioParamFactory();
        final AudioParam audioParam = audioParamFactory.create(1, 1, false, 1);
        text2SpeechInteractor = new Text2SpeechInteractor();
        text2SpeechController = new Text2SpeechController(text2SpeechInteractor);
        text2SpeechController.execute(inputText, "en-US", audioParam);
    }


    private void beginTranscription() {
        transcriptionTextArea.setText("Transcription started...\n");
        beginTranscriptionButton.setEnabled(false);
        endTranscriptionButton.setEnabled(true);
        startRecording();
    }

    private void endTranscription() {
        transcriptionTextArea.append("Transcription ended.\n");
        beginTranscriptionButton.setEnabled(true);
        endTranscriptionButton.setEnabled(false);
        stopRecordingAndTranscribe();
    }

    private void startRecording() {
        try {
            audioRecorder.start();
        } catch (Exception e) {
            transcriptionTextArea.append("Error starting recording: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void stopRecordingAndTranscribe() {
        try {
            audioRecorder.stop();
            byte[] audioData = audioRecorder.getAudioData();

            String transcription = speechToTextController.handleSpeechInput(audioData);
            updateTranscriptionText("Transcription Result: " + transcription);

        } catch (Exception e) {
            transcriptionTextArea.append("Error during transcription: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    public void updateDisplay(String prediction) {
        signLanguageTextArea.append(prediction);
    }

    public void updateTranscriptionText(String text) {
        transcriptionTextArea.append(text + "\n");
    }

    // Hook translate button with translation use case


    // Custom GlowButton class for a unique, glowing effect
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

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println(evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Sth. to do with the firepropertychange()
        final TranslationState state = (TranslationState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(TranslationState state) {
        // translationField.setText(state.getTranslation());
        signLanguageTextArea.setText(state.getTranslation());
        // Set it so that the input and output are in the same box - fits the UI and links to text-to-speech
    }

    public void setSelectLanguageController(TranslationController translationController) {
        this.translationController = translationController;
    }
}