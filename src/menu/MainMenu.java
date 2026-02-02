package menu;

import Controller.AuthController;
import Model.DataTransfer.LoginRequest;
import Model.Normal.User;
import Session.Session;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthController authController = new AuthController();

    public void start(){
        while(true){
            LoginMenu loginMenu = new LoginMenu(scanner);
            LoginRequest request = loginMenu.getLoginInput();

            User user = authController.login(request.username(), request.password());

            if(user == null){
                System.out.println("Invalid credentials. Try again.");
                continue;
            }

            Session.login(user);

            switch(user.getRoles()){
                case "ADMIN" -> new AdminMenu().show(scanner);
                case "STUDENT" -> new StudentMenu().show();
                case "TEACHER" -> new TeacherMenu().show(scanner);
            }
        }
    }
}
