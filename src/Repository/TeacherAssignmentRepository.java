package Repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
