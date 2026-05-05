
package com.mycompany.prog_poe;
import java.util.Scanner;
public class PROG_POE {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //create a Registration object 
        Registration rgtObj = new Registration();
        //Register the user
        boolean registrationSuccess = rgtObj.registerUser();
        //only allow the user to proceed to login if registration was successful
        if(registrationSuccess) {
            
        }
        //create a Login object
        Login lgnObj = new Login(rgtObj);
        //Begin the login process
        boolean loginSuccess = lgnObj.loginUser();
        
        if(loginSuccess) {
            //display this message after the login is successful
            System.out.println("User logged in successfully!");
        }
        else {
            //otherwise, display this message if the login is unsucessful
            System.out.println("Login failed.");
        }
        if(loginSuccess) {
            System.out.println("\n ");
            System.out.println("Welcome to QuickChat");
            
        int choice;
        
        do {
        System.out.println("\n ");
        System.out.println("====== MENU ======");
        System.out.println("1. Send Messages");
        System.out.println("2. Display recently sent messages");
        System.out.println("3. Quit");
        System.out.print("User's option: ");
        choice = input.nextInt();
        
        switch(choice) {
            case 1:
                Message msgObj = new Message();
                msgObj.retrieveMessage();
                break;
            case 2:
                System.out.println("Coming soon!");
                break;
                
            case 3:
                Message msg = new Message();
                System.out.println("Total messages sent: " + msg.returnTotalMessages());
                
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
        }
        while(choice != 3);
        }
        else {
            System.out.println("Login failed.");
        }
    }
}
