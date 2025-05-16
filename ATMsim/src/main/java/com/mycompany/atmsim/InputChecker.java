package com.mycompany.atmsim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputChecker {

    /*
     * functions to check the correct PIN & Card number for users
     * and to check the correct Email and Password for admins
     * You need to make the files to test this funcs
     * the template for users.txt:
     * Name,Card Number,PIN,Balance
     * the template for admins.txt:
     * Email,Name,Password
     */
    private static final String DATA_FILE = "admin_users.txt";

    public static boolean isValidUser(String cardNumber, String pin) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Format: name, card number, balance, pin
                if (parts.length == 4 && parts[1].trim().equals(cardNumber) && parts[3].trim().equals(pin)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return false;
    }
}