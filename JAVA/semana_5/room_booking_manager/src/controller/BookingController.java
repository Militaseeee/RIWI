package controller;

import domain.Booking;
import service.interfaces.BookingService;

import java.time.LocalDate;
import java.util.List;

public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    public void createBooking(Booking booking) {
        service.createBooking(booking);
    }

    public Booking findById(int id) {
        return service.searchBooking(id);
    }

    public List<Booking> listAll() {
        return service.listBookings();
    }

    public void cancelBooking(int id) {
        service.cancelBooking(id);
    }

    public List<Booking> listByFilters(LocalDate startDate, LocalDate endDate, Integer roomId) {
        return service.listBookingsByFilter(startDate, endDate, roomId);
    }
}