package menu;

import session.Session;

import java.util.Scanner;

public class TeacherMenu {

    public void show(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------Welcome to Teacher Menu--------");
        System.out.println("1. View course assignment.");
        System.out.println("2. Take student's attendance.");
        System.out.println("3. View my attendance record.");
        System.out.println("4. Assign assignments to students.");
        System.out.println("5. View student's submitted assignments.");
        System.out.println("6. Assign lab assignments.");
        System.out.println("7. Attach notes.");
        System.out.println("8. Upload marks.");
        System.out.println("9. Logout.");
        System.out.println("Enter your choice:");

        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            case 7:
                break;

            case 8:
                break;

            case 9:
                Session.logout();
                System.out.println("Logged Out!");
                new MainMenu();
        }
    }
}
