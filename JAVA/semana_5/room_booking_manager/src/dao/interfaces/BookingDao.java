package dao.interfaces;

import domain.Booking;
import exception.DataAccessException;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao extends Crud<Booking>{
    List<Booking> findByFilters(LocalDate startDate, LocalDate endDate, Integer idRoom) throws DataAccessException;
}
