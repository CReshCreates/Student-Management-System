package Repository;

import java.sql.*;

public class DepartmentRepository {
    public int getDeptId(String deptName){
        int deptId;
        String qry = "SELECT dept_id FROM department WHERE name = ?";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", "user", "password");
            PreparedStatement ps = conn.prepareStatement(qry);

            ps.setString(1, deptName);

            ResultSet retrievedId = ps.executeQuery();
            if(retrievedId.next()){
                deptId = retrievedId.getInt(1);
                return deptId;
            }
            else{
                throw new SQLException("Department not found!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void departmentRegistration(String depart_name){
        String qry = "INSERT INTO department (name) VALUES (?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", "user", "password");
            PreparedStatement ps = conn.prepareStatement(qry);

            ps.setString(1, depart_name);

            int affected_rows = ps.executeUpdate();
            if(affected_rows == 0){
                throw new SQLException("No rows affected. Department registration failed");
            }
            else{
                System.out.println("New Department Registered!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
