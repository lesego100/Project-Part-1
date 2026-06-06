package com.mycompany.prog_poe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author elmak
 */
public class MessageManagerTest {
    
    public MessageManagerTest() {
    }
    @BeforeEach
    public static void setup() {
    MessageManager.sentMessages = new String[50];
    MessageManager.storedMessages = new String[50];
    MessageManager.disregardedMessages = new String [50];
    MessageManager.hash = new String[50];
    MessageManager.ID = new String[50];
    MessageManager.recipient = new String[50];
    MessageManager.populateTestData();
    }

    @Test
    public void testAddMessage() { 
        String expected = "Did you get the cake?\nIt is dinner time!";
        String actual = MessageManager.getSentMessagesAsString();
         
        assertEquals(expected, actual);
    }

    @Test
    public void testGetGlobalIndex() {
        int expected = 5;
        int actual = MessageManager.getGlobalIndex();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testLongestStoredMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = MessageManager.longestStoredMessage();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessageByID() {
        String expected = "It is dinner time!";
        String actual = MessageManager.searchMessageByID("0838884567");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchRecipientMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time."
                         +"\nOk, I am leaving without you.";
        String actual = MessageManager.searchRecipientMessage("+27838884567");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteMessageByHash() {
        String expected = "Message successfully deleted";
        String actual = MessageManager.deleteMessageByHash("H2");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateReport() {
        String report = MessageManager.generateReport();
        
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
    }
    
}
