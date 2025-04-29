package com.mycompany.atmsim;

import javax.swing.*;
import java.awt.event.*;

public class UserGui {
    private JFrame frame;
    private JTextField cardField;
    private JPasswordField pinField;
    private UserSide currentUser;

    public UserGui() {
        frame = new JFrame("User Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setBounds(50, 50, 100, 30);
        frame.add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(150, 50, 150, 30);
        frame.add(cardField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(50, 100, 100, 30);
        frame.add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(150, 100, 150, 30);
        frame.add(pinField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardField.getText();
                String pin = new String(pinField.getPassword());
                if ((InputChecker.isValidUser(cardNumber, pin))) {
                    currentUser = new UserSide(cardNumber, pin);
                    showUserOptions();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Card Number or PIN!");
                }
            }
        });

        frame.setVisible(true);
    }

    private void showUserOptions() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getName());
        welcomeLabel.setBounds(100, 30, 200, 30);
        frame.add(welcomeLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 70, 200, 30);
        frame.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 110, 200, 30);
        frame.add(withdrawButton);

        JButton showBalanceButton = new JButton("Show Balance");
        showBalanceButton.setBounds(100, 150, 200, 30);
        frame.add(showBalanceButton);

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setBounds(100, 190, 200, 30);
        frame.add(changePinButton);

        // depositButton.addActionListener(e -> {
        //     String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
        //     try {
        //         double amount = Double.parseDouble(amountStr);
        //         currentUser.deposit(amount);
        //         JOptionPane.showMessageDialog(frame, "Deposited Successfully!");
        //     } catch (Exception ex) {
        //         JOptionPane.showMessageDialog(frame, "Invalid amount!");
        //     }
        // });

        // withdrawButton.addActionListener(e -> {
        //     String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
        //     try {
        //         double amount = Double.parseDouble(amountStr);
        //         if (currentUser.withdraw(amount)) {
        //             JOptionPane.showMessageDialog(frame, "Withdrawn Successfully!");
        //         } else {
        //             JOptionPane.showMessageDialog(frame, "Insufficient Balance!");
        //         }
        //     } catch (Exception ex) {
        //         JOptionPane.showMessageDialog(frame, "Invalid amount!");
        //     }
        // });

        showBalanceButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Your Balance: " + currentUser.getBalance());
        });

        changePinButton.addActionListener(e -> {
            String newPin = JOptionPane.showInputDialog(frame, "Enter new PIN:");
            if (newPin != null && !newPin.trim().isEmpty()) {
                currentUser.changePin(newPin);
                JOptionPane.showMessageDialog(frame, "PIN Changed Successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid PIN!");
            }
        });

 
    }

    public static void main(String[] args) {
        new UserGui();
    }
}
