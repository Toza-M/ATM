
package com.mycompany.atmsim;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;



public class AdminSide extends JFrame {
 private Scanner scanner=new Scanner(System.in); 
 private ArrayList<String[]> users=new ArrayList<>(); // array list  
 
 public void adduser(){
     System.out.println("add user");
     System.out.println("Enter ur name");
     
     String name=scanner.nextLine();
      System.out.println("Enter account number");
      String accountnumber=scanner.nextLine();
      
       System.out.println("Enter ur balance");
        String balance=scanner.nextLine();
        
        System.out.println("Enter pin");
         String pin=scanner.nextLine();
         
         
          String[] newuser=new String[4];
         newuser[0]=name;
         newuser[1]=accountnumber;
         newuser[2]=String.valueOf(balance);
         newuser[3]=pin;
         users.add(newuser);
         System.out.println("user added succssfully");
         System.out.println("ur account number is " + accountnumber);
         
    }
 
 public void updateuser(){
 
 System.out.println("update user details");
 System.out.println("enter ur number account number to update");
 String accountnumber=scanner.nextLine();
 
 String[] finduser=null;
for(String[] user:users){
if (user[1].equals(accountnumber)){
    finduser=user;
    break;}
}
if(finduser==null){
    
System.out.println("user not found");
return;

}
System.out.println("enter ur new name");
String newname=scanner.nextLine();
if(!newname.isEmpty()){
finduser[0]=newname;
}
System.out.println("enter ur new pin");
String newpin=scanner.nextLine();
if(!newpin.isEmpty()){
finduser[3]=newpin;
}
System.out.println("user details updated");

    
}
 
 
 public void deleteuser(){
     
     System.out.println("delete user");
     System.out.println("enter ur account number to delete");
     String accountnumber=scanner.nextLine();
     boolean deleted=false;
     for(int i=0;i<users.size();i++){
         if(users.get(i)[1].equals(accountnumber)){
         users.remove(i);
         deleted=true;
         break;
         
        }
    }
     if(deleted){
     System.out.println("user deleted succsefully");
     }
     else {
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

    AdminSide(){

frame=new JFrame("Admin side");
frame.setSize(600, 500);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());

Color panel = new Color(245, 245, 245);// 34aan a8yr mara wahda fkolo
Color button = new Color(0, 120, 215);
Color buttontextcolor = Color.white;
Dimension fieldsize = new Dimension(125, 25);


JPanel inputpanel = new JPanel(new GridLayout(5, 2));
inputpanel.setBorder(BorderFactory.createTitledBorder("Adduser"));
inputpanel.setBackground(panel);


        
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

      
       
       
        inputpanel.add(namelabel);
        inputpanel.add(namefield);
        inputpanel.add(accountnumberlabel);
        inputpanel.add(accountnumberfield);
        inputpanel.add(balancelabel);
        inputpanel.add(balancefield);
        inputpanel.add(pinlabel);
        inputpanel.add(pinfield);
        
                
        
        
        JButton addbutton = new JButton("Add User");
        addbutton.setBackground(button);
        addbutton.setForeground(buttontextcolor);
        addbutton.setPreferredSize(new Dimension(120,30));
        
        
         namefield.addActionListener(e ->accountnumberfield.requestFocus());// 34qn lm ados enter ywdeny 3l b3do
           accountnumberfield.addActionListener(e ->balancefield.requestFocus());
              balancefield.addActionListener(e ->pinfield.requestFocus());
                 pinfield.addActionListener(e ->addbutton.requestFocus());
                
        addbutton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namefield.getText();
                String accountnumber = accountnumberfield.getText();
                String balance = balancefield.getText();
                String pin = pinfield.getText();

              
                if (name.isEmpty() || accountnumber.isEmpty() || balance.isEmpty() || pin.isEmpty()) 
                {
                    JOptionPane.showMessageDialog(frame, "all fields required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
        String[] newUser = {name, accountnumber, balance, pin};
        users.add(newUser);

                
                namefield.setText("");
                accountnumberfield.setText("");
                balancefield.setText("");
                pinfield.setText("");

              
                JOptionPane.showMessageDialog(frame, "user added successfully!!!!!!");
            }
        });
        
//        JPanel viewallusers=new JPanel(new GridLayout(8,2)
        JPanel centralpanel=new JPanel(new BorderLayout());
        centralpanel.setBorder(BorderFactory.createTitledBorder("view users"));
        centralpanel.setBackground(panel);
        
        JTextArea textarea=new JTextArea();
        textarea.setEditable(false);
        JScrollPane scrollpane=new JScrollPane (textarea);
        
        
        JButton viewbutton=new JButton("view users");
        viewbutton.setBackground(button);
        viewbutton.setForeground(buttontextcolor);
        viewbutton.setPreferredSize(new Dimension(120,30));
        
        viewbutton.addActionListener(e ->
        {
           String result= ""; //start with empty string
        if(users.isEmpty()){
           result="no user found";
           }
          else{
           for(String[]user:users){
               result+="User details:\n";
               result+="name:" +user[0] +" \n";
                result+="Account number:" +user[1] +" \n";
                 result+="Balance:" +user[2] +" \n";
                  result+="PIN: ***" + "\n"; 
          }
           }
        textarea.setText(result);
        
 });
        JPanel updatepanel = new JPanel(new GridLayout(4, 2));
        updatepanel.setBackground(panel);
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
        updateButton.setBackground(button);
        updateButton.setForeground(buttontextcolor);
        updateButton.setPreferredSize(new Dimension(120,30));
        
        
         updateaccountnumberfield.addActionListener(e ->updatenamefield.requestFocus());// 34qn lm ados enter ywdeny 3l b3do
         updatenamefield.addActionListener(e ->updatepinfield.requestFocus());
         updatepinfield.addActionListener(e ->updateButton.requestFocus());
              
                
    
      
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
         deletePanel.setBackground(panel);

        JLabel deleteAccountNumberLabel = new JLabel("Account Number to Delete:");
        JTextField deleteAccountNumberField = new JTextField();
        deleteAccountNumberField.setPreferredSize(fieldsize);

        JButton deleteButton = new JButton("Delete User");
        deleteButton.setBackground(button);
        deleteButton.setForeground(buttontextcolor);
        deleteButton.setPreferredSize(new Dimension(120,30));
    
      
        
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
        
        
        
                inputpanel.add(addbutton);
                
                 centralpanel.add(viewbutton , BorderLayout.NORTH);
                 centralpanel.add(scrollpane , BorderLayout.CENTER);
                 
                 JTabbedPane tabbedpane=new JTabbedPane();
               tabbedpane.addTab("Adduser",inputpanel);
               tabbedpane.addTab("viewusers",centralpanel);
                tabbedpane.addTab("delete",deletePanel);
                tabbedpane.addTab("update",updatepanel);
                
                frame.add(tabbedpane,BorderLayout.CENTER);
                
                frame.setVisible(true);
           
                
                
                 
     }
    
}


  


 
 
  
