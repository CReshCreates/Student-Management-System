package Repository;

import Model.Normal.Subjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    public int getSubId(Connection conn, String code){
        String qry = "SELECT sub_id FROM subjects WHERE code = ?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1, code);

            ResultSet rs = preparedStatement.executeQuery();

            int id = 0;

            while (rs.next()) {
                id = rs.getInt(1);
            }

            return id;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
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

    public List<Subjects> viewSubjects(Connection conn, int courseId){
        String qry = "SELECT code, name, semester FROM subjects WHERE course_id = ?";
        List<Subjects> subjects = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setInt(1, courseId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String code = rs.getString(1);
                String name = rs.getString(2);
                int semester = rs.getInt(3);
                subjects.add(new Subjects(code, name, semester));
            }
            return subjects;

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean updateSubjectName(Connection conn, String code, String newName){
        String qry = "UPDATE subjects SET name = ? WHERE code = ?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, code);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                return false;
            }

            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean updateSubjectCode(Connection conn, String oldCode, String newCode){
        String qry = "UPDATE subjects SET code = ? WHERE code = ?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1, oldCode);
            preparedStatement.setString(2, newCode);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean updateSemester(Connection conn, String code, int semester){
        String qry = "UPDATE subjects SET semester = ? WHERE code = ?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setInt(1, semester);
            preparedStatement.setString(2, code);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isAvailable(Connection conn, String subjectCode){
        String qry = "SELECT code FROM subjects WHERE code = ?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1, subjectCode);

            boolean check = false;

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                if(rs.getString(1).equals(subjectCode)){
                    check = true;
                }
                else{
                    check = false;
                }
            }

            return check;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
