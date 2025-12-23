package service;

import Model.UserView;
import Repository.UserRepository;
import util.DBUtil;

import java.sql.Connection;
import java.util.List;

public class UserService {
    private final UserRepository userRepo = new UserRepository();
    private final DBUtil dbUtil = new DBUtil();

    public void viewAllUsers(){
        List<UserView> users = userRepo.getUserWithNameAndRole();
        users.forEach(System.out::println);
    }

    public void deleteUser(String username){
        try{
            Connection connection = dbUtil.connection();
            userRepo.deleteUserByUsername(connection, username);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
