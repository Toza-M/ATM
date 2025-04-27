package com.mycompany.atmsim;

import javax.swing.*;
import java.awt.*;

public class LogInGui extends JFrame {
    public LogInGui() {
        // Create main frame
        setTitle("Log In");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel with grid layout
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Username components
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        // Password components
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Login button
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(loginButton);

        // Add components to main panel
        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(buttonPanel);

        // Add to frame
        add(mainPanel, BorderLayout.CENTER);

        // Center the frame on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

  
}