package Repository;

import Model.DeptInfo;

import java.sql.*;
import java.util.List;

public class DepartmentRepository {
    public DeptInfo getDeptId(Connection connection, String deptName){
        String qry = "SELECT *FROM department WHERE name = ?";
        try {
            Connection conn = connection;
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, deptName);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                return new DeptInfo(id, name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void departmentRegistration(Connection connection, String depart_name){
        String qry = "INSERT INTO department (name) VALUES (?)";
        try {
            Connection conn = connection;
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

    public List<DeptInfo> deptInfoList(){
        String qry = "SELECT "
    }
}
