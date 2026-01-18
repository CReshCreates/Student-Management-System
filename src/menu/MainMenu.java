package menu;

import Controller.AuthController;
import Model.LoginRequest;
import Model.User;
import service.AuthService;
import session.Session;

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
                case "ADMIN" -> new AdminMenu().show();
                case "STUDENT" -> new StudentMenu().show();
                case "TEACHER" -> new TeacherMenu().show();
            }
        }
    }
}
