/*
REFERENCES:
[1]Emeris School of Computer Science, “PROG5121 Unit Testing Getting started,” YouTube, Apr. 09, 2025. https://www.youtube.com/watch?v=MOhiM2SXZl0 (accessed Apr. 10, 2026).
‌[2]IIEVC School of Computer Science, “PROG5121 Maven Automated testing,” YouTube, May 14, 2024. https://www.youtube.com/watch?v=oz0Qd5H4Onk (accessed Apr. 10, 2026).
‌[3]J. Farrell, Java Programming., 10th ed. S.L.: Cengage Learning, 2022.
[4]Ramesh Fadatare, “Building a Login and Registration System with Java,” Javaguides.net, Apr. 07, 2024. https://www.javaguides.net/2024/04/building-login-and-registration-system-with-java.html (accessed Apr. 03, 2026).
*/
package com.mycompany.prog_poe;
public class PROG_POE {

    public static void main(String[] args) {
        //create a Registration object 
        Registration rgtObj = new Registration();
        //Register the user
        boolean registrationSuccess = rgtObj.registerUser();
        //only allow the user to proceed to login if registration was successful
        if(registrationSuccess) {
            
        }
        //create a Login object
        Login lgnObj = new Login(rgtObj);
        //Begin the login process
        boolean loginSuccess = lgnObj.loginUser();
        
        if(loginSuccess) {
            //display this message after the login is successful
            System.out.println("User logged in successfully!");
        }
        else {
            //otherwise, display this message if the login is unsucessful
            System.out.println("Login failed.");
        }
    }
}
