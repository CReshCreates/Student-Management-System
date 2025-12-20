package auth;

import Model.User;
import Repository.UserRepository;
import util.PasswordUtil;

import java.sql.SQLException;

public class AuthService {

    public User validation(String username, String password){
        try {
            UserRepository userRepo= new UserRepository();

            User user = userRepo.findUserByName(username);

            if(user == null){
                System.out.println("No users found!!!");
                return null;
            }

            PasswordUtil passwordUtil = new PasswordUtil();
            String encryptedPassword = passwordUtil.encryptPassword(password);

            if(user.getPassword().equals(encryptedPassword)){
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
