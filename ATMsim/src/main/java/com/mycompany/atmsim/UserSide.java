/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atmsim;

/**
 *
 * @author HP
 */
import java.io.*;
import java.util.*;

public class UserSide {
    private static final String file = "users.txt";
    private String name;
    private String cardNumber;
    private String pin;
    private double balance;

    public UserSide(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        loadUserData();
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].trim().equals(cardNumber) && parts[2].trim().equals(pin)) {
                    this.name = parts[0].trim();
                    this.balance = Double.parseDouble(parts[3].trim());
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
    public String getCardNumber(){
        return cardNumber;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        updateUserData();
        TransactionFile.log(this.getName(), this.getCardNumber(), "DEPOSIT");
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        updateUserData();
        TransactionFile.log(this.getName(), this.getCardNumber(), "WITHDRAW");
        return true;
    }

    public boolean changePin(String newPin) {
        this.pin = newPin;
        updateUserData();
        TransactionFile.log(this.getName(), this.getCardNumber(), "CHANGE PIN");
        return true;
    }

    private void updateUserData() {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].trim().equals(cardNumber)) {
                    users.add(name + "," + cardNumber + "," + pin + "," + balance);
                } else {
                    users.add(line);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String user : users) {
                writer.write(user);
                writer.newLine();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}