package Repository;

import Model.TeacherAndDepartment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    public void regTeachers(Connection connection, String f_name, String phone_number, String address, int u_id, int dept_id){
        String qry = "INSERT INTO teachers (full_name, phone_number, address, u_id, dept_id) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = connection;
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

    public List<TeacherAndDepartment> getTeacherAndBatch(Connection conn, int deptId){
        List<TeacherAndDepartment> teacherAndDepartments = new ArrayList<>();
        String qry = "SELECT t_id, full_name FROM teachers WHERE dept_id = ?";
        try{
            PreparedStatement prepareStatement = conn.prepareStatement(qry);
            prepareStatement.setInt(1, deptId);
            ResultSet rs = prepareStatement.executeQuery();

            while(rs.next()){
                TeacherAndDepartment teacherAndDepartment = new TeacherAndDepartment(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
                );
                teacherAndDepartments.add(teacherAndDepartment);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return teacherAndDepartments;
    }
}
