package Repository;

import Model.User;

import java.sql.*;

public class UserRepository {
    public User findUserByName(String username) throws SQLException {
        String qry = "SELECT username, password_hash, role FROM users WHERE username = ?";

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", "user", "password");
        PreparedStatement preparedStatement = conn.prepareStatement(qry);

        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            String UserName = rs.getString("username");
            String Password = rs.getString("password_hash");
            String Roles = rs.getString("role");

            if(UserName.equals(username)){
                return new User(UserName, Password, Roles);
            }
        }

        return null;
    }

    public int regUser(String user_name, String password_hash, String role){
        String qry = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", "user", "password");
            PreparedStatement ps = conn.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,user_name);
            ps.setString(2, password_hash);
            ps.setString(3, role);

            int affected_rows = ps.executeUpdate();

            if(affected_rows == 0){
                throw new SQLException("Creating User Failed, no rows affected.");
            }

            int userId;

            ResultSet generated_keys = ps.getGeneratedKeys();
            if(generated_keys.next()){
                userId = generated_keys.getInt(1);
                return userId;
            }
            else{
                throw new SQLException("Creating user failed, no ID obtained");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
