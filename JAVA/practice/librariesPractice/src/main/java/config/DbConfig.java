package config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConfig {
    // Objeto para almacenar las propiedades cargadas del archivo
    private static final Properties properties = new Properties();

    // Bloque estático que se ejecuta una sola vez cuando la clase es cargada
    static {
        // Cargar el driver de PostgreSQL
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // Si el driver no se encuentra, es un error fatal.
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        }

        // Cargar las propiedades del archivo config.properties
        try (InputStream input = DbConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find config.properties");
            } else {
                // Cargar el archivo de propiedades
                properties.load(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Obtiene una conexión a la base de datos usando las propiedades cargadas.

    public static Connection getConnection() throws SQLException {
        // Usa las propiedades para establecer la conexión
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
    }

    // Metodo de utilidad para obtener cualquier otra propiedad del archivo de configuración.

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}