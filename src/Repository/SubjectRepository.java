package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubjectRepository {
    public void addSubject(Connection conn, String code, String name, int sem, int courseId){
        String qry = "INSERT INTO subjects (code, name, semester, course_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, sem);
            preparedStatement.setInt(4, courseId);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                System.out.println("No rows of subject table affected. Registration failed!!!");
            }
            System.out.println("Subject Registration Successful.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
