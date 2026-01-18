package Controller;

import service.RegistrationService;
import service.UserService;

import java.util.Scanner;

public class AdminController {

    private final RegistrationService registrationService = new RegistrationService();
    private final UserService userService = new UserService();

    public void registerUser(Scanner scanner){
        System.out.println("Enter the role:");
        String role = scanner.nextLine().toUpperCase();

        while (!role.equals("ADMIN") && !role.equals("TEACHER") && !role.equals("STUDENT")) {
            System.out.println("Invalid role. Try again:");
            role = scanner.nextLine().toUpperCase();
        }

        System.out.println("Enter username (email): ");
        String username = scanner.nextLine();

        while(!registrationService.isUsernameAvailable(username)){
            System.out.println("Username already taken. Enter another.");
            username = scanner.nextLine();
        }

        System.out.println("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        switch(role){
            case "STUDENT":
                System.out.println("Phone Number:");
                String phoneNumber = scanner.nextLine();

                System.out.println("Address:");
                String address = scanner.nextLine();

                System.out.println("Batch/Year");
                int year = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Program:");
                String program = scanner.nextLine();

                System.out.println("Section:");
                String section = scanner.nextLine();

                registrationService.registerStudent(username, password, fullName, phoneNumber, address, year, program, section);
                System.out.println("Student registered successfully!");

                break;

            case "TEACHER":
                System.out.println("Phone Number:");
                String phone = scanner.nextLine();

                System.out.println("Address:");
                String tAddress = scanner.nextLine();

                System.out.println("Department:");
                String department = scanner.nextLine();

                registrationService.registerTeacher(username, password, fullName, phone, tAddress, department);
                break;

            case "ADMIN":
                registrationService.registerAdmin(username, password, fullName);
                break;
        }
    }

    public void viewAllUsers(){
        userService.viewAllUsers();
    }

    public void deleteUser(Scanner scanner){
        System.out.println("Enter username to be deleted:");
        String userToBeDeleted = scanner.nextLine();
        System.out.println("The user will be permanently deleted. Are you sure? Y/N");

        while(true){
            char confirmation = scanner.next().charAt(0);
            if(confirmation == 'y' || confirmation == 'Y'){
                userService.deleteUser(userToBeDeleted);
                break;
            } else if (confirmation == 'n' || confirmation == 'N') {
                System.out.println("Deletion cancelled!");
                break;
            }
            else{
                System.out.println("Invalid input. Please type y or n.");
            }
        }
    }

    public void registerDepartment(Scanner scanner){
        System.out.println("Enter new department:");
        String deptName = scanner.nextLine();

        registrationService.registerDepartment(deptName);
    }
}
