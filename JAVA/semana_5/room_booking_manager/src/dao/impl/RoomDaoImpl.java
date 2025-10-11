package dao.impl;

import config.DbConfig;
import dao.interfaces.RoomDao;
import domain.Room;
import exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDao {

    @Override
    public Optional<Room> findById(int id) throws DataAccessException {
        String sql = "SELECT * FROM rooms WHERE id_room = ?";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet objRest = preparedStatement.executeQuery()) {
                if (objRest.next()) {
                    Room room = new Room();
                    room.setIdRoom(objRest.getInt("id_room"));
                    room.setAvailable(objRest.getBoolean("available"));
                    return Optional.of(room); // Returns the object wrapped in an Optional
                }
            }
        } catch (SQLException e) {
            // Wrap the technical exception in a data access exception
            throw new DataAccessException("Error finding room by ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public void create(Room object) throws DataAccessException {
    }

    @Override
    public List<Room> findAll() throws DataAccessException {
        String sql = "SELECT * FROM rooms ORDER BY id_room";
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet objRest = statement.executeQuery(sql)) {

            while (objRest.next()) {
                Room room = new Room();
                room.setIdRoom(objRest.getInt("id_room"));
                room.setAvailable(objRest.getBoolean("available"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching all rooms", e);
        }
        return rooms;
    }

    public void updateAvailableStatus(int idRoom, boolean available) throws DataAccessException {
        String sql = "UPDATE rooms SET available = ? WHERE id_room = ?";

        // Aquí usamos try-with-resources para asegurar que la Conexión y el PreparedStatement se cierren.
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Establece el valor del campo 'available' (TRUE o FALSE)
            preparedStatement.setBoolean(1, available);
            preparedStatement.setInt(2, idRoom);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Captura cualquier error de SQL y lo envuelve en tu excepción personalizada
            throw new DataAccessException("Error updating room availability status for ID: " + idRoom, e);
        }
    }

    @Override
    public void update(Room object) throws DataAccessException {
        String sql = "UPDATE rooms SET available = ? WHERE id_room = ?";

        // Use try-with-resources to automatically close the connection and statement
        try (Connection connection = DbConfig.getConnection(); // Gets a connection from your utility class
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setBoolean(1, object.isAvailable());
            preparedStatement.setInt(2, object.getIdRoom());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                // This case is handled by the service, but it's good practice
                throw new DataAccessException("Updating room failed, no rows affected. Room with ID " + object.getIdRoom() + " might not exist");
            }

        } catch (SQLException e) {
            // Wrap the technical SQL exception into your custom DataAccessException.
            throw new DataAccessException("Error updating room in the database", e);
        }
    }

    @Override
    public void delete(int id) throws DataAccessException {
    }
}