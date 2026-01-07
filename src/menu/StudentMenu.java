package menu;

import session.Session;

import java.util.Scanner;

public class StudentMenu {

    Scanner scanner = new Scanner(System.in);
    public void show(){
        while(true){
            System.out.println("--------Welcome To Student Menu--------");
            System.out.println("1. Change password.");
            System.out.println("2. View attendance.");
            System.out.println("3. View assignments.");
            System.out.println("4. View lab assignments.");
            System.out.println("5. View notes.");
            System.out.println("6. Log out.");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    new PasswordMenu().show();
                    break;

                case 6:
                    Session.logout();
                    System.out.println("Logged Out Successfully!");
                    new MainMenu();
            }
        }
    }

}
