package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRepository {
    public void registerAdmin(Connection connection, String fullName, int userId){
        String qry = "INSERT INTO admin (full_name, u_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(qry);
            ps.setString(1, fullName);
            ps.setInt(2, userId);

            int affectedRows = ps.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Registration failed. No rows affected.");
            }
            else
                System.out.println("Registration Successful.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
