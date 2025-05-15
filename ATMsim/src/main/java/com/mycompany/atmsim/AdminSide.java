package com.mycompany.atmsim;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AdminSide extends JFrame {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String[]> users = new ArrayList<>(); // array list
    private static final String DATA_FILE = "admin_users.txt";
    
    public void resetPin() {
    System.out.print("Enter account number: ");
    String accountNumber = scanner.nextLine();

    System.out.print("Enter old PIN: ");
    String oldPin = scanner.nextLine();

    boolean founded = false;

    for (String[] user : users) {
        if (user.length == 4 && user[1].equals(accountNumber)) {
            founded = true;

            if (!user[3].equals(oldPin)) {
                System.out.println("Invalid old PIN.");
                return;
            }

            System.out.print("Enter new PIN: ");
            String newPin = scanner.nextLine();

            System.out.print("Confirm new PIN: ");
            String confirmPin = scanner.nextLine();

            if (!newPin.equals(confirmPin)) {
                System.out.println("New PINs do not match.");
                return;
            }

            user[3] = newPin;
            System.out.println("PIN reset successfully.");
            return;
        }
    }

    if (!founded) {
        System.out.println("Account number not found.");
    }
}

    public void searchAccount() {
    System.out.println("Search for a user : ");
    System.out.print("Enter the account number you want to serch for: ");
    String accountNumber = scanner.nextLine();

    boolean found = false;

    for (String[] user : users) {
        if (user.length >= 4 && user[1].equals(accountNumber)) {
            System.out.println(" User founded:");
            System.out.println("Name: " + user[0]);
            System.out.println("Account Number: " + user[1]);
            System.out.println("Balance: " + user[2]);
            System.out.println("PIN: ****");
            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println(" Invalid account numer.");
    }
}

    public void adduser() {
        System.out.println("add user");
        System.out.println("Enter ur name");

        String name = scanner.nextLine();
        System.out.println("Enter account number");
        String accountnumber = scanner.nextLine();

        System.out.println("Enter ur balance");
        String balance = scanner.nextLine();

        System.out.println("Enter pin");
        String pin = scanner.nextLine();

        String[] newuser = new String[4];
        newuser[0] = name;
        newuser[1] = accountnumber;
        newuser[2] = String.valueOf(balance);
        newuser[3] = pin;
        users.add(newuser);
        System.out.println("user added succssfully");
        System.out.println("ur account number is " + accountnumber);

    }
//      
        

    public void updateuser() {

        System.out.println("update user details");
        System.out.println("enter ur number account number to update");
        String accountnumber = scanner.nextLine();

        String[] finduser = null;
        for (String[] user : users) {
            if (user[1].equals(accountnumber)) {
                finduser = user;
                break;
            }
        }
        if (finduser == null) {

            System.out.println("user not found");
            return;

        }
        System.out.println("enter ur new name");
        String newname = scanner.nextLine();
        if (!newname.isEmpty()) {
            finduser[0] = newname;
        }
        System.out.println("enter ur new pin");
        String newpin = scanner.nextLine();
        if (!newpin.isEmpty()) {
            finduser[3] = newpin;
        }
        System.out.println("user details updated");

    }

    public void deleteuser() {

        System.out.println("delete user");
        System.out.println("enter ur account number to delete");
        String accountnumber = scanner.nextLine();
        boolean deleted = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i)[1].equals(accountnumber)) {
                users.remove(i);
                deleted = true;
                break;

            }
        }
        if (deleted) {
            System.out.println("user deleted succsefully");
        } else {
            System.out.println("user not found");
        }

    }

    public void viewusers() {
        System.out.println("view users");

        if (users.isEmpty()) {
            System.out.println("no users found.");
            return;
        }

        for (String[] user : users) {
            System.out.println("user details");
            System.out.println(" name: " + user[0]);
            System.out.println(" account number: " + user[1]);
            System.out.println(" balance: " + user[2]);
            System.out.println(" PIN: ***");
            System.out.println();
        }
    }

    private JFrame frame;

    public AdminSide() {
        // Load users from file when the program starts
        users = loadUsersFromFile();

        frame = new JFrame("Admin side");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Save users to file when the program exits
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveUsersToFile();
            }
        });

        Color panelcolor = new Color(245, 245, 245);// 34aan a8yr mara wahda fkolo
        Color buttoncolor = new Color(0, 120, 215);
        Color buttontextcolor = Color.white;
        Dimension fieldsize = new Dimension(125, 25);
        
        

        JPanel addpanel = new JPanel(new GridLayout(5, 2));
        addpanel.setBorder(BorderFactory.createTitledBorder("Adduser"));
        addpanel.setBackground(panelcolor);

        JLabel namelabel = new JLabel("Name:");
        JTextField namefield = new JTextField();
        namefield.setPreferredSize(fieldsize);

        JLabel accountnumberlabel = new JLabel("Account Number:");
        JTextField accountnumberfield = new JTextField();
        accountnumberfield.setPreferredSize(fieldsize);

        JLabel balancelabel = new JLabel("intial balance:");
        JTextField balancefield = new JTextField();
        balancefield.setPreferredSize(fieldsize);

        JLabel pinlabel = new JLabel("PIN");
        JPasswordField pinfield = new JPasswordField();
        pinfield.setPreferredSize(fieldsize);

        addpanel.add(namelabel);
        addpanel.add(namefield);
        addpanel.add(accountnumberlabel);
        addpanel.add(accountnumberfield);
        addpanel.add(balancelabel);
        addpanel.add(balancefield);
        addpanel.add(pinlabel);
        addpanel.add(pinfield);

        JButton addbutton = new JButton("Add User");
        addpanel.add(addbutton);
        addbutton.setBackground(buttoncolor);
        addbutton.setForeground(buttontextcolor);
        addbutton.setPreferredSize(new Dimension(120, 30));

        namefield.addActionListener(e -> accountnumberfield.requestFocus());// 34qn lm ados enter ywdeny 3l b3do
        accountnumberfield.addActionListener(e -> balancefield.requestFocus());
        balancefield.addActionListener(e -> pinfield.requestFocus());
        pinfield.addActionListener(e -> addbutton.requestFocus());

        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namefield.getText();
                String accountnumber = accountnumberfield.getText();
                String balance = balancefield.getText();
                String pin = pinfield.getText();

                if (name.isEmpty() || accountnumber.isEmpty() || balance.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "all fields required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] newUser = { name, accountnumber, balance, pin };
                users.add(newUser);

                namefield.setText("");
                accountnumberfield.setText("");
                balancefield.setText("");
                pinfield.setText("");

                JOptionPane.showMessageDialog(frame, "user added successfully!!!!!!");
            }
        });
         
          
        

        // JPanel viewallusers=new JPanel(new GridLayout(8,2)
        JPanel viewalluserspanel = new JPanel(new BorderLayout());//view all users
        viewalluserspanel.setBorder(BorderFactory.createTitledBorder("view users"));
        viewalluserspanel.setBackground(panelcolor);

        JTextArea textarea = new JTextArea();
        textarea.setEditable(false);
        JScrollPane scrollpane = new JScrollPane(textarea);

        JButton viewbutton = new JButton("view users");
        viewbutton.setBackground(buttoncolor);
        viewbutton.setForeground(buttontextcolor);
        viewbutton.setPreferredSize(new Dimension(120, 30));

        viewbutton.addActionListener(e -> {
            String result = ""; // start with empty string
            if (users.isEmpty()) {
                result = "no user found";
            } else {
                for (String[] user : users) {
                    result += "User details:\n";
                    result += "name:" + user[0] + " \n";
                    result += "Account number:" + user[1] + " \n";
                    result += "Balance:" + user[2] + " \n";
                    result += "PIN: ***" + "\n";
                }
            }
            textarea.setText(result);

        });
        
        
                viewalluserspanel.add(viewbutton, BorderLayout.NORTH);
        viewalluserspanel.add(scrollpane, BorderLayout.CENTER);  
        
        
        
        JPanel updatepanel = new JPanel(new GridLayout(4, 2)); //update
        
        updatepanel.setBackground(panelcolor);
        updatepanel.setBorder(BorderFactory.createTitledBorder("update"));

        JLabel updateaccountnumberlabel = new JLabel("Account Number:");
        JTextField updateaccountnumberfield = new JTextField();
        updatepanel.setPreferredSize(fieldsize);

        JLabel updatenamelabel = new JLabel("New Name:");
        JTextField updatenamefield = new JTextField();
        updatenamefield.setPreferredSize(fieldsize);

        JLabel updatepinlabel = new JLabel("New PIN:");
        JPasswordField updatepinfield = new JPasswordField();
        updatepinfield.setPreferredSize(fieldsize);

        JButton updateButton = new JButton("Update User");
        updateButton.setBackground(buttoncolor);
        updateButton.setForeground(buttontextcolor);
        updateButton.setPreferredSize(new Dimension(120, 30));

        updateaccountnumberfield.addActionListener(e -> updatenamefield.requestFocus());// 34qn lm ados enter ywdeny 3l
                                                                                        // b3do
        updatenamefield.addActionListener(e -> updatepinfield.requestFocus());
        updatepinfield.addActionListener(e -> updateButton.requestFocus());

        updateButton.addActionListener(e -> {
            String accountnumber = updateaccountnumberfield.getText();
            String newname = updatenamefield.getText();
            String newpin = updatepinfield.getText();

            if (accountnumber.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Account Number is required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean userFound = false;

            for (String[] user : users) {
                if (user[1].equals(accountnumber)) {
                    userFound = true;

                    if (!newname.isEmpty()) {
                        user[0] = newname;
                    }
                    if (!newpin.isEmpty()) {
                        user[3] = newpin;
                    }

                    JOptionPane.showMessageDialog(frame, "user details updated successfully!");
                    break;
                }
            }

            if (!userFound) {
                JOptionPane.showMessageDialog(frame, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
            }

            updateaccountnumberfield.setText("");
            updatenamefield.setText("");
            updatepinfield.setText("");
        });

        updatepanel.add(updateaccountnumberlabel);
        updatepanel.add(updateaccountnumberfield);
        updatepanel.add(updatenamelabel);
        updatepanel.add(updatenamefield);
        updatepanel.add(updatepinlabel);
        updatepanel.add(updatepinfield);
        updatepanel.add(updateButton);

        JPanel deletePanel = new JPanel(new GridLayout(5, 2));
        deletePanel.setBorder(BorderFactory.createTitledBorder("delete"));
        deletePanel.setBackground(panelcolor);

        JLabel deleteAccountNumberLabel = new JLabel("Account Number to Delete:");
        JTextField deleteAccountNumberField = new JTextField();
        deleteAccountNumberField.setPreferredSize(fieldsize);

        JButton deleteButton = new JButton("Delete User");
        deleteButton.setBackground(buttoncolor);
        deleteButton.setForeground(buttontextcolor);
        deleteButton.setPreferredSize(new Dimension(120, 30));

        deleteButton.addActionListener(e -> {
            String accountNumber = deleteAccountNumberField.getText();

            if (accountNumber.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Account Number is required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean userDeleted = false;

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i)[1].equals(accountNumber)) {
                    users.remove(i);
                    userDeleted = true;
                    break;
                }
            }

            if (userDeleted) {
                JOptionPane.showMessageDialog(frame, "User deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            deleteAccountNumberField.setText("");
        });

        deletePanel.add(deleteAccountNumberLabel);
        deletePanel.add(deleteAccountNumberField);
        deletePanel.add(deleteButton);
        
        
        
        
        JPanel searchPanel = new JPanel(new GridLayout(3, 2)); // to search
        
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search User"));
        searchPanel.setBackground(panelcolor);

        JLabel searchAccountNumberLabel = new JLabel("Enter Account Number:");
        JTextField searchAccountNumberField = new JTextField();
        JButton searchButton = new JButton("Search User");
        searchButton.setBackground(buttoncolor);
        searchButton.setForeground(buttontextcolor);

        searchButton.addActionListener(e -> {
            String accountNumber = searchAccountNumberField.getText();

            if (accountNumber.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Account Number is required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean userFound = false;
            for (String[] user : users) {
                if (user[1].equals(accountNumber)) {
                    JOptionPane.showMessageDialog(frame,
                            "User Details:\n" +
                            "Name: " + user[0] + "\n" +
                            "Account Number: " + user[1] + "\n" +
                            "Balance: " + user[2] + "\n" +
                            "PIN: ***",
                            "User Found", JOptionPane.INFORMATION_MESSAGE);
                    userFound = true;
                    break;
                }
            }

            if (!userFound) {
                JOptionPane.showMessageDialog(frame, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            searchAccountNumberField.setText("");
        });

        searchPanel.add(searchAccountNumberLabel);
        searchPanel.add(searchAccountNumberField);
        searchPanel.add(searchButton);
         
    JPanel resetpinpanel = new JPanel(new GridLayout(5, 2));// resest pin
    
    
resetpinpanel.setBorder(BorderFactory.createTitledBorder("Reset PIN"));
resetpinpanel.setBackground(panelcolor);

JLabel resetaccountnumberpanel = new JLabel("Account Number:");
JTextField resetAccountNumberField = new JTextField();

JLabel oldPinLabel = new JLabel("Old PIN:");
JTextField oldpinfield = new JTextField();

JLabel newpinpanel = new JLabel("New PIN:");
JTextField newpinfield = new JTextField();

JLabel confirmpinpanel = new JLabel("Confirm New PIN:");
JTextField confirmPinField = new JTextField();

JButton resetPinButton = new JButton("Reset PIN");
resetPinButton.setBackground(buttoncolor);
resetPinButton.setForeground(buttontextcolor);
resetPinButton.setPreferredSize(new Dimension(120, 30));


resetAccountNumberField.addActionListener(e -> oldpinfield.requestFocus());
oldpinfield.addActionListener(e -> newpinfield.requestFocus());
newpinfield.addActionListener(e -> confirmPinField.requestFocus());
confirmPinField.addActionListener(e -> resetPinButton.requestFocus());

resetPinButton.addActionListener(e -> {
    String accountNumber = resetAccountNumberField.getText();
    String oldPin = oldpinfield.getText();
    String newPin = newpinfield.getText();
    String confirmPin = confirmPinField.getText();

    if (accountNumber.isEmpty() || oldPin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    boolean userFound = false;
    for (String[] user : users) {
        if (user.length == 4 && user[1].equals(accountNumber)) {
            userFound = true;

            if (!user[3].equals(oldPin)) {
                JOptionPane.showMessageDialog(frame, "Invalid old PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(frame, "New PIN and confirmation do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            user[3] = newPin;
            JOptionPane.showMessageDialog(frame, "PIN reset successfully!");
            break;
        }
    }

    if (!userFound) {
        JOptionPane.showMessageDialog(frame, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

 
    resetAccountNumberField.setText("");
    oldpinfield.setText("");
    newpinfield.setText("");
    confirmPinField.setText("");
});

resetpinpanel.add(resetaccountnumberpanel);
resetpinpanel.add(resetAccountNumberField);
resetpinpanel.add(oldPinLabel);
resetpinpanel.add(oldpinfield);
resetpinpanel.add(newpinpanel);
resetpinpanel.add(newpinfield);
resetpinpanel.add(confirmpinpanel);
resetpinpanel.add(confirmPinField);
resetpinpanel.add(resetPinButton);
        
        
        
        
 

         // add all panels to Jtappedpane
        JTabbedPane tabbedpane = new JTabbedPane();
        tabbedpane.addTab("Adduser", addpanel);
        tabbedpane.addTab("viewusers", viewalluserspanel);
        tabbedpane.addTab("delete", deletePanel);
        tabbedpane.addTab("update", updatepanel);
        tabbedpane.addTab("search user",searchPanel );
        tabbedpane.addTab("reset",resetpinpanel );
        
        frame.add(tabbedpane, BorderLayout.CENTER);
        frame.setVisible(true);

    } 

    public void saveUsersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("admin_users.txt"))) {
            for (String[] user : users) {
                writer.println(String.join(",", user));
            }
            System.out.println("Users saved successfully to file.");
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    public ArrayList<String[]> loadUsersFromFile() {
        ArrayList<String[]> loadedUsers = new ArrayList<>();
        try (FileReader reader = new FileReader("users.txt")) {
            StringBuilder data = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                if (character == '\n') {
                    String[] user = data.toString().split(",");
                    if (user.length == 4) {
                        loadedUsers.add(user);
                    }
                    data.setLength(0); // Clear the StringBuilder for the next line
                } else {
                    data.append((char) character);
                }
            }
            // Handle the last line if it doesn't end with a newline
            if (data.length() > 0) {
                String[] user = data.toString().split(",");
                if (user.length == 4) {
                    loadedUsers.add(user);
                }
            }
            System.out.println("Users loaded successfully from file.");
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
        return loadedUsers;
   }
}
