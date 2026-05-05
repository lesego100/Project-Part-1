package com.mycompany.prog_poe;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Message {
    private String messageID;
    private String recipientCell;
    private String message;
    private String messageHash;
    private static int numberOfMessages = 0;
    private static String totalMessages = "";
    private Random id = new Random();
    
    private static final String RECIPIENT_CELL_REGEX = "^\\+27\\d{9}$";
    
    Scanner input = new Scanner(System.in);
    
    public void retrieveMessage() {
    
        generateMessageID();
        
        System.out.print("Enter recipient cell phone number: ");
        recipientCell = input.nextLine();
        
        while(checkRecipientCell(recipientCell).equals("Invalid cell phone number")) {
            System.out.println("Re-enter cell phone number: ");
            recipientCell = input.nextLine();
        }
        System.out.print("Write message(max 250 characters): ");
        message = input.nextLine();
        
        while(message.length() > 250) {
            System.out.println("Message exceed 250 characters; re-enter message.");
            message = input.nextLine();
        }
        messageHash = generateMessageHash();
        
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message To Send Later");
        System.out.println("User's option: ");
        int choice = input.nextInt();
        
        sentMessage(choice);
        
}
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    public void generateMessageID() {
        
        messageID = String.valueOf(id.nextInt(100000000));
    }
    public String checkRecipientCell(String recipientCell) {
        if(recipientCell.matches(RECIPIENT_CELL_REGEX)) {
            return "Valid cell phone number";
        }
        else {
            return "Invalid cell phone number";
        }
    }
    public String generateMessageHash() {
        String[] words = message.trim().split("");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String msgID = messageID.substring(0,2);
        return msgID + ":" + totalMessages + ":" + firstWord + " " + lastWord;
    }
    public String sentMessage(int choice) {
        
        switch(choice) {
            case 1:
                numberOfMessages = numberOfMessages + 1;
                
                totalMessages = totalMessages + "\nMessage ID: " + messageID;
                totalMessages = totalMessages + "\nRecipient: " + recipientCell;
                totalMessages = totalMessages + "\nMessage: " + message;
                totalMessages = totalMessages + "\nHash: " + messageHash;
                return "Message sent successfully";
            
            case 2:
                return "Press 0 to delete the message";
                
            case 3: 
                return "Message stored successfully";
                
            default:
                return "Invalid option.";
                
        }
    }
    public String printMessages() {
        return totalMessages;
    }
    public static int returnTotalMessages() {
        return numberOfMessages;
    }
    public void storeMessages() {
        
        try {
            FileWriter json = new FileWriter("messages.json", true);
            
            json.write("\"Message ID: \":\"" + messageID + "\",\n");
            json.write("\"Recipient: \":\"" + recipientCell + "\",\n");
            json.write("\"Message: \":\"" + message + "\",\n");
            json.write("\"Hash: \":\"" + messageHash + "\",\n");
        }
        catch(IOException e) {
            System.out.println("Failed to store message.");
        }
    }
}
