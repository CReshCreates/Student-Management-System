package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public Connection connection(){
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIS", user, password);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
