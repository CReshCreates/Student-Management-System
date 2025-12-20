package Repository;

import java.sql.*;

public class StudentRepository {
    public void reg_students(Connection connection, String full_name, String phone_number, String address, int u_id, int batch_id ){
        String qry = "INSERT INTO students (full_name, phone_number, address, u_id, batch_id) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = connection;
            PreparedStatement ps = conn.prepareStatement(qry);

            ps.setString(1, full_name);
            ps.setString(2, phone_number);
            ps.setString(3, address);
            ps.setInt(4, u_id);
            ps.setInt(5, batch_id);

            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("No rows in student table affected. Registration failed.");
            }
            else
                System.out.println("Student registration successful");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
