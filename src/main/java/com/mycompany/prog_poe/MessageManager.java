package com.mycompany.prog_poe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MessageManager {
    //create parallel arrays
    //array to store all sent messages
    static String[] sentMessages = new String[50];
    //array to store all stored messages
    static String[] storedMessages = new String[50];
    //array to store all disregarded messages
    static String[] disregardedMessages = new String[50];
    
    //parallel used to store all message details
    static String[] hash = new String[50];
    static String[] ID = new String[50];
    static String[] recipient = new String[50];
    
    //counters used to track all different message types
    static int sentCount = 0;
    static int storedCount = 0;
    static int disregardCount = 0;
    
    //this tracks the next available position in arrays
    static int globalIndex = 0;
    
    /**
     * Adds a message and its details to the correct array based on whether it
     * was sent, stored, or disregarded
     * @param message
     * @param messageHash
     * @param messageID
     * @param recipientCell
     * @param choice 
     */
    public static void addMessage(String message, String messageHash, String messageID, String recipientCell, int choice ) {
        
        //indicates the next available array position
        int index = globalIndex++;
        
        //store similar message details
        hash[index] = messageHash;
        ID[index] = messageID;
        recipient[index] = recipientCell;
        
        if(choice == 1) {
            sentMessages[index] = message;
        }
        else if(choice == 2) {
            disregardedMessages[index] = message;
        }
        else if(choice == 3) {
            storedMessages[index] = message;
        }
    }
    
    //methods finds and returns the longest message that was stored
    public static String longestStoredMessage() {
        
        String longestMsg = "";
        
        for(int i = 0; i < globalIndex; i++){
            if(storedMessages[i] != null) {
                if(longestMsg.equals("") || storedMessages[i].length() > longestMsg.length()) {
                longestMsg = storedMessages[i];
               }
            }
        }
        return longestMsg;
    }
    
    //searches for a stored message using its message ID
    public static String searchMessageByID(String messageID) {
        for(int i = 0; i < globalIndex; i++) {
            if(ID[i] != null && ID[i].equals(messageID) && storedMessages[i] != null) {
                
               return "Recipient: " + recipient[i] + "\nMessage: " + storedMessages[i];    
               }
                
            }
        return "Message ID not found";
    }
    
    //returns all stored messages linked to a specific recipient
    public static String searchRecipientMessage(String recipientCell) {
        String result = "";
        for(int i = 0; i < globalIndex; i++) {
                    if(storedMessages[i] != null) {
                        if(recipient[i] != null && recipient[i].equals(recipientCell)) {
                    result += storedMessages[i] + "\n";
                }
                    }
        }
        if(result.equals("")) {
            return "No messages found";
        }
        return result;
    }
    
    //deletes a stored message using its message hash and moves the remaining array elements to prevent gaps
    public static String deleteMessageByHash(String messageHash) {
        for(int i = 0; i < globalIndex; i++) {
            if(hash[i] != null && hash[i].equals(messageHash)) {
                for(int x = i; x < globalIndex - 1; x++) {
                    
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
                    
                    globalIndex--;
                    
                    return "Message successfully deleted";
            }
        }
        return "Message not found";
    }
    
    //generates a report containing all the details of the stored messages
    public static String generateReport() {
        String report = "";
        
        for(int i = 0; i < globalIndex; i++) {
           if(storedMessages[i] != null) {
                report += "\nMessage ID: " + ID[i];
                report += "\nMessage Hash: " + hash[i];
                report += "\nRecipient: " + recipient[i];
                report += "\nMessage: " + storedMessages[i];
            }
            report += "\n---------------------\n";
           }
        return report;
    }
    
    //populates arrays with already given test data required for the unit tests
    public static void populateTestData() {
        
        sentCount = 0;
        storedCount = 0;
        disregardCount = 0;
        globalIndex = 0;
        
        addMessage("Did you get the cake?", "H1", "0834557896", "+27834557896", 1);
        addMessage("Where are you? You are late! I have asked you to be on time.", "H2", "0838884567", "+27838884567", 3);
        addMessage("Yohoooo, I am at your gate.", "H3", "0833448908", "+27833448908", 2);
        addMessage("It is dinner time!", "H4", "0838884567", "+27838884567", 1);
        addMessage("Ok, I am leaving without you.", "H5", "0833884567", "+27833884567",3);
    }
    
    //returns all test data as one formatted string
    public static String getSentMessagesAsString() {
        String result = "";
        
        boolean first = true;
        
        for(int i = 0; i < globalIndex; i++) {
            if(sentMessages[i] != null) { 
               if(!first){
              result += "\n";
               }
               result += sentMessages[i];
               first = false;
            }
        }
        return result;
    }
    
    //reads all stored messages from the JSON file and loads them into parallel arrays
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

