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
    //resets all arrays and loads all the required test data before each unit test is executed
    @BeforeEach
    public void setup() {
    MessageManager.sentMessages = new String[50];
    MessageManager.storedMessages = new String[50];
    MessageManager.disregardedMessages = new String [50];
    
    MessageManager.hash = new String[50];
    MessageManager.ID = new String[50];
    MessageManager.recipient = new String[50];
    
    MessageManager.sentCount = 0;
    MessageManager.storedCount = 0;
    MessageManager.disregardCount = 0;
    
    MessageManager.globalIndex = 0;
    
    MessageManager.addMessage("Did you get the cake?", "H1", "0834557896", "+27834557896", 1);
    MessageManager.addMessage("Where are you? You are late! I have asked you to be on time.", "H2", "0838884567", "+27838884567", 3);
    MessageManager.addMessage("Yohoooo, I am at your gate.", "H3", "0833448908", "+27833448908", 2);
    MessageManager.addMessage("It is dinner time!", "H4", "0838884567", "+27838884567", 1);
    MessageManager.addMessage("Ok, I am leaving without you.", "H5", "0833884567", "+27833884567",3);
    }
    //tests whether all the sent messages array contains the expected messages
    @Test
    public void testSentMessage() { 
        String expected = "Did you get the cake?\nIt is dinner time!";
        String actual = MessageManager.getSentMessagesAsString();
         
        assertEquals(expected, actual);
    }
    //tests whether the longest message is returned correctly
    @Test
    public void testLongestStoredMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = MessageManager.longestStoredMessage();
        
        assertEquals(expected, actual);
    }
    //tests whether a message can be found using a recipient's number
    @Test
    public void testSearchRecipientMessage() {
        String actual = MessageManager.searchRecipientMessage("+27838884567");
        
        assertTrue(actual.contains("Where are you?"));
    }
    //tests whether a message can be deleted using its message hash
    @Test
    public void testDeleteMessageByHash() {
        String result = MessageManager.deleteMessageByHash("H2");
        
        assertEquals("Message successfully deleted", result);
    }
    //tests whether the genrated report has the required details
    @Test
    public void testGenerateReport() {
        String report = MessageManager.generateReport();
        
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
    }
}
