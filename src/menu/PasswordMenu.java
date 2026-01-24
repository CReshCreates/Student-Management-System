package menu;

import Controller.PasswordController;

import java.util.Scanner;

public class PasswordMenu {
    private Scanner scanner = new Scanner(System.in);
    private final PasswordController passwordController = new PasswordController();

    public void show(){
        System.out.println("Enter old password:");
        String oldPassword = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        boolean success = passwordController.changePassword(scanner, oldPassword, newPassword);
        System.out.println(success ? "Password changed successfully!" : "Password change failed!");
    }
}
