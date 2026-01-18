package menu;

import Model.LoginRequest;

import java.util.Scanner;

public class LoginMenu {
    private final Scanner scanner;

    public LoginMenu(Scanner scanner){
        this.scanner = scanner;
    }

    public LoginRequest getLoginInput(){
        System.out.println("Username: ");
        String username = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        return new LoginRequest(username, password);
    }
}
