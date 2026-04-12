package com.mycompany.prog_poe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 * This class is used to test if the login feature works properly
 */
public class LoginTest {
    
    public LoginTest() {
    }
/**
 * test to check if the username is formatted correctly and therefore, we are 
 * using assertTrue and assertFalse because this method returns a Boolean
 */
    @Test
    public void testCheckUserName() {
        //create a Registration object
        Registration reg = new Registration();
         //test both the correctly formatted username and incorrectly formatted username
        assertTrue(reg.checkUserName("kyl_1"));
        assertFalse(reg.checkUserName("kyle!!!!!!!"));

    }
/**
 * test to check if the password meets the complexity requirements and use 
 * assertTrue and asserFalse for validation
 */
    @Test
    public void testCheckPasswordComplexity() {
        //create a Registration object
        Registration reg = new Registration();
         //test both the correct and incorrect password
        assertTrue(reg.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(reg.checkPasswordComplexity("password"));
    }
/**
 * test to check if the cell phone number is formatted correctly and that it 
 * contains the South African international code
 */
    @Test
    public void testCheckCellPhoneNumber() {
        //create a Registration object
        Registration reg = new Registration();
         //test both correctly formatted and incorrectly formatted cell phone number
        assertTrue(reg.checkCellPhoneNumber("+27838968976"));
        assertFalse(reg.checkCellPhoneNumber("08966553"));
    }
/**
 * test to verify the login status message, and we use assertEquals because the
 * method returns a String
 */
    @Test
    public void testReturnLoginStatus() {
        //create a Registration object 
        Registration reg = new Registration();
        //set stored user details
        reg.username = "kyl_1";
        reg.password = "Ch&&sec@ke99!";
        //create a Logn object
        Login login = new Login(reg);
        //test both success and failure messages
        assertEquals("Welcome back kyl_1, it is great to see you again.", login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Username or password incorrect; try again.", login.returnLoginStatus("kyle!!!!!!!", "password"));
    }
/**
 * test to check login functionality
 */
    @Test
    public void testLoginUser() {
        //create a Rgeistration object and create user data
        Registration reg = new Registration();
        reg.username = "kyl_1";
        reg.password = "Ch&&sec@ke99!";
        //create a Login object
        Login login = new Login(reg);
        //test that successful login returns true and failed login returns false
        assertTrue(login.checkUserName("kyl_1") && login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkUserName("kyle!!!!!!!") && login.checkPasswordComplexity("password"));
    }
    
}
