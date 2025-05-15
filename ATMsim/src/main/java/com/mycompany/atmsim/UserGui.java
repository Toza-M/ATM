package com.mycompany.atmsim;

import javax.swing.*;
import java.awt.event.*;

public class UserGui extends JFrame {
    JTextField cardField;
    JPasswordField pinField;
    UserSide currentUser;

    public UserGui() {
        setTitle("User Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setBounds(50, 50, 100, 30);
        add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(150, 50, 150, 30);
        add(cardField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(50, 100, 100, 30);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(150, 100, 150, 30);
        add(pinField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardField.getText();
                String pin = new String(pinField.getPassword());
                if ((InputChecker.isValidUser(cardNumber, pin))) {
                    currentUser = new UserSide(cardNumber, pin);
                    showUserOptions();
                } else {
                    JOptionPane.showMessageDialog(UserGui.this, "Invalid Card Number or PIN!");
                }
            }
        });

        setVisible(true);
    }

    private void showUserOptions() {
        getContentPane().removeAll();
        repaint();

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getName());
        welcomeLabel.setBounds(100, 30, 200, 30);
        add(welcomeLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 70, 200, 30);
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 110, 200, 30);
        add(withdrawButton);

        JButton showBalanceButton = new JButton("Show Balance");
        showBalanceButton.setBounds(100, 150, 200, 30);
        add(showBalanceButton);

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setBounds(100, 190, 200, 30);
        add(changePinButton);

        depositButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(amountStr);
                currentUser.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposited Successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        });

        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(amountStr);
                if (currentUser.withdraw(amount)) {
                    JOptionPane.showMessageDialog(this, "Withdrawn Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        });

        showBalanceButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Your Balance: " + currentUser.getBalance());
        });

        changePinButton.addActionListener(e -> {
            String newPin = JOptionPane.showInputDialog(this, "Enter new PIN:");
            if (newPin != null && !newPin.trim().isEmpty()) {
                currentUser.changePin(newPin);
                JOptionPane.showMessageDialog(this, "PIN Changed Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid PIN!");
            }
        });

    }

}