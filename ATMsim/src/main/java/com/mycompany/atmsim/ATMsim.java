package com.mycompany.atmsim;

import javax.swing.SwingUtilities;

public class ATMsim {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });

    }
}

