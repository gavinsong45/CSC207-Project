package view;

import javax.swing.*;
import java.awt.*;

import data_access.UserDataStore;
import entity.User;
import frameworks_and_drivers.RunSignLanguageApp;
import use_case.LoginUseCase;
import use_case.RegisterUseCase;

public class LoginScreen {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;

    public LoginScreen(UserDataStore userDataStore) {
        this.loginUseCase = new LoginUseCase(userDataStore);
        this.registerUseCase = new RegisterUseCase(userDataStore);
        displayLogin();
    }

    public void displayLogin() {
        frame = new JFrame("Login or Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegister());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        frame.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(statusLabel, BorderLayout.NORTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = loginUseCase.login(username, password);
        if (user != null) {
            statusLabel.setText("Login successful! Welcome, " + user.getUsername());
            statusLabel.setForeground(Color.GREEN);

            frame.dispose();
            launchMainApplication(user);
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (registerUseCase.register(username, password)) {
            statusLabel.setText("Registration successful!");
            statusLabel.setForeground(Color.GREEN);
        } else {
            statusLabel.setText("Username already exists.");
        }
    }

    private void launchMainApplication(User user) {
        RunSignLanguageApp.start(user);
    }
}
