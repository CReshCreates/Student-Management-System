package Repository;

import java.sql.*;

public class StudentRepository {
    public void reg_students(String full_name, String phone_number, String address, int u_id, int batch_id ){
        String qry = "INSERT INTO students (full_name, phone_number, address, u_id, batch_id) VALUES (?, ?, ?, ?, ?)";


        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", user, pass);
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
