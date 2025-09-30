package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?user=postgres.yvnzrdusxbcctafoyapo&password=Admin123*";
//            String user = "root";
//            String password = "TuNuevaContraseñaFuerte";

            objConnection = (Connection) DriverManager.getConnection(url);
            System.out.println("I connected successfully");

        } catch (ClassNotFoundException error) {
            System.out.println("Driver not installed" + error.getMessage());
        } catch (SQLException error) {
            System.out.println("Error connecting to database" + error.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Close successfully");
            }
        } catch (SQLException error) {
            System.out.println("Error" + error.getMessage());
        }
    }
}