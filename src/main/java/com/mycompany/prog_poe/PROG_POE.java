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
        //User chooses how many messages they want to enter
        System.out.println("How many messages would you like to enter? ");
        int maxMessages = input.nextInt();
        input.nextLine();
        
        int messagesEntered = 0;    
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
                //Check if user has reached the limit
                if(messagesEntered < maxMessages) {
                    Message msgObj = new Message(input);
                    msgObj.retrieveMessage();
                    messagesEntered++;
                    
                    System.out.println("Messages entered: " + messagesEntered 
                                      +"/" + maxMessages);
                }
                else {
                    System.out.println("You have already entered the maximum"
                                     + " number of messages.");
                }
                break;
            //displays this message since the feature is still being developed
            case 2:
                System.out.println("Coming soon!");
                break;
            //chice to display stored messages menu
            case 3:
                System.out.println("\n====STORED MESSAGES MENU====");
                System.out.println("a. Sender and Recipient");//NEED TO FIX RECIEPIENT PART
                System.out.println("b. Longest stored message");//FIX: MUST BE STORED MESSAGE NOT SENT
                System.out.println("c. Search by Message ID");//FIX: MESSAGE ID MUST BE OF THE STORED MESSAGE
                System.out.println("d. Search by Recipient");//FIX: ONLY FINDS MESSAGE 2 
                System.out.println("e. Delete by Message Hash");//case to press zero to delete
                System.out.println("f. Message Report ");
                System.out.println("User's option: ");
                String option = input.nextLine();
                
                switch(option) {
                    case "a":
                            Message msgObj = new Message(input);
                            System.out.println("Sender: " + rgtObj.cellPhoneNumber 
                                              +"\nRecipient: " + msgObj.getRecipientCell());
                       break;
                    case "b":
                            System.out.println(MessageManager.longestStoredMessage());
                        break;
                    case "c":
                            System.out.println("Please enter Message ID to search: ");
                            String ID = input.nextLine();
                            
                            System.out.println(MessageManager.searchMessageByID(ID));
                       break;
                    case "d":
                            System.out.println("Please enter Recipient to search: ");//check this
                            String recipient = input.nextLine();
                            
                            System.out.println(MessageManager.searchRecipientMessage(recipient));
                        break;
                    case "e":
                            System.out.println("Please enter Message Hash to delete: ");
                            String hash = input.nextLine();
                            
                            System.out.println(MessageManager.deleteMessageByHash(hash));
                       break;
                    case "f":
                            System.out.println("\n====MESSAGE REPORT====");
                            System.out.println(MessageManager.generateReport());
                        break;  
                }
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
