package service;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class transactionManager {
    private static final DBUtil dbUtil = new DBUtil();

    public static void execute(TransactionOperation operation){
        Connection conn = null;
        try {
            conn = dbUtil.connection();
            conn.setAutoCommit(false);
            operation.run(conn);
            conn.commit();
        } catch (SQLException e) {
            try{
                if(conn!=null)
                    conn.rollback();
            }
            catch (Exception ignored)
            {}
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            try{
                if(conn!=null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            }catch(Exception ignored){}
        }
    }

    @FunctionalInterface
    public interface TransactionOperation{
        void run(Connection conn) throws Exception;
    }
}
