package menu;

import service.RegistrationService;
import service.UserService;
import session.Session;

import java.util.Scanner;

public class AdminMenu {
    private final RegistrationService registrationService= new RegistrationService();
    private final UserService userService = new UserService();

    Scanner scanner = new Scanner(System.in);

    public void show(){
        while(true){

            System.out.println("--------Welcome to Admin Menu--------");
            System.out.println("1. Register User.");
            System.out.println("2. View All Users");
            System.out.println("3. Delete User.");
            System.out.println("4. Assign Teacher.");
            System.out.println("5. Add Department");
            System.out.println("6. Logout.");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

                switch (choice){
                    case 1:
                        userRegistrationMenu();
                        break;

                    case 2:
                        userService.viewAllUsers();
                        break;

                    case 3:
                        selectUserToDelete();
                        break;

                    case 4:

                        break;

                    case 5:
                        registerDepartment();
                        break;

                    case 6:
                        Session.logout();
                        System.out.println("Logged Out Successfully!");
                        new MainMenu();
                }
        }
    }

    public void userRegistrationMenu(){
        String role = getRole();
        String u_name = getAvailableUsername();

        System.out.println("Full Name:");
        String f_name = scanner.nextLine();

        String password;
        System.out.println("Password:");
        password = scanner.nextLine();


        while(true){

            System.out.println("Confirm Password:");
            String conf_pass = scanner.nextLine();

            if(password.equals(conf_pass)){
               break;
            }
            System.out.println("Password do not match. Please try again!!!");
        }

        switch(role){
            case "STUDENT":
                registerStudent(u_name, password, f_name);
                break;

            case "TEACHER":
                registerTeacher(u_name, password, f_name);
                break;

            case "ADMIN":
                registerAdmin(u_name, password, f_name);
                break;
        }
    }

    public void registerStudent(String userName, String password, String fullName){
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

        registrationService.registerStudent(userName, password, fullName, phoneNumber, address, year, program, section);
        System.out.println("Student registered successfully!");
    }

    public void registerTeacher(String userName, String password, String fullName){
        System.out.println("Phone Number:");
        String phone = scanner.nextLine();

        System.out.println("Address:");
        String address = scanner.nextLine();

        System.out.println("Department:");
        String department = scanner.nextLine();

        registrationService.registerTeacher(userName, password, fullName, phone, address, department);
    }

    public void registerAdmin(String userName, String password, String fullName){
        registrationService.registerAdmin(userName, password, fullName);
    }

    public void registerDepartment(){
        System.out.println("Enter new department:");
        String deptName = scanner.nextLine();

        registrationService.registerDepartment(deptName);
    }

    public void selectUserToDelete(){
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

    private String getRole(){
        while(true){
            System.out.println("Enter the role:");
            String upperRole = scanner.nextLine().toUpperCase();

            if(upperRole.equals("ADMIN") || upperRole.equals("TEACHER") || upperRole.equals("STUDENT")){
                return upperRole;
            }
            System.out.println("Invalid role. Please try again!!!");
        }
    }

    private String getAvailableUsername(){
        while(true){
            System.out.println("Username (email):");
            String u_name = scanner.nextLine();
            if(registrationService.isUsernameAvailable(u_name)){
                return u_name;
            }
            System.out.println("Username already taken. Please use another username:");
        }
    }
}
