package model;

import database.CRUD;
import database.ConfigDB;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// MODEL -> TODA LA LÓGICA DE NEGOCIO

// Implementamos CRUD<Product>, indicando que T = Product.
public class ProductModel implements CRUD<Product>{

    @Override
    public Product insert(Product product) {

        String sql = "INSERT INTO products (name, price, stock) VALUES (?,?,?)";

        // Abrir conexión
        try (Connection objConnection = ConfigDB.openConnection();
             //Insertar datos a esa conexión que tenemos
             PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) { // Preparar el statement para ingresar los datos, ademas agregar el return generated keys para que devuelva el id

            objPrepare.setString(1, product.getName());
            objPrepare.setDouble(2, product.getPrice());
            objPrepare.setInt(3, product.getStock());

            // Ejecutar la query
            objPrepare.execute();

            // Obtener resultados con los id generados
            try (ResultSet objRest = objPrepare.getGeneratedKeys()) {
                if (objRest.next()) {
                    product.setId(objRest.getInt(1));
                }
            }
        } catch (SQLException error) {
            System.err.println("Error creating product: " + error.getMessage());
        }
        //ConfigDB.closeConnection();

        return product;
    }

    @Override
    public List<Product> findAll() {
        // Para guardar los products de la BD
        List<Product> listProducts = new ArrayList<>();

        // Hacemos la sentencia SQL
        String sql = "SELECT * FROM products ORDER BY id ASC";

        // Generar la conexión a la BD
        try (Connection objConnection = ConfigDB.openConnection();
             // Usamos el preparedStatemend que me permite hacer la consulta
             PreparedStatement objPrepare = objConnection.prepareStatement(sql)) {

            // EJecutamos el query y lo guardamos en una variable
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                listProducts.add(new Product(
                        objResult.getInt("id"),
                        objResult.getString("name"),
                        objResult.getDouble("price"),
                        objResult.getInt("stock")
                ));
            }

        } catch (SQLException error) {
            System.err.println("Error listing products: " + error.getMessage());
        }
        //ConfigDB.closeConnection();

        return listProducts;
    }

    @Override
    public boolean update(Product product) {

        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE id = ?";
        boolean isUpdate = false;

        try (Connection objConnection = ConfigDB.openConnection();
             PreparedStatement objPrepare = objConnection.prepareStatement(sql)) {

            // Setear los valores
            objPrepare.setString(1, product.getName());
            objPrepare.setDouble(2, product.getPrice());
            objPrepare.setInt(3, product.getStock());
            objPrepare.setInt(4, product.getId());

            // Ejecutar la query
            int result = objPrepare.executeUpdate();
            // Si más de una columna fue modificada (eliminada) eso significa que fue eliminada
            if (result > 0) {
                isUpdate = true;
            }

        } catch (SQLException error) {
            System.err.println("Error updating product: " + error.getMessage());
        }
        //ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(int id) {
        // Hacemos la sentencia SQL
        String sql = "DELETE FROM products WHERE id = ?";

        boolean isDeleted = false;

        try (Connection objConnection = ConfigDB.openConnection();
             PreparedStatement objPrepare = objConnection.prepareStatement(sql)) {

            // Paso el id del coder que nos pasaron por parametros para eliminarlo
            objPrepare.setInt(1, id);

            // Obtengo cuantas columnas fueron afectadas
            int result = objPrepare.executeUpdate();

            // Si más de una columna fue modificada (eliminada) eso significa que fue eliminada
            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException error) {
            System.err.println("Error deleting product: " + error.getMessage());
        }
        //ConfigDB.closeConnection();
        return isDeleted;
    }

    // Buscar un producto por ID
    public Product findById(int id) {

        String sql = "SELECT * FROM products WHERE id = ?";

        Product objProduct = null;

        try (Connection objConnection = ConfigDB.openConnection();
             PreparedStatement objPrepare = objConnection.prepareStatement(sql)) {

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objProduct = new Product(); // Se debe inicializar
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
            }

        } catch (SQLException error) {
            System.err.println("Error searching ID of product: " + error.getMessage());
        }
        //ConfigDB.closeConnection();
        return objProduct;
    }

    public List<Product> findByName(String name) {
        List<Product> listProducts = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?;";
        try (Connection objConnection = ConfigDB.openConnection();
             PreparedStatement objPrepare = objConnection.prepareStatement(sql)) {

            objPrepare.setString(1, "%" + name + "%");
            try (ResultSet objResult = objPrepare.executeQuery()) {
                while (objResult.next()) {
                    Product objProduct = new Product();
                    objProduct.setId(objResult.getInt("id"));
                    objProduct.setName(objResult.getString("name"));
                    objProduct.setPrice(objResult.getDouble("price"));
                    objProduct.setStock(objResult.getInt("stock"));
                    listProducts.add(objProduct);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding product by name: " + e.getMessage());
        }
        return listProducts;
    }

    public Product findByNameExact(String name) {
        String sql = "SELECT * FROM products WHERE name = ?;";
        Product objProduct = null;

        try (Connection objConnection = ConfigDB.openConnection();
             PreparedStatement objPrepare = objConnection.prepareStatement(sql)) {

            objPrepare.setString(1, name);
            try (ResultSet objResult = objPrepare.executeQuery()) {
                if (objResult.next()) {
                    objProduct = new Product();
                    objProduct.setId(objResult.getInt("id"));
                    objProduct.setName(objResult.getString("name"));
                    objProduct.setPrice(objResult.getDouble("price"));
                    objProduct.setStock(objResult.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding product by exact name: " + e.getMessage());
        }
        return objProduct;
    }
}