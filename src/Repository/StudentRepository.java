package Repository;

import java.sql.*;

public class StudentRepository {
    public boolean reg_students(Connection connection, String full_name, String phone_number, String address, int u_id, int batch_id, String section ){
        String qry = "INSERT INTO students (full_name, phone_number, address, u_id, batch_id, section) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = connection;
            PreparedStatement ps = conn.prepareStatement(qry);

            ps.setString(1, full_name);
            ps.setString(2, phone_number);
            ps.setString(3, address);
            ps.setInt(4, u_id);
            ps.setInt(5, batch_id);
            ps.setString(6, section);

            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0){
                return false;
            }
            else
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
