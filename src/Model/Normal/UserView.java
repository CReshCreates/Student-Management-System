package Model.Normal;

public class UserView {
    private int user_id;
    private String userName;
    private String fullName;
    private String role;

    public UserView(int user_id, String fullname, String username, String role){
        this.user_id = user_id;
        this.userName = username;
        this.fullName = fullname;
        this.role = role;
    }

    public int getUser_id(){
        return user_id;
    }

    public String getUserName(){
        return userName;
    }

    public String getFullName(){
        return fullName;
    }

    public String getRole(){
        return role;
    }

    @Override
    public String toString(){
        return "User Id: " + user_id + ", Username: " + userName + ", Full Name: " + fullName + ", Role: " + role;
    }
}
