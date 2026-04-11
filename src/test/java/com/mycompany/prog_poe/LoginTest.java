/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }

    @Test
    public void testCheckUserName() {
        Registration reg = new Registration();
         
        assertTrue(reg.checkUserName("kyl_1"));
        assertFalse(reg.checkUserName("kyle!!!!!!!"));

    }

    @Test
    public void testCheckPasswordComplexity() {
        Registration reg = new Registration();
         
        assertTrue(reg.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(reg.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber() {
        Registration reg = new Registration();
         
        assertTrue(reg.checkCellPhoneNumber("+27838968976"));
        assertFalse(reg.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testReturnLoginStatus() {
        Registration reg = new Registration();
        reg.username = "kyl_1";
        reg.password = "Ch&&sec@ke99!";
        
        Login login = new Login(reg);
        
        assertEquals("Welcome back kyl_1, it is great to see you again.", login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Username or password incorrect; try again.", login.returnLoginStatus("kyle!!!!!!!", "password"));
    }

    @Test
    public void testLoginUser() {
        Registration reg = new Registration();
        reg.username = "kyl_1";
        reg.password = "Ch&&sec@ke99!";
        
        Login login = new Login(reg);
        
        assertTrue(login.checkUserName("kyl_1") && login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkUserName("kyle!!!!!!!") && login.checkPasswordComplexity("password"));
    }
    
}
