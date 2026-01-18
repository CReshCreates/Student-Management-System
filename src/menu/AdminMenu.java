package menu;

import Controller.AdminController;
import service.RegistrationService;
import service.UserService;
import session.Session;

import java.util.Scanner;

public class AdminMenu {
    private final AdminController controller = new AdminController();
    private Scanner scanner = new Scanner(System.in);

    public void show(){
        while(true){
            System.out.println("--------Welcome to Admin Menu--------");
            System.out.println("1. Register User.");
            System.out.println("2. View All Users");
            System.out.println("3. Delete User.");
            System.out.println("4. Manage Course.");
            System.out.println("5. Assign Teacher.");
            System.out.println("6. Add Department");
            System.out.println("7. Change Password.");
            System.out.println("8. Logout.");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

                switch (choice){
                    case 1 -> controller.registerUser(scanner);
                    case 2 -> controller.viewAllUsers();
                    case 3-> controller.deleteUser(scanner);
                    case 4 -> new CourseMenu().show();
                    case 6 -> controller.registerDepartment(scanner);
                    case 7 -> new PasswordMenu().show();
                    case 8 -> {
                        Session.logout();
                        System.out.println("Logged out successfully!");
                    }
                    default -> System.out.println("Invalid choice!!!");
                }
        }
    }
}
