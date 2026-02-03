package Model.Normal;

public class User {
    String UserName;
    String Password;
    String Roles;
    int userId;

    public User(String username, String password, String roles, int userId){
        this.UserName= username;
        this.Password = password;
        this.Roles = roles;
        this.userId = userId;
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

    public int getUserId(){
        return userId;
    }
}
