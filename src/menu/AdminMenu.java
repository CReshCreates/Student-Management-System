package menu;

import Repository.*;
import service.RegistrationService;
import auth.Session;

import java.util.Scanner;

public class AdminMenu {
    private final RegistrationService registrationService= new RegistrationService();

    Scanner scanner = new Scanner(System.in);

    public void show(){
        String deptName;
        System.out.println("--------Welcome to Admin Menu--------");
        System.out.println("1. Register User.");
        System.out.println("2. Delete User.");
        System.out.println("3. Assign Teacher.");
        System.out.println("4. Manage Department");
        System.out.println("5. Logout.");
        System.out.println("Enter your choice:");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                regUI();
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                System.out.println("Enter new department:");
                deptName = scanner.nextLine();
                registrationService.registerDepartment(deptName);
                break;

            case 5:
                Session.logout();
                System.out.println("Logged Out Successfully!");
                new MainMenu();
        }
    }

    public void regUI(){
        int year;
        String phone_number;
        String address;
        String upperRole;
        String program;
        String section;
        String phone_number_t;
        String t_address;
        String department;
        String u_name;

        while(true){
            System.out.println("Enter the role:");
            upperRole = scanner.nextLine().toUpperCase();

            if(upperRole.equals("ADMIN") || upperRole.equals("TEACHER") || upperRole.equals("STUDENT")){
                break;
            }
            System.out.println("Invalid role. Please try again!!!");
        }

        System.out.println(upperRole);

        while(true){
            System.out.println("username (email):");
            u_name = scanner.nextLine();
            if(registrationService.isUsernameAvailable(u_name)){
                break;
            }
            System.out.println("Username already taken. Please use another username:");
        }

        System.out.println("Full Name:");
        String f_name = scanner.nextLine();

        while(true){
            System.out.println("Password:");
            String password = scanner.nextLine();

            System.out.println("Confirm Password:");
            String conf_pass = scanner.nextLine();

            if(password.equals(conf_pass)){
                switch(upperRole){
                    case "STUDENT":
                        System.out.println("Phone Number:");
                        phone_number = scanner.nextLine();
                        System.out.println("Address:");
                        address = scanner.nextLine();
                        System.out.println("Batch:");
                        year = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Program:");
                        program = scanner.nextLine();
                        System.out.println("Section:");
                        section = scanner.nextLine();

                        registrationService.registerStudent(u_name,password,upperRole,f_name,phone_number,address,year,program,section);

                        break;

                    case "TEACHER":
                        System.out.println("Phone Number:");
                        phone_number_t = scanner.nextLine();
                        System.out.println("Address:");
                        t_address = scanner.nextLine();
                        System.out.println("Department:");
                        department = scanner.nextLine();

                        registrationService.registerTeacher(u_name,password,upperRole,f_name,phone_number_t,t_address,department);
                        break;

                    case "ADMIN":
                        break;
            }
               break;
            }
            System.out.println("Password do not match. Please try again!!!");
        }

    }
}
