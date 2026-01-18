package Model;

public class LoginRequest {
    String username;
    String password;

    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String username(){
        return username;
    }

    public String password(){
        return password;
    }
}
