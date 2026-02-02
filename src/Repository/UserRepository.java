package Repository;

import Model.Normal.User;
import Model.Normal.UserView;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final DBUtil dbUtil = new DBUtil();
    public User findUserByName(String username) throws SQLException {
        String qry = "SELECT username, password_hash, role FROM users WHERE username = ?";

        Connection conn = dbUtil.connection();
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

    public int regUser(Connection connection, String user_name, String password_hash, String role){
        String qry = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);

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

    public List<UserView> getUserWithNameAndRole(){
        List<UserView> users = new ArrayList<>();
        String qry = "SELECT u.u_id, COALESCE(s.full_name , t.full_name, a.full_name) AS full_name, u.username, u.role " +
                "FROM users u " +
                " LEFT JOIN students s ON u.u_id = s.u_id" +
                " LEFT JOIN teachers t ON u.u_id = t.u_id" +
                " LEFT JOIN admin a ON u.u_id = a.u_id";

        try {
            Connection conn = dbUtil.connection();
            PreparedStatement ps = conn.prepareStatement(qry);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                UserView userview = new UserView(
                  rs.getInt(1),
                  rs.getString(2),
                  rs.getString(3),
                  rs.getString(4)
                );
                users.add(userview);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean deleteUserByUsername(Connection connection, String username){
        String qry = "DELETE FROM users WHERE username = ?";
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(qry);
            ps.setString(1, username);

            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("No rows affected. Deletion failed!");
            }
            System.out.println("User deletion successful...");
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            if(ps != null){
                try{
                    ps.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean updatePassword(Connection conn, String username, String newHashedPassword){
        String qry = "UPDATE users SET password_hash = ? WHERE username = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1, newHashedPassword);
            preparedStatement.setString(2, username);
            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
