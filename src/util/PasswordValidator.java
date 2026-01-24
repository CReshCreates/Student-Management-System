package util;

import java.util.Scanner;

public class PasswordValidator {
    static Scanner scanner = new Scanner(System.in);
    public String validate(String password) {

        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be empty");

        if (password.length() < 8)
            throw new IllegalArgumentException("Password must be at least 8 characters");

        if (!password.matches(".*[A-Z].*"))
            throw new IllegalArgumentException("Password must contain an uppercase letter");

        if (!password.matches(".*[a-z].*"))
            throw new IllegalArgumentException("Password must contain a lowercase letter");

        if (!password.matches(".*\\d.*"))
            throw new IllegalArgumentException("Password must contain a digit");
        else
            return password;
    }

    public String passwordConfirmation(String password){
        while(true){
            System.out.println("Confirm Password:");
            String conf_pass = scanner.nextLine();

            if(password.equals(conf_pass)){
                break;
            }
            System.out.println("Password do not match. Please try again!!!");
        }
        return password;
    }
}
