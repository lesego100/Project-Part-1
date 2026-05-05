package com.mycompany.prog_poe;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Registration {
    //Create a scanner object to enable the user to input data
    Scanner input = new Scanner(System.in);
    //declare or create global variables to store user details
    String username;
    String password;
    String cellPhoneNumber;
    
    //create regex constants
    private static final String USERNAME_REGEX = "^.{1,5}_.*$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$";
    private static final String CELLPHONENUMBER_REGEX = "^\\+27\\d{9}$";
    //create methods to validate the username, password, and cell phone number(to check if the user input follows the conditions)
    boolean checkUserName(String username) {
        if(Pattern.matches(USERNAME_REGEX, username)) {
            System.out.println("Username successfully captured.");
            return true;
        }
        else {
            System.out.println("Username is not correctly formatted; please "
                    + "ensure that your username contains an underscore and "
                    + "no more than five characters in length.");
            return false;
        }
    }
    boolean checkPasswordComplexity(String password) {
        if(Pattern.matches(PASSWORD_REGEX, password)) {
            System.out.println("Password successfully captured.");
            return true;
        }
        else {
            System.out.println("Password is not correctly formatted; please "
                    + "ensure that the pasword contains at least eight "
                    + "characters, a capital letter, a number, and a special "
                    + "character. ");
            return false;
        }
    }
    boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if(Pattern.matches(CELLPHONENUMBER_REGEX, cellPhoneNumber)) {
            System.out.println("Cell phone number successfully added.");
            return true;
        }
        else {
            System.out.println("Cell phone number incorrectly formatted or does "
                    + "not contain international code.");
            return false;
        }
    }
    //create method to register user
    boolean registerUser() {
        System.out.println("=== REGISTER ===");
        //create a loop that will repeat or loop until all conditions are met
        while(true) {
            //ask user for input
        System.out.print("Enter your username: ");
        username = input.nextLine();
        System.out.print("Enter your password: ");
        password = input.nextLine();
        System.out.print("Enter your cell phone number: ");
        cellPhoneNumber = input.nextLine();
        //validate all the inputs( check if all the inputs follow the conditions)
        boolean usernameValid = checkUserName(username);
        boolean passwordValid = checkPasswordComplexity(password);
        boolean cellPhoneNumberValid = checkCellPhoneNumber(cellPhoneNumber);
        //if all user inputs are correct or valid, then the registration process will be successful
        if(usernameValid && passwordValid && cellPhoneNumberValid) {
            System.out.println("Registration successful!");
            return true;
        }
        else {
            //if one or more of the user inputs aren't valid, then the registration will be unsuccessful and the user must try again
            System.out.println("Registration failed! Please try again.");
            System.out.println("\n ");
        }
        
        }
    
}
}
