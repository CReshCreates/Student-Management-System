package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherRepository {
    public void regTeachers(String f_name, String phone_number, String address, int u_id, int dept_id){
        String qry = "INSERT INTO teachers (full_name, phone_number, address, u_id, dept_id) VALUES (?, ?, ?, ?, ?)";

        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", user, pass);
            PreparedStatement ps = conn.prepareStatement(qry);

            ps.setString(1, f_name);
            ps.setString(2, phone_number);
            ps.setString(3, address);
            ps.setInt(4, u_id);
            ps.setInt(5, dept_id);

            int affected_rows = ps.executeUpdate();

            if(affected_rows == 0){
                throw new SQLException("Registration failed. No rows affected");
            }
            else{
                System.out.println("Teacher registration successful.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
