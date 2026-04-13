package com.mycompany.prog_poe;
import java.util.Scanner;
public class Login {
    String firstName = "Kyle";
    String lastName = "Adams";
    //create a scanner for user input
    Scanner scan = new Scanner(System.in);
    //reference to Registration onbject(so that the Login class can access the stored details which are in the Registration class
    Registration reg;
    //create a constructor to receive the Registration object
    public Login(Registration reg) {
        this.reg = reg;
    }
        
    /**
     *
     * @param username
     * @return
     */
    //check if the username entered during login matches the registered username
    public boolean checkUserName(String username) {
        return username.equals(reg.username);
    }

    /**
     *
     * @param password
     * @return
     */
    //check if the password entered during login matches the registered password
    public boolean checkPasswordComplexity(String password) {
        return password.equals(reg.password);
    }

    /**
     *
     * @param cellPhoneNumber
     * @return
     */
    //check if the cell phone number entered during login matches the registered cell phone number
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        return cellPhoneNumber.equals(reg.cellPhoneNumber);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    //create a method to return the login message
    public String returnLoginStatus(String username, String password) {
        if(checkUserName(username) && checkPasswordComplexity(password)) {
            return "Welcome back " + firstName + " " + lastName + ", it is great to see you again.";
        }
        else {
            return "Username or password incorrect; try again.";
        }
    }
    //create a method to log the user in
    public boolean loginUser() {
        if(reg.username == null) {
            System.out.println("No user registered yet.");
          return false;
        }
        System.out.println("=== LOGIN ===");
        //create a loop that will repeat until the correct login details are entered
        while(true) {
            //ask the user to input their login details
        System.out.print("Enter your username: ");
        String inputUsername = scan.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = scan.nextLine();
        //display the login results
        System.out.println(returnLoginStatus(inputUsername, inputPassword));
        //if the entered details are valid, then exit the loop
        if(checkUserName(inputUsername) && checkPasswordComplexity(inputPassword)) {
            return true;
        }
        //add space for readability
        System.out.println();
        }
    
    }
}
