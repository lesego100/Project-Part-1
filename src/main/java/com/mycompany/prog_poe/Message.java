package com.mycompany.prog_poe;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Message {
    //declare message attributes which can only be accessed in this class
    private String messageID;
    private String recipientCell;
    private String message;
    private String messageHash;
    //static variables to track the total number of messages sent and are shared by all message objects
    private static int numberOfMessages = 0;
    private static String totalMessages = "";
    //random object used to generate message ID
    private Random id = new Random();
    //regex constant for the recipient's cell phone number
    private static final String RECIPIENT_CELL_REGEX = "^\\+27\\d{9}$";
    //scanner passed from the main class and this helps avoid multiple scanner issues
    private final Scanner input;
    //getter to help call variable in other classes
    public String getRecipientCell() {
        return recipientCell;
    }
    /**
     *
     * @param input
     */
    //constructor to help receive the scanner from the main class
    public Message(Scanner input) {
        this.input = input;
    }
    
    //method to retrieve and process message details
    public void retrieveMessage() {
        
        //program generates unique message ID
        generateMessageID();
        
        //input recipient's cell phone number
        System.out.print("\n");
        System.out.println("Enter recipient cell phone number: ");
        recipientCell = input.nextLine();
        
        //loop which validates the entered recipient's cell phone number using regex
        while(checkRecipientCell(recipientCell).equals("Invalid cell phone number")) {
            System.out.println("Invalid cell phone number");
            System.out.println("\nRe-enter cell phone number: ");
            recipientCell = input.nextLine();
        }
        //input message
        System.out.println("Write message(max 250 characters): ");
        message = input.nextLine();
        
        //loop which validates the length of the message
        while(message.length() > 250) {
            System.out.println("Message exceeds 250 characters; re-enter message.");
            message = input.nextLine();
            
        }
        //program generates message hash after the validation process is complete
        messageHash = generateMessageHash();
        
        //user will pick an option for what they want to do with the message
        System.out.println("\n1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message To Send Later");
        System.out.print("User's option: ");
        //reads user's option
        int choice = input.nextInt();
        input.nextLine();
        
        //process user's input and displays result
        System.out.println(sentMessage(choice));
        
}
    //generates random message ID
    public void generateMessageID() {
        messageID = String.valueOf(id.nextInt(100000000));
    }
    //validates generated message ID
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    //validates entered recipient cell phone number
    public String checkRecipientCell(String recipientCell) {
        if(recipientCell.matches(RECIPIENT_CELL_REGEX)) {
            return "Valid cell phone number";
        }
        else {
            return "Invalid cell phone number";
        }
    }
    //generates message hash using message ID and message content
    public String generateMessageHash() {
        //split message into words
        String[] words = message.trim().replaceAll("[^a-zA-Z0-9 ]", "").split(" ");
        //first and last words of the entered message will be in capital letters
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        if(words.length == 1) {
            lastWord = firstWord;
        }
        //first two digits of message ID
        String msgID = messageID.substring(0,2);
        //returns message hash in the correct format
        return (msgID + ":" + numberOfMessages + ":" + firstWord + lastWord).toUpperCase();
    }
    //manages the send, store, and disregard logic 
    public String sentMessage(int choice) {
        
        switch(choice) {
            
            //send message
            case 1:
                numberOfMessages++;
                
                System.out.println("Message ID: " + messageID);
                System.out.println("Message Hash: " + messageHash);
                System.out.println("Recipient Cell: " + recipientCell);
                System.out.println("Message: " + message);
                
                totalMessages += "\nMessage ID: " + messageID;
                totalMessages += "\nMessage Hash: " + messageHash;
                totalMessages += "\nRecipient Cell: " + recipientCell;
                totalMessages += "\nMessage: " + message;
                storeMessages();
                
                MessageManager.addMessage(message, messageHash, messageID, recipientCell, choice);
                
                return "Message sent successfully.";
            
            case 2:
                MessageManager.addMessage(message, messageHash, messageID, recipientCell, choice);
                
                return "Press 0 to delete message.";
            
            //stores entered message for later
            case 3: 
                MessageManager.addMessage(message, messageHash, messageID, recipientCell, choice);
                
                return "Message stored successfully.";
            //program displays this if user's choice is incorrect    
            default:
                return "Invalid option.";
                
        }
    }
    //returns all messages sent
    public String printMessages() {
        return totalMessages;
    }
    //returns the total number of messages sent
    public static int returnTotalMessages() {
        return numberOfMessages;
    }
    //store message details in a JSON file
    public void storeMessages() {
        
        try {
            //open JSON file and add new data without deleting existing content
            FileWriter json = new FileWriter("messages.json", true);
            
            //write JSON file structure
            json.write("{\n");
            json.write("\"Message ID\": \"" + messageID + "\",\n");
            json.write("\"Recipient\": \"" + recipientCell + "\",\n");
            json.write("\"Message\": \"" + message + "\",\n");
            json.write("\"Hash\": \"" + messageHash + "\"\n");
            json.write("}\n");
        }
        catch(IOException e) {
            System.out.println("Failed to store message.");
        }
    }
}
