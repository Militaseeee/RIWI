package service.interfaces;

import domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    void createBooking(Booking booking);
    Booking searchBooking(int idBooking);
    void cancelBooking(int idBooking);
    List<Booking> listBookings();

    List<Booking> listBookingsByFilter(LocalDate startDate, LocalDate endDate, Integer idRoom);
}
