package service;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final DBUtil dbUtil = new DBUtil();

    public static void execute(TransactionOperation operation){
        Connection conn = null;
        try {
            conn = dbUtil.connection();
            conn.setAutoCommit(false);
            operation.run(conn);
            conn.commit();
        } catch (Exception e) {
            try{
                if(conn!=null)
                    conn.rollback();
            }
            catch (SQLException ignored) {}

            throw new RuntimeException(e);
        }finally{
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
