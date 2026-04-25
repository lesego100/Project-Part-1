package com.mycompany.prog_poe;
public class Message {
    private String messageID;
    private String cellPhoneNumber;
    private String message;
    private String messageHash;
    static int numberOfMessages = 0;
    
public Message(String messageID, String cellPhoneNumber, String message, String messageHash) {
    this.messageID = messageID;
    this.cellPhoneNumber = cellPhoneNumber;
    this.message = message;
    this.messageHash = messageHash;
    numberOfMessages++;
    
    }
boolean checkMessageID(String messageID) {
    if(messageID.length() <= 10){
        return true;
}
    else {
        return false;
    }
}
}
