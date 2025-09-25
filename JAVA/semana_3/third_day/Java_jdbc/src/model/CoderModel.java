package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// MODEL -> TODA LA LÓGICA DE NEGOCIO

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        // Abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        // Convertir objeto abstracto en coder
        Coder objCoder = (Coder) obj; // Objeto tipo Coder

        try {
            String sql = "INSERT INTO coder (name, age, clan) VALUES (?,?,?)";

            //Insertar datos a esa conexión que tenemos
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // Preparar el statement para ingresar los datos, ademas agregar el return generated keys para que devuelva el id

            objPrepare.setString(1, objCoder.getName());
            objPrepare.setInt(2, objCoder.getAge());
            objPrepare.setString(3, objCoder.getClan());

            // Ejecutar la query
            objPrepare.execute();
            // Obtener resultados con los id generados
            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()) {
                objCoder.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Coder was successfully added");

        } catch (SQLException error) {
            JOptionPane.showInputDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objCoder;
    }

    @Override
    public List<Object> findAll() {
        // Para guardar los coders de la BD
        List<Object> listCoders = new ArrayList<>();

        // Generar la conexión a la BD
        Connection objConnection = ConfigDB.openConnection();

        try {
            // Hacemos la sentencia SQL
            String sql = "SELECT * FROM coder";
            // Usamos el preparedStatemend que me permite hacer la consulta
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // EJecutamos el query y lo guardamos en una variable
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                // Crear coder para poder agregarlo a la lista
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setName(objResult.getString("name"));
                objCoder.setAge(objResult.getInt("age"));
                objCoder.setClan(objResult.getString("clan"));

                listCoders.add(objCoder);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listCoders;
    }

    // Buscar un usuario por ID
    public Coder findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try {
            String sql = "SELECT * FROM coder WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objCoder = new Coder(); // Se debe inicializar
                objCoder.setId(objResult.getInt("id"));
                objCoder.setName(objResult.getString("name"));
                objCoder.setAge(objResult.getInt("age"));
                objCoder.setClan(objResult.getString("clan"));
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public boolean update(Object obj) {

        Coder objCoder = (Coder) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isUpdate = false;

        try {

            String sql = "UPDATE coder SET name = ?, age = ?, clan = ? WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Setear los valores
            objPrepare.setString(1, objCoder.getName());
            objPrepare.setInt(2, objCoder.getAge());
            objPrepare.setString(3, objCoder.getClan());
            objPrepare.setInt(4, objCoder.getId());

            // Ejecutar la query
            int result = objPrepare.executeUpdate();

            // Si más de una columna fue modificada (eliminada) eso significa que fue eliminada
            if (result > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Coder updated successfully");
            }


        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Coder not found " + error.getMessage());
        }
        ConfigDB.closeConnection();

        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {

        // Convertir el objeto en un coder
        Coder objCoder = (Coder) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;

        try {
            // Hacemos la sentencia SQL
            String sql = "DELETE FROM coder WHERE id = ?";
            // Usamos el preparedStatemend que me permite hacer la consulta
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Paso el id del coder que nos pasaron por parametros para eliminarlo
            objPrepare.setInt(1, objCoder.getId());

            // Obtengo cuantas columnas fueron afectadas
            int result = objPrepare.executeUpdate();

            // Si más de una columna fue modificada (eliminada) eso significa que fue eliminada
            if (result > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Coder deleted successfully");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }


}
