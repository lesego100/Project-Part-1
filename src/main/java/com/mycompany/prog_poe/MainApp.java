package com.mycompany.prog_poe;
import java.util.Scanner;
public class MainApp {
    Scanner input = new Scanner(System.in);
    
    int choice = 0;
    do {
    System.out.println("Welcome to QucikChat.");
    System.out.println("========== MENU ==========");
    System.out.println("1. Send Messages");
    System.out.println("2. Show recently sent messages");
    System.out.println("3. Quit");
    choice = input.nextInt();
    
    switch(choice) {
    case 1:
    System.out.println("Message");
    break;
    case 2:
    System.out.println("Coming Soon.");
    break;
    case 3:
    System.out.println("Exiting QuickChat.");
    break;
    default:
    System.out.println("Invalid option. Try again.");
    
}
}
    
}
