package com.mycompany.prog_poe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MessageManager {
    //create parallel array list
    static String[] sentMessages = new String[50];
    static String[] storedMessages = new String[50];
    static String[] disregardedMessages = new String[50];
    
    static String[] hash = new String[50];
    static String[] ID = new String[50];
    static String[] recipient = new String[50];
    
    static int sentCount = 0;
    static int storedCount = 0;
    static int disregardCount = 0;
    
    static int globalIndex = 0;
    
    public static void addMessage(String message, String messageHash, String messageID, String recipientCell, int choice ) {
        
        int index = globalIndex++;
        
        hash[index] = messageHash;
        ID[index] = messageID;
        recipient[index] = recipientCell;
        
        if(choice == 1) {
            sentMessages[index] = message;
        }
        else if(choice == 2) {
            storedMessages[index] = message;
        }
        else if(choice == 3) {
            disregardedMessages[index] = message;
        }
        System.out.println("Choice = " + choice);
        System.out.println("Index = " + index);
        System.out.println("Stored = " + storedMessages[index]);
    }
    
    public static String longestStoredMessage() {
        
        String longestMsg = "";
        
        for(int i = 0; i < globalIndex; i++){
            if(storedMessages[i] != null) {
                if(storedMessages[i].length() > longestMsg.length()) {
                longestMsg = storedMessages[i];
               }
            }
        }
        return longestMsg;
    }
    public static String searchMessageByID(String messageID) {
        for(int i = 0; i < globalIndex; i++) {
            if(ID[i] != null && ID[i].equals(messageID)) {
                
               System.out.println("Recipient: " + recipient[i]);
               
               if(sentMessages[i] != null) {
                   return sentMessages[i];
               }
               if(storedMessages[i] != null) {
                   return storedMessages[i];
               }
               if(disregardedMessages[i] !=  null) {
                   return disregardedMessages[i];
                   
               }
                
            }
        }
        return "Message ID not found";
    }
    public static String searchRecipientMessage(String recipientCell) {
        String result = "";
        for(int i = 0; i < globalIndex; i++) {
            if(recipient[i] != null && recipient[i].equals(recipientCell)) {
                if(storedMessages[i] != null) {
                    result += storedMessages[i] + "\n";
                }
            }
        }
        if(result.equals("")) {
            return "No messages found";
        }
        return result;
    }
    public static String deleteMessageByHash(String messageHash) {
        for(int i = 0; i < globalIndex; i++) {
            if(hash[i] != null && hash[i].equals(messageHash)) {
                for(int x = 0; x < globalIndex; x++) {
                    
                    hash[x] = hash[x + 1];
                    ID[x] = ID[x + 1];
                    recipient[x] = recipient[x + 1];
                    
                    sentMessages[x] = sentMessages[x + 1];
                    storedMessages[x] = storedMessages[x + 1];
                    disregardedMessages[x] = disregardedMessages[x + 1];
                }
                int last = globalIndex - 1;
                
                hash[last] = null;
                    ID[last] = null;
                    recipient[last] = null;
                    
                    sentMessages[last] = null;
                    storedMessages[last] = null;
                    disregardedMessages[last] = null;
                    
                    globalIndex = globalIndex -1;
                    
                    return "Message successfully deleted";
                
            }
        }
        return "Message not found";
    }
    public static String generateReport() {
        String report = "";
        
        for(int i = 0; i < globalIndex; i++) {
           if(hash[i] != null) {
                
            report += "\nMessage Hash: " + hash[i];
            report += "\nRecipient: " + recipient[i];
            report += "\nMessage: " + storedMessages[i];
            System.out.println("\n");
            
            if(sentMessages[i] != null ) {
                report += "Message: " + sentMessages[i];
            }
            if(storedMessages[i] != null) {
                report += "\nMessage: " + storedMessages[i];
            }
            if(disregardedMessages[i] != null) {
                report += "\nMessage: " + disregardedMessages[i];
            }
           }
        }
        return report;
    }
    public static void populateTestData() {
        
        sentCount = 0;
        storedCount = 0;
        disregardCount = 0;
        globalIndex = 0;
        
        addMessage("Did you get the cake?", "H1", "0834557896", "+27834557896", 1);
        addMessage("Where are you? You are late! I have asked you to be on time.", "H2", "0838884567", "+27838884567", 2);
        addMessage("Yohoooo, I am at your gate.", "H3", "0833448908", "+27833448908", 3);
        addMessage("It is dinner time!", "H4", "0838884567", "+27838884567", 1);
        addMessage("Ok, I am leaving without you.", "H5", "0833884567", "+27833884567",2);
    }
    public static String getSentMessagesAsString() {
        String result = "";
        
        for(int i = 0; i < sentCount; i++) {
            if(sentMessages[i] != null) {
              result += sentMessages[i];  
               if(i < sentCount - 1){
              result += "\n";
               }
            }
        }
        return result;
    }
    public static void loadStoredMessagesFromJSON() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("messages.json"));
            String line;
            
            int index = 0;
            
            String currentID = "";
            String currentRecipient = "";
            String currentMessage = "";
            String currentHash = "";
            
            while((line = reader.readLine()) != null) {
                line = line.trim();
                
                if(line.startsWith("\"Message ID\"")) {
                    currentID = line.substring(line.indexOf(":") + 3, line.lastIndexOf("\""));
                }
                if(line.startsWith("\"Recipient\"")) {
                    currentRecipient = line.substring(line.indexOf(":") + 3, line.lastIndexOf("\""));
                }
                if(line.startsWith("\"Message\"")) {
                    currentMessage = line.substring(line.indexOf(":") + 3, line.lastIndexOf("\""));
                }
                if(line.startsWith("\"Hash\"")) {
                    currentHash = line.substring(line.indexOf(":") + 3, line.lastIndexOf("\""));
                    
                    storedMessages[index] = currentMessage;
                    ID[index] = currentID;
                    recipient[index] = currentRecipient;
                    hash[index] = currentHash;
                    
                    index = index + 1;
                }
            }
            storedCount = index;
            reader.close();
                
        }
        catch(IOException e) {
            System.out.println("Error reading messages.json");
        }
    }
}

