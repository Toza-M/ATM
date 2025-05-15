
package com.mycompany.atmsim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {
    JButton userButton;
    JButton adminButton;

    public MainWindow() {
        // The Main Window
        setTitle("ATM SIM");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#f4f4f4"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Establish the Title (Ana sa7eb el bank :))
        JLabel titleLabel = new JLabel("Welcome to Banque de Toza");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#f4f4f4"));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 80));

        userButton = new JButton("User");
        adminButton = new JButton("Admin");

        Color black = Color.BLACK;
        Color white = Color.WHITE;
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        // Make it in for loop to not waste my time
        for (JButton btn : new JButton[] { userButton, adminButton }) {
            btn.setBackground(black);
            btn.setForeground(white);
            btn.setFocusPainted(false);
            btn.setFont(buttonFont);
            btn.setPreferredSize(new Dimension(120, 40));
        }

        buttonPanel.add(userButton);
        buttonPanel.add(adminButton);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(buttonPanel);

        add(mainPanel);

        userButton.addActionListener(this);
        adminButton.addActionListener(this);
        

    }

    // The action after clicking on the buttons
    // The user and admin sides are not implemented yet
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton)
            new UserGui();
        if (e.getSource() == adminButton)
            new AdminSide();
    }

}