package Controller;

import Model.Normal.User;
import service.UserService;
import Session.Session;
import util.PasswordUtil;
import util.PasswordValidator;

import java.util.Scanner;

public class PasswordController {
    private final UserService userService= new UserService();
    private final PasswordValidator passwordValidator = new PasswordValidator();
    private final PasswordUtil passwordUtil = new PasswordUtil();

    public boolean changePassword(Scanner scanner, String oldPassword, String newPassword){
        passwordValidator.passwordConfirmation(newPassword);

        User user = Session.getCurrentUser();
        String oldpassword = passwordUtil.encryptPassword(oldPassword);

        while(!oldpassword.equals(user.getPassword())){
            System.out.println("Old password incorrect!");
            System.out.println("Enter correct old password: ");
            oldpassword = passwordUtil.encryptPassword(scanner.nextLine());
        }

        return userService.changePassword(user.getName(), newPassword);
    }
}
