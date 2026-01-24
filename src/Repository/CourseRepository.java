package Repository;

import Model.Course;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    private final DBUtil dbUtil = new DBUtil();

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

    public List<Course> viewCourse(Connection conn){
        String qry = "SELECT course_id, name FROM course";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(qry);

            List<Course> courses = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int courseId = rs.getInt(1);
                String courseName = rs.getString(2);
                courses.add(new Course(courseName, courseId));
            }
            return courses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCourseAvailable(Connection conn, int courseId){
        String qry = "SELECT sub_id FROM subjects where sub_id = ?";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setInt(1, courseId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int course_id = rs.getInt(1);
                if(course_id == courseId){
                    return true;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateCourseName(Connection conn, int id, String newName){
        String qry = "UPDATE course SET name = ? where course_id = ?";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean updateCourseDuration(Connection conn, int courseId, int duration){
        return false;
    }

    public boolean deleteCourse(Connection conn, int courseId){
        String qry = "DELETE FROM course WHERE course_id = ?";
        try{
            PreparedStatement prepareStatement = conn.prepareStatement(qry);
            prepareStatement.setInt(1, courseId);

            int affectedRows = prepareStatement.executeUpdate();

            if(affectedRows == 0){
                return false;
            }

            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
