package service;

import Model.User;
import Model.UserView;
import Repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepo = new UserRepository();

    public void viewAllUsers(){
        List<UserView> users = userRepo.getUserWithNameAndRole();
        users.forEach(System.out::println);
    }

    public void deleteUser(String username){
        transactionManager.execute(conn ->{
            User user = userRepo.findUserByName(username);
            if(user == null){
                System.out.println("User not found!");
                return;
            }
            userRepo.deleteUserByUsername(conn, username);
            System.out.println("User deleted successfully!!!");
        });
    }
}
