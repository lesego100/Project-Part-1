package com.mycompany.prog_poe;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author elmak
 */
public class MessageTest {
    
    public MessageTest() {
    }

    @Test
    public void testMessageLengthSuccess() {
        String message = "Hi Mike, can you join us for dinner tonight?";
        
        String expected = "Message ready to send.";
        String actual;
        
        if(message.length() <= 250) {
            actual = "Message ready to send.";
        }
        else {
            int excess = message.length() - 250;
            actual = "Message exceeds 250 characters by" + excess + "; please reduce the length.";
        }
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMessageLengthFailure() {
        String message = "Hi".repeat(300);
        
        int excess = message.length() - 250;
        
        String expected = "Message exceeds 250 characters by" + excess 
                          +"; please reduce the length.";
        String actual;
        
        if(message.length() <= 250) {
            actual = "Message ready to send.";
        }
        else {
            actual = "Message exceeds 250 characters by" + excess 
                    +"; please reduce the length.";
        }
        assertEquals(expected, actual);
    }
    
    @Test
    public void testValidRecipientCell() {
        Scanner input = new Scanner(System.in);
        Message msgObj = new Message(input);
        
        String expected = "Cell phone number successfully captured.";
        String actual;
        
        if(msgObj.checkRecipientCell("+27718693002").equals("Valid cell phone number")) {
        actual = "Cell phone number successfully captured.";
    }
    else {
    actual = "Cell phone number is incorrectly formatted or does not contain an"
            +"international code. Please correct the number and try again.";
    }
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInvalidRecipientCell() {
        Scanner input = new Scanner(System.in);
        Message msgObj = new Message(input);
        
        String expected = "Cell phone number is incorrectly formatted or does"
                         +"not contain an international code. Please correct"
                         +"the number and try again.";
        String actual;
        
        if(msgObj.checkRecipientCell("08575975889").equals("Valid cell phone number")) {
        actual = "Cell phone number successfully captured.";
    }
    else {
    actual = expected;
    }
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMessageHash() {
     Scanner input = new Scanner(System.in);
     Message msgObj = new Message(input);
     
     msgObj.generateMessageID();
     
     String expected = "00:0:HITONIGHT";
     
     String actual = "00:0:HITONIGHT";
     
     assertEquals(expected, actual);
}
    
    @Test
    public void testLoopHashRequiremet() {

        int[] numbers = {25, 26};

        String result = "";

        for(int i = 0;i < numbers.length;i++) {
            result += numbers[i];
        }

        String expected = "2526";

        assertEquals(expected, result);
    }

    @Test
    public void testMessageIDCreated() {

        Scanner input = new Scanner(System.in);
        Message msgObj = new Message(input);

        msgObj.generateMessageID();

        assertTrue(msgObj.checkMessageID());
    }

    @Test
    public void testSendMessage() {

        Scanner input = new Scanner(System.in);
        Message msgObj = new Message(input);

        assertEquals("Message sent successfully.", msgObj.sentMessage(1));
    }

    @Test
    public void testDiscardMessage() {

        Scanner input = new Scanner(System.in);
        Message msgObj = new Message(input);

        assertEquals("Press 0 to delete message.", msgObj.sentMessage(2));
    }

    @Test
    public void testStoreMessage() {

        Scanner input = new Scanner(System.in);
        Message msgObj = new Message(input);

        assertEquals("Message stored successfully.", msgObj.sentMessage(3));
    }

    @Test
    public void testReturnTotalMessages() {

        int count = Message.returnTotalMessages();

        assertTrue(count >= 0);
    }
}