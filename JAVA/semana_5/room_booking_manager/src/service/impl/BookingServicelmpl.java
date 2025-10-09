package service.impl;


import dao.impl.BookingDaoImpl;
import domain.Booking;
import service.interfaces.BookingService;

import java.util.List;

public class BookingServicelmpl implements BookingService {

    BookingDaoImpl bookingDao;

    public BookingServicelmpl(BookingDaoImpl bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void createBooking(Booking booking) {

    }

    @Override
    public Booking searchBooking(int idBooking) {
        return null;
    }

    @Override
    public void cancelBooking(int idBooking) {

    }

    @Override
    public List<Booking> listBookings() {
        return List.of();
    }
}
