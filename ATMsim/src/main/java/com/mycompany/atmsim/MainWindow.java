package com.mycompany.atmsim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

public class MainWindow extends JFrame implements ActionListener {
    JButton userButton;
    JButton adminButton;

    private ArrayList<String> adminUsernames = new ArrayList<>();
    private ArrayList<String> adminPasswords = new ArrayList<>();
    private static final String ADMIN_FILE = "admin_access.txt";

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

        
        // omar helal
        
        loadAdminCredentials();
    }

    
   
    
    private void loadAdminCredentials() {
        File file = new File(ADMIN_FILE);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("admin,admin");
            } catch (IOException e) {
                // ignore
            }
        }
        adminUsernames.clear();
        adminPasswords.clear();
        try (FileReader reader = new FileReader(file)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                if (c == '\n' || c == '\r') {
                    if (sb.length() > 0) {
                        String[] parts = sb.toString().split(",", 2);
                        if (parts.length == 2) {
                            adminUsernames.add(parts[0].trim());
                            adminPasswords.add(parts[1].trim());
                        }
                        sb.setLength(0);
                    }
                } else {
                    sb.append((char) c);
                }
            }
            // Handle last line if not newline-terminated
            if (sb.length() > 0) {
                String[] parts = sb.toString().split(",", 2);
                if (parts.length == 2) {
                    adminUsernames.add(parts[0].trim());
                    adminPasswords.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            // ignore
        }
    }

    private void saveAdminCredentials() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ADMIN_FILE))) {
            for (int i = 0; i < adminUsernames.size(); i++) {
                writer.println(adminUsernames.get(i) + "," + adminPasswords.get(i));
            }
        } catch (IOException e) {
            // ignore
        }
    }

    private void showAdminLoginDialog() {
        JDialog dialog = new JDialog(this, "Admin Login", true);
        dialog.setSize(350, 220);
        dialog.setLayout(null);
        dialog.setLocationRelativeTo(this);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 20, 80, 25);
        dialog.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(120, 20, 160, 25);
        dialog.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 60, 80, 25);
        dialog.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(120, 60, 160, 25);
        dialog.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 110, 100, 30);
        dialog.add(loginBtn);

        JButton addAdminBtn = new JButton("Add New Admin");
        addAdminBtn.setBounds(160, 110, 130, 30);
        dialog.add(addAdminBtn);

        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            boolean found = false;
            for (int i = 0; i < adminUsernames.size(); i++) {
                if (adminUsernames.get(i).equals(username) && adminPasswords.get(i).equals(password)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                dialog.dispose();
                new AdminSide();
            } else {
                JOptionPane.showMessageDialog(dialog, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addAdminBtn.addActionListener(e -> {
            // Ask for master password before allowing to add new admin
            JPasswordField masterPassField = new JPasswordField();
            JPanel masterPanel = new JPanel(new GridLayout(0, 1));
            masterPanel.add(new JLabel("Enter master password:"));
            masterPanel.add(masterPassField);
            int masterResult = JOptionPane.showConfirmDialog(
                    dialog,
                    masterPanel,
                    "Master Password Required",
                    JOptionPane.OK_CANCEL_OPTION);
            if (masterResult != JOptionPane.OK_OPTION) {
                return;
            }
            String masterPass = new String(masterPassField.getPassword());
            if (!"1234".equals(masterPass)) {
                JOptionPane.showMessageDialog(dialog, "Incorrect master password!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JTextField newUser = new JTextField();
            JPasswordField newPass = new JPasswordField();
            JPanel addPanel = new JPanel(new GridLayout(0, 1));
            addPanel.add(new JLabel("New Username:"));
            addPanel.add(newUser);
            addPanel.add(new JLabel("New Password:"));
            addPanel.add(newPass);
            int result = JOptionPane.showConfirmDialog(dialog, addPanel, "Add New Admin", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String uname = newUser.getText().trim();
                String pword = new String(newPass.getPassword());
                if (uname.isEmpty() || pword.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Username and password cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean exists = false;
                    for (String existing : adminUsernames) {
                        if (existing.equals(uname)) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        JOptionPane.showMessageDialog(dialog, "Username already exists.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        adminUsernames.add(uname);
                        adminPasswords.add(pword);
                        saveAdminCredentials();
                        JOptionPane.showMessageDialog(dialog, "Admin added successfully.");
                    }
                }
            }
        });

        dialog.setVisible(true);
    }

    // The action after clicking on the buttons
    // The user and admin sides are not implemented yet
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton)
            new UserGui();
        if (e.getSource() == adminButton)
            showAdminLoginDialog();
    }

}
//omarhelal