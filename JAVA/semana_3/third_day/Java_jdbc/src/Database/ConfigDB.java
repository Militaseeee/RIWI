package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc";
            String user = "root";
            String password = "TuNuevaContraseñaFuerte";

            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecté exitosamente");

        } catch (ClassNotFoundException error) {
            System.out.println("Driver no instalado" + error.getMessage());
        } catch (SQLException error) {
            System.out.println("Error al conectar la base de datos" + error.getMessage());
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
