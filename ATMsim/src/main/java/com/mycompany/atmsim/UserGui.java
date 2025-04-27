package com.mycompany.atmsim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGui extends JFrame /*implements ActionListener*/ {

    JButton signInButton;
    JButton logInButton;

    public UserGui() {
        
        
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("User Options");
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        
        JLabel titleLabel = new JLabel("User Options");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel);
        
        JPanel buttonPanel = new JPanel();
        signInButton = new JButton("Sign In");
        logInButton = new JButton("Log In");
        
        JButton signInButton = new JButton("Sign In");
        JButton logInButton = new JButton("Log In");

        // signInButton.addActionListener(this);
        // logInButton.addActionListener(this);
        
        
        signInButton.setPreferredSize(new Dimension(200, 40));
        logInButton.setPreferredSize(new Dimension(200, 40));
        signInButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logInButton.setFont(new Font("Arial", Font.PLAIN, 16));
        
        
        buttonPanel.add(signInButton);
        buttonPanel.add(logInButton);
        
        
        
        
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     if (e.getSource() == signInButton) {
    //         LogInGui logInGui = new LogInGui(); 
    //     }
    //     else if (e.getSource() == logInButton){
    //         SignInGui signInGui = new SignInGui();
            
    //     }
    // }

    
    
    
   
}