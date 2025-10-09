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
        return List.of();
    }

    @Override
    public void create(Booking object) throws DataAccessException {
        String sql = "INSERT INTO bookings (id_room, booking_date, start_time, end_time, organizer) VALUES (?,?,?,?,?)";
        try(Connection connection = DbConfig.getConnection();
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, object.getIdBooking());
            preparedStatement.setDate(2, Date.valueOf(object.getDate()));
            preparedStatement.setTime(3, Time.valueOf(object.getStartTime()));
            preparedStatement.setTime(4, Time.valueOf(object.getEndTime()));
            preparedStatement.setString(5, object.getOrganizer());

            int rowsAffected =  preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Error creating new booking");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                booking.setIdBooking(objRest.getInt("id_room"));
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

    }

    @Override
    public Optional<Booking> findById(int id) throws DataAccessException {
        return Optional.empty();
    }
}
