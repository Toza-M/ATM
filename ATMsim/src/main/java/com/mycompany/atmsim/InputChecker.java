
package com.mycompany.atmsim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputChecker {
    
    /*
        functions to check the correct PIN & Card number for users 
        and to check the correct Email and Password for admins
        You need to make the files to test this funcs
        the template for users.txt:
            Name,Card Number,PIN
        the template for admins.txt:
            Email,Name,Password
    */
    
     public static boolean isValidUser(String cardNumber, String pin) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[1].trim().equals(cardNumber) && parts[2].trim().equals(pin)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValidAdmin(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("admins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].trim().equalsIgnoreCase(email) && parts[2].trim().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
