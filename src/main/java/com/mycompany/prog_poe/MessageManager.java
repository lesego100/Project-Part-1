package com.mycompany.prog_poe;
import java.util.ArrayList;
public class MessageManager {
    //
    static String[] sentMessages = new String[50];
    static String[] storedMessages = new String[50];
    static String [] disregardedMessages = new String [50];
    
    static String[] hash = new String[50];
    static String[] ID = new String[50];
    static String[] recipient = new String[50];
    
    static int sentCount = 0;
    static int storedCount = 0;
    static int disregardCount = 0;
    
    public static void addMessage(String message, String messageHash, String messageID, String recipientCell, int choice ) {
        hash[getGlobalIndex()] = messageHash;
        ID[getGlobalIndex()] = messageID;
        recipient[getGlobalIndex()] = recipientCell;
        
        if(choice == 1) {
            sentMessages[sentCount] = message;
            sentCount++;
        }
        else if(choice == 2) {
            sentMessages[storedCount] = message;
            storedCount++;
        }
        else if (choice == 3) {
            sentMessages[disregardCount] = message;
            disregardCount++;
        }  
    }
    public static int getGlobalIndex() {
        return sentCount + storedCount + disregardCount;
    }
    public static String longestStoredMessage() {
        
        String longestMsg = storedMessages[0];
        
        for(int i = 1; i < storedCount; i++){
            if(storedMessages[i].length() > longestMsg.length()) {
                longestMsg = storedMessages[i];
            }
        }
        return longestMsg;
    }
    public static String searchMessageByID(String messageID) {
        for(int i = 0; i < storedCount; i++) {
            if(ID[i].equals(messageID)) {
                return "Recipient: " + recipient[i] + "\nMessage: " + storedMessages[i];
            }
        }
        return "Message ID not found.";
    }
    public static String searchRecipientMessage(String recipientCell) {
        String result = "";
        for(int i = 0; i < storedCount; i++) {
            if(recipient[i].equals(recipientCell)) {
                result += storedMessages[i];
            }
        }
        return result.equals("")? "No messages found" : result;
    }
    public static String deleteMessageByHash(String messageHash) {
        for(int i = 0; i < storedCount; i++) {
            if(hash[i].equals(messageHash)) {
                for(int count = i; count < storedCount - 1; count++) {
                    storedMessages[count] = storedMessages[count + 1];
                    hash[count] = hash[count + 1];
                    ID[count] = ID[count + 1];
                    recipient[count] = recipient[count + 1];
                }
                storedCount--;
                return "Message successfully deleted.";
            }
        }
        return "Message not found";
    }
    public static String generateReport() {
        String report = "";
        
        for(int i = 0; i < storedCount; i++) {
            report += "Message Hash: " + hash[i];
            report += "Recipient: " + recipient[i];
            report += "Message: " + storedMessages[i];
        }
        return report;
    }
}
