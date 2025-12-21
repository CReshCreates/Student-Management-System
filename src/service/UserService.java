package service;

import Model.User;
import Repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepo = new UserRepository();

    public void viewAllUsers(){
        List<User> users = userRepo.allUser();
        users.forEach(System.out::println);
    }
}
