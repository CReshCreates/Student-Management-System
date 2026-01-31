package Repository;

import Model.BatchInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchRepository {
    public int regBatch(Connection connection, int year, String program){
        int batch_id;

        String qry = "INSERT INTO batch (year, program) VALUES (?, ?)";
        try {
            Connection conn = connection;
            PreparedStatement ps = conn.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, year);
            ps.setString(2, program);

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

    public BatchInfo batchInfo(Connection conn, int batch, String userProgram){
        String qry = "SELECT *FROM batch WHERE year = ? AND program = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setInt(1, batch);
            preparedStatement.setString(2, userProgram);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                int year = rs.getInt(2);
                String program = rs.getString(3);

                return new BatchInfo(id, year, program);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<BatchInfo> getProgramAndBatch(Connection conn){
        List<BatchInfo> batchInfo = new ArrayList<>();
        String qry = "SELECT *FROM batch";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(qry);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                BatchInfo info = new BatchInfo(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3)
                );
                batchInfo.add(info);
            }

            return batchInfo;

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
