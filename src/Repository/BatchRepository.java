package Repository;

import java.sql.*;

public class BatchRepository {
    public int regNewStudentBatch(int year, String program, String section){
        int batch_id;

        String qry = "INSERT INTO batch (year, program, section) VALUES (?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", "user", "password");
            PreparedStatement ps = conn.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, year);
            ps.setString(2, program);
            ps.setString(3, section);

            int affected_rows = ps.executeUpdate();

            if(affected_rows == 0){
                throw new SQLException("Couldn't register batch. Try again!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                batch_id = generatedKeys.getInt(1);
                return batch_id;
            }
            else{
                throw new SQLException("Couldn't register batch, no batch id obtained");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
