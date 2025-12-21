package menu;

import Model.User;
import service.AuthService;
import session.Session;

import java.util.Scanner;

public class MainMenu {
    public AuthService authService;
    public MainMenu(){
        input();
    }

    public void input(){
        System.out.println("--------Welcome to Student Management System--------");

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the username:");
        String username = sc.nextLine();
        System.out.print("\nEnter the password:");
        String password = sc.nextLine();

        authService = new AuthService();
        User user = authService.validation(username, password);

        if(user != null){
            Session.login(user);

            System.out.println(user.getRoles());
            System.out.println("Log In Successful.");
            switch(user.getRoles()){
                case "ADMIN":
                    System.out.println("Logged In as admin!");
                    new AdminMenu().show();
                    break;

                case "STUDENT":
                    System.out.println("Logged In as Student!");
                    break;

                case "TEACHER":
                    System.out.println("Logged In as Teacher!");
                    break;
            }
        }
        else{
            System.out.println("Invalid Username or Password. Please try again.");
            input();
        }
    }

}
