package Controller;

import Model.User;
import service.AuthService;

public class AuthController {
    private final AuthService authService = new AuthService();

    public User login(String username, String password){
        return authService.validation(username, password);
    }
}
