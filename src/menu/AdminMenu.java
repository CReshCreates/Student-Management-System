package menu;

import Controller.AdminController;
import Model.BatchInfo;
import Model.DeptInfo;
import Model.UserRegInfo;
import Model.UserView;
import session.Session;

import java.util.ArrayList;
import java.util.List;
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
            System.out.println("5. Create a new Batch");
            System.out.println("6. Assign Teacher.");
            System.out.println("7. Add Department");
            System.out.println("8. Change Password.");
            System.out.println("9. Logout.");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

                switch (choice){
                    case 1 -> registerMenu();
                    case 2 -> viewUser();
                    case 3-> deleteMenu();
                    case 4 -> new CourseMenu().show();
                    case 5 -> batchCreationMenu();
                    case 6 -> teacherAssignmentMenu();
                    case 7 -> deptMenu();
                    case 8 -> new PasswordMenu().show();
                    case 9 -> {
                        Session.logout();
                        System.out.println("Logged out successfully!");
                        return;
                    }
                    default -> System.out.println("Invalid choice!!!");
                }
        }
    }

    public void viewUser(){
        List<UserView> userViewList = new ArrayList<>();
        userViewList.forEach(System.out::println);
    }

    public void registerMenu(){
        System.out.println("Enter the role:");
        String role = scanner.nextLine().toUpperCase();

        role = controller.roleCheck(scanner, role);

        System.out.println("Enter username (email): ");
        String username = scanner.nextLine();

        username = controller.usernameValidation(scanner, username);

        System.out.println("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        password = controller.validate(password);

        switch(role){
            case "STUDENT":

                System.out.println("Phone Number:");
                String phoneNumber = scanner.nextLine();

                System.out.println("Address:");
                String address = scanner.nextLine();

                System.out.println("Batch/Year");
                int year = readInt();

                System.out.println("Program:");
                String program = scanner.nextLine();

                BatchInfo batchInfo = controller.validateBatchAndProgram(scanner, year, program);

                System.out.println("Section:");
                String section = scanner.nextLine();

                UserRegInfo info = new UserRegInfo(role,username,fullName,password,phoneNumber,address,batchInfo.getYear(),batchInfo.getProgram(),section,null);

                controller.registerStudent(info, batchInfo);
                System.out.println("Student registered successfully!!!");
                break;

            case "TEACHER":

                System.out.println("Phone Number:");
                phoneNumber = scanner.nextLine();

                System.out.println("Address:");
                address = scanner.nextLine();

                System.out.println("Department:");
                String department = scanner.nextLine();

                DeptInfo deptInfo = controller.departmentValidation(scanner, department);

                info = new UserRegInfo(role,username,fullName,password,phoneNumber,address,null,null,null,deptInfo.getDeptName());

                controller.registerTeacher(info, deptInfo);
                System.out.println("Teacher registered successfully!!!");
                break;

            case "ADMIN":

                info = new UserRegInfo(role,username,fullName,password,null,null,null,null,null,null);
                controller.registerAdmin(info);
                System.out.println("Admin registered successfully!!!");
                break;
        }
    }

    private void deleteMenu() {
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

        char confirm;
        while (true) {
            System.out.print("Are you sure? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("n")) {
                confirm = input.charAt(0);
                break;
            }
            System.out.println("Invalid input. Please enter y or n.");
        }

        if (confirm == 'y') {
            controller.deleteUser(username);
            System.out.println("User deleted!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }


    public void deptMenu(){
        System.out.println("Enter new department:");
        String deptName = scanner.nextLine();

        controller.registerDepartment(deptName);
    }

    public void batchCreationMenu(){
        System.out.println("Enter a new batch: ");
        int newBatch = readInt();
        System.out.println("Enter a program: ");
        String newProgram = scanner.nextLine();

        controller.createNewBatchWithValidation(scanner, newBatch, newProgram);
    }

    public void teacherAssignmentMenu(){

    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number:");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
