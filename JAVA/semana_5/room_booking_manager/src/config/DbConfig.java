package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    // connection to supabase
    private static final String URL ="jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres";
    private static final String USER ="postgres.xxynpuumtpksqfcdxhdk";
    private static final String PASSWORD="militaseeee*";

    static{
        // load driver
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
