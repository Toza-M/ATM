package com.mycompany.atmsim;

import javax.swing.SwingUtilities;

public class ATMsim {

    public static void main(String[] args) {
        AdminSide admin=new AdminSide();
          admin.adduser();
//        admin.updateuser();
//       admin.deleteuser();
//          admin.viewusers();
          MainWindow mainWindow = new MainWindow();
        
         

    }
}
