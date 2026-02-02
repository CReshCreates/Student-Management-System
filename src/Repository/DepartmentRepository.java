package Repository;

import Model.Normal.DeptInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {
    public DeptInfo getDept(Connection connection, String deptName){
        String qry = "SELECT *FROM department WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(qry);
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

    public List<DeptInfo> getAllDepartInfo(Connection connection){
        String qry = "SELECT *FROM department";
        List<DeptInfo> deptInfoList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(qry);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DeptInfo deptInfo = new DeptInfo(
                        rs.getInt(1),
                        rs.getString(2)
                );
                deptInfoList.add(deptInfo);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return deptInfoList;
    }

    public void departmentRegistration(Connection connection, String depart_name){
        String qry = "INSERT INTO department (name) VALUES (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(qry);

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
