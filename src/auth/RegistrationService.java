package auth;

import Model.User;
import Repository.UserRepository;

import java.sql.SQLException;

public class RegistrationService {
    UserRepository userRepo = new UserRepository();
    public boolean isUsernameAvailable(String username){
        try {
            User user = userRepo.findUserByName(username);
            return user == null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerStudent(String user_name, String password, String role, String full_name, String phone_number, String address, String year, String program, String section){
        PasswordUtil hashed_password = new PasswordUtil();
        String reg_hashed_password = hashed_password.encryptPassword(password);

        UserRepository userRepo = new UserRepository();
        userRepo.regUser(user_name,reg_hashed_password,role);
    }

}
