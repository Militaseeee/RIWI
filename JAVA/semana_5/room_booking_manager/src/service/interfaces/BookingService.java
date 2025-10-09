package service.interfaces;

import domain.Booking;

import java.util.List;

public interface BookingService {
    void createBooking(Booking booking);
    Booking searchBooking(int idBooking);
    void cancelBooking(int idBooking);
    List<Booking> listBookings();
}
