package Model;

public class User {
    String UserName;
    String Password;
    String Roles;

    public User(String username, String password, String roles){
        this.UserName= username;
        this.Password = password;
        this.Roles = roles;
    }

    public String getName(){
        return UserName;
    }

    public String getPassword(){
        return Password;
    }

    public String getRoles(){
        return Roles;
    }
}
