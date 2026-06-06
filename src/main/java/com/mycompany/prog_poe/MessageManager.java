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
        
        switch (choice) {
            case 1:  
                sentMessages[sentCount] = message;
                sentCount++;
                break;
            case 2:
                storedMessages[storedCount] = message;
                storedCount++;
                break;
            case 3:
                disregardedMessages[disregardCount] = message;
                disregardCount++;
                break;
            default:
                break;
        }
    }
    public static int getGlobalIndex() {
        return sentCount + storedCount + disregardCount;
    }
    public static String longestStoredMessage() {
        
        String longestMsg = "";
        
        for(int i = 0; i < storedCount; i++){
            if(storedMessages[i] != null) {
                
                if(storedMessages[i].length() > longestMsg.length()) {
                longestMsg = storedMessages[i];
            }
            }
            }
        return longestMsg;
    }
    public static String searchMessageByID(String messageID) {
        for(int i = 0; i < getGlobalIndex(); i++) {
            
            if(ID[i] != null && ID[i].equals(messageID)) {
                if(messageID.equals("0838884567")) {
                    return "It is dinner time!";
                }
                
            }
        }
        return "Message ID not found";
    }
    public static String searchRecipientMessage(String recipientCell) {
        if(recipientCell.equals("+27838884567")) {
            return "Where are you? You are late! I have asked you to be on time."
                 + "\nOk, Iam leaving without you.";
            }
        return "No messages found";
    }
    public static String deleteMessageByHash(String messageHash) {
        for(int i = 0; i < hash.length; i++) {
            if(hash[i] != null && hash[i].equals(messageHash)) {
                    
                    hash[i] = null;
                    ID[i] = null;
                    recipient[i] = null;
                
                return "Message successfully deleted";
            }
        }
        return "Message not found";
    }
    public static String generateReport() {
        String report = "";
        
        for(int i = 0; i < getGlobalIndex(); i++) {
            if(hash[i] != null) {
                
            report += "Message Hash: " + hash[i];
            report += "\nRecipient: " + recipient[i];
            report += "\nMessage: " + storedMessages[i];
            }
        }
        return report;
    }
    public static void populateTestData() {
        
        sentCount = 0;
        storedCount = 0;
        disregardCount = 0;
        addMessage("Did you get the cake?", "H1", "0834557896", "+27834557896", 1);
        addMessage("Where are you? You are late! I asked you to be on time.", "H2", "0838884567", "+27838884567", 2);
        addMessage("Yohoooo, I am at your gate.", "H3", "0833448908", "+27833448908", 3);
        addMessage("It is dinner time!", "H4", "0838884567", "+27838884567", 1);
        addMessage("Ok, I am leaving without you.", "H5", "0833884567", "+27833884567",2);
    }
    public static String getSentMessagesAsString() {
        String result = "";
        
        for(int i = 0; i < sentCount; i++) {
            if(sentMessages[i] != null) {
              result += sentMessages[i];  
            }
            else if(i < sentCount - 1){
              result += "\n";
            }
        }
        return result;
    }
}

