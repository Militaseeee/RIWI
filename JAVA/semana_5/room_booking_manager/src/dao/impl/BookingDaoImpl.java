package dao.impl;

import config.DbConfig;
import dao.interfaces.BookingDao;
import domain.Booking;
import exception.DataAccessException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDaoImpl implements BookingDao {


    @Override
    public List<Booking> findByFilters(LocalDate startDate, LocalDate endDate, Integer idRoom) throws DataAccessException {
        // Construcción dinámica de la consulta SQL
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM bookings WHERE 1=1");
        if (startDate != null) {
            sqlBuilder.append(" AND booking_date >= ?");
        }
        if (endDate != null) {
            sqlBuilder.append(" AND booking_date <= ?");
        }
        if (idRoom != null) {
            sqlBuilder.append(" AND id_room = ?");
        }

        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString())) {

            int paramIndex = 1; // Contador para los parámetros
            if (startDate != null) {
                preparedStatement.setDate(paramIndex++, Date.valueOf(startDate));
            }
            if (endDate != null) {
                preparedStatement.setDate(paramIndex++, Date.valueOf(endDate));
            }
            if (idRoom != null) {
                preparedStatement.setInt(paramIndex, idRoom);
            }

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setIdBooking(rs.getInt("id_booking"));
                    booking.setIdRoom(rs.getInt("id_room"));
                    booking.setDate(rs.getDate("booking_date").toLocalDate());
                    booking.setStartTime(rs.getTime("start_time").toLocalTime());
                    booking.setEndTime(rs.getTime("end_time").toLocalTime());
                    booking.setOrganizer(rs.getString("organizer"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error filtering bookings", e);
        }
        return bookings;
    }

    @Override
    public void create(Booking object) throws DataAccessException {
        String sql = "INSERT INTO bookings (id_room, booking_date, start_time, end_time, organizer) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = DbConfig.getConnection();
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, object.getIdBooking());
            preparedStatement.setDate(2, Date.valueOf(object.getDate()));
            preparedStatement.setTime(3, Time.valueOf(object.getStartTime()));
            preparedStatement.setTime(4, Time.valueOf(object.getEndTime()));
            preparedStatement.setString(5, object.getOrganizer());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error creating new booking", e);
        }
    }

    @Override
    public List<Booking> findAll() throws DataAccessException {
        String sql = "SELECT * FROM bookings";
        List<Booking> bookings = new ArrayList<>();

        try (Connection objConnection = DbConfig.getConnection();
             Statement statement = objConnection.createStatement();
             ResultSet objRest = statement.executeQuery(sql)) {

            while(objRest.next()) {

                Booking booking = new Booking();
                booking.setIdBooking(objRest.getInt("id_booking"));
                booking.setIdRoom(objRest.getInt("id_room"));
                booking.setDate(objRest.getDate("date_booking").toLocalDate());
                booking.setStartTime(objRest.getTime("start_time").toLocalTime());
                booking.setEndTime(objRest.getTime("end_time").toLocalTime());
                booking.setOrganizer(objRest.getString("organizer"));

                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error listing reservations", e);
        }
        return bookings;
    }

    @Override
    public void update(Booking object) throws DataAccessException {

    }

    @Override
    public void delete(int id) throws DataAccessException {
        String sql = "DELETE FROM bookings WHERE id_booking = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();

            // Opcional: verificar si algo se borró realmente
            if (affectedRows == 0) {
                // Esto ayuda a detectar si se intentó borrar un ID que ya no existía
                System.out.println("Warning: No booking found with ID " + id + " to delete.");
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error deleting booking with id: " + id, e);
        }
    }

    @Override
    public Optional<Booking> findById(int id) throws DataAccessException {
        String sql = "SELECT * FROM bookings WHERE id_booking = ?";

        try (Connection objConnection = DbConfig.getConnection();
             PreparedStatement objPrepare = objConnection.prepareStatement(sql)) {

            objPrepare.setInt(1, id);

            ResultSet objRest = objPrepare.executeQuery();
            if (objRest.next()) {
                Booking booking = new Booking();
                booking.setIdBooking(objRest.getInt("id_booking"));
                booking.setIdRoom(objRest.getInt("id_room"));
                booking.setDate(objRest.getDate("booking_date").toLocalDate());
                booking.setStartTime(objRest.getTime("start_time").toLocalTime());
                booking.setEndTime(objRest.getTime("end_time").toLocalTime());
                booking.setOrganizer(objRest.getString("organizer"));
                return Optional.of(booking);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error finding booking by ID: " + id, e);
        }
        return Optional.empty();
    }
}
