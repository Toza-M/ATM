package com.mycompany.atmsim;

import javax.swing.SwingUtilities;

public class ATMsim {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Automatically load data for AdminSide and UserSide
            new MainWindow();
        });
    }
}
