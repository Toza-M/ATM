
package com.mycompany.atmsim;

import java.util.Scanner;
import java.util.ArrayList;

public class AdminSide {
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
}
 
  
