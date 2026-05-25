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
        System.out.println("3. Quit");
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
            //choice to exit the application and displays total number of messages sent
            case 3:
                Message msg = new Message(input);
                System.out.println("\n ");
                System.out.println("Total messages sent: " + msg.returnTotalMessages());
                
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option; Try again.");
        }
        }
        //program will continue while the choice is not number 3
        while(choice != 3);
        }
        //if login is not successful, program will display the following message
        else {
            System.out.println("Login failed.");
        }
    }
}
