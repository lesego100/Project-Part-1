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
        int index = getGlobalIndex();
        
        hash[index] = messageHash;
        ID[index] = messageID;
        recipient[index] = recipientCell;
        
        if(choice == 1) {
            sentMessages[sentCount++] = message;  
        }
        else if(choice == 2) {
            storedMessages[storedCount++] = message;
        }
        else if (choice == 3) {
            disregardedMessages[disregardCount++] = message;
        }  
    }
    public static int getGlobalIndex() {
        return sentCount + storedCount + disregardCount;
    }
    public static String longestStoredMessage() {
        
        String longestMsg = "";
        
        for(int i = 0; i < storedCount; i++){
            if(storedMessages[i] != null && (longestMsg.equals("") || storedMessages[i].length() > longestMsg.length())) {
                longestMsg = storedMessages[i];
            }
        }
        return longestMsg;
    }
    public static String searchMessageByID(String messageID) {
        
        for(int i = 0; i < getGlobalIndex(); i++) {
            if(ID[i] != null && ID[i].equals(messageID)) {
                return storedMessages[i];
            }
        }
        return "Message ID not found.";
    }
    public static String searchRecipientMessage(String recipientCell) {
        String result = "";
        
        for(int i = 0; i < getGlobalIndex(); i++) {
            if(recipient[i] != null && recipient[i].equals(recipientCell) && storedMessages[i] != null) {
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
                return "Message successfully deleted";
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
    public static void populateTestData() {
        
        addMessage("Did you get the cake?", "H1", "08334557896", "+27834557896", 1);
        
        addMessage("Where are you? You are late! I have asked you to be on time.", "H2", "0838884567", "+27838884567", 2);
        
        addMessage("Yohoooo, I am at your gate.", "H3", "0833448908", "+27746893676", 3);
        
        addMessage("It is dinner time!", "H4", "0838884567", "+27833448567", 1);
        
        addMessage("Ok, I am leaving without you.", "H5","0833884567", "+27833884567", 2);
        
    }
    public static String getSentMessagesAsString() {
        String result = "";
        
        for(int i = 0; i < sentCount; i++) {
            if(sentMessages[i] != null) {
              result += sentMessages[i];  
            }
            else {
              result += sentMessages[i] + "\n";
            }
        }
        return result;
    }

}
