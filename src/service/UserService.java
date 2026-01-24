package service;

import Model.User;
import Model.UserView;
import Repository.UserRepository;
import util.DBUtil;
import util.PasswordUtil;
import util.PasswordValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserRepository userRepo = new UserRepository();
    private final DBUtil dbUtil = new DBUtil();
    private final PasswordUtil passwordUtil = new PasswordUtil();
    private final PasswordValidator passwordValidator = new PasswordValidator();

    public void viewAllUsers(){
        List<UserView> users = userRepo.getUserWithNameAndRole();
        users.forEach(System.out::println);
    }

    public void deleteUser(String username){
        TransactionManager.execute(conn ->{
            User user = userRepo.findUserByName(username);
            if(user == null){
                System.out.println("User not found!");
                return;
            }
            userRepo.deleteUserByUsername(conn, username);
            System.out.println("User deleted successfully!!!");
        });
    }

    public boolean changePassword(String username, String newPassword){
        Connection conn = dbUtil.connection();
        try {
            User user = userRepo.findUserByName(username);
            if(user == null){
                throw new RuntimeException("User not found!");
            }

            passwordValidator.validate(newPassword);
            String hashedPassword = passwordUtil.encryptPassword(newPassword);
            return userRepo.updatePassword(conn, username, hashedPassword);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
