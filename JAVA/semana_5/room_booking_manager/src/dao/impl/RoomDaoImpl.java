package dao.impl;

import config.DbConfig;
import dao.interfaces.RoomDao;
import domain.Room;
import exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // Podrías implementar esto si necesitas listar todas las salas
        return List.of();
    }

    @Override
    public void update(Room object) throws DataAccessException {
    }

    @Override
    public void delete(int id) throws DataAccessException {
    }
}