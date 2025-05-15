package com.mycompany.atmsim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionFile {
    private static final String path = "transaction.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String name, String cardNumber, String op){
        String date = LocalDateTime.now().format(formatter);
        String entry = String.format("%s,%s,%s", date, name, cardNumber, op);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
            writer.write(entry);
            writer.newLine();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
