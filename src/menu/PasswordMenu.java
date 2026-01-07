package menu;

import Model.User;
import service.UserService;
import session.Session;
import util.PasswordValidator;

import java.util.Scanner;

public class PasswordMenu {
    private Scanner scanner = new Scanner(System.in);
    private final PasswordValidator passwordValidator = new PasswordValidator();
    private final UserService userService = new UserService();

    public void show(){
        System.out.println("Enter old password:");
        String oldPassword = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        User user = Session.getCurrentUser();
        String userName = user.getName();

        passwordValidator.passwordConfirmation(newPassword);

        userService.changePassword(userName, oldPassword, newPassword);
        System.out.println("Password changed successfully!");
    }
}
