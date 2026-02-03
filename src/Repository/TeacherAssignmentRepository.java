package Repository;


import Model.Normal.TeacherAssignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherAssignmentRepository {
    public void assignSubject(Connection conn, int teacherId, int subjectId, int batchId, String section) {
        try {
            // 1. Check if assignment exists
            String checkQry = "SELECT COUNT(*) FROM teacher_assignment WHERE t_id = ? AND sub_id = ? AND batch_id = ? AND section = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQry)) {
                checkStmt.setInt(1, teacherId);
                checkStmt.setInt(2, subjectId);
                checkStmt.setInt(3, batchId);
                checkStmt.setString(4, section);
                var rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Teacher " + teacherId + " is already assigned subject " + subjectId + " in section " + section);
                    return; // skip insert
                }
            }

            // 2. Insert assignment
            String qry = "INSERT INTO teacher_assignment (t_id, sub_id, batch_id, section) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(qry)) {
                preparedStatement.setInt(1, teacherId);
                preparedStatement.setInt(2, subjectId);
                preparedStatement.setInt(3, batchId);
                preparedStatement.setString(4, section);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Failed to assign subject.");
                } else {
                    System.out.println("Subject " + subjectId + " successfully assigned to teacher " + teacherId + " in section " + section);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TeacherAssignment> getSubjectAssignment(Connection conn, int teacherId){
        String qry = "SELECT " +
                "ta.ta_id AS teacher_assignment_id, " +
                "s.name AS subject_name, " +
                "c.name AS course_name, " +
                "b.year AS year, " +
                "b.program AS program, " +
                "ta.section " +
                "FROM Teacher_Assignment ta " +
                "JOIN Teachers t ON ta.t_id = t.t_id " +
                "JOIN Subjects s ON ta.sub_id = s.sub_id " +
                "JOIN Course c ON s.course_id = c.course_id " +
                "JOIN Batch b ON ta.batch_id = b.batch_id " +
                "WHERE t.t_id = ?";

        List<TeacherAssignment> teacherAssignmentInfo = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setInt(1, teacherId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                TeacherAssignment teacherAssignment = new TeacherAssignment(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                teacherAssignmentInfo.add(teacherAssignment);
            }

            return teacherAssignmentInfo;

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
