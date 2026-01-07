package Repository;

import java.sql.*;

public class CourseRepository {
    public int addCourse(Connection conn, String courseName, int deptId){
        String qry = "INSERT INTO course (name, dept_id) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, courseName);
            preparedStatement.setInt(2, deptId);
            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                System.out.println("No rows affected in course table. Registration failed!");
            }

            int courseId;

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if(generatedKeys.next()){
                courseId = generatedKeys.getInt(1);
                return courseId;
            }
            else{
                throw new SQLException("Creating user failed, no ID obtained");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
