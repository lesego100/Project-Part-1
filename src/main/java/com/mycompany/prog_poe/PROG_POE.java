package com.mycompany.prog_poe;
import java.util.Scanner;
public class PROG_POE {

    public static void main(String[] args) {
        //Scanner object to allow user input across the program
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
        //Only allow access to the app once login is successful
        if(loginSuccess) {
            System.out.println("\n ");
            System.out.println("Welcome to QuickChat");
            
        int choice;
        //do-while menu loop which allows the user to choose from 3 options
        do {
        System.out.println("\n ");
        System.out.println("====== MENU ======");
        System.out.println("1. Send Messages");
        System.out.println("2. Display recently sent messages");
        System.out.println("3. Stored Messages");
        System.out.println("4. Quit");
        System.out.print("User's option: ");
        //this reads the user's input of their menu choice
        choice = input.nextInt();
        input.nextLine();
        
        
        switch(choice) {
            //choice to send a message
            case 1:
                Message msgObj = new Message(input);
                msgObj.retrieveMessage();
                break;
            //displays this message since the feature is still being developed
            case 2:
                System.out.println("Coming soon!");
                break;
            //chice to display stored messages menu
            case 3:
                System.out.println("\n====STORED MESSAGES MENU====");
                
                System.out.println("Longest store message: ");
                System.out.println(MessageManager.longestStoredMessage());
                
                System.out.println("\nMessage Report: ");
                System.out.println(MessageManager.generateReport());
                break;
            //choice to exit the application and displays total number of messages sent
            case 4:
                Message msg = new Message(input);
                System.out.println("\n ");
                System.out.println("Total messages sent: " + msg.returnTotalMessages());
                
                System.out.println("Exiting...");
                break;
            /**
             *if an invalid choice is picked from the menu, the program will 
             *then display the following message
             */
            default:
                System.out.println("Invalid option. Try again.");
        }
        }
        //program will continue while the choice is not number 4
        while(choice != 4);
        }
        //if login is not successful, program will display the following message
        else {
            System.out.println("Login failed.");
        }
    }
}
