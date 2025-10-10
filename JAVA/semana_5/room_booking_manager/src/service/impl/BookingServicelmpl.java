package service.impl;

import dao.interfaces.BookingDao;
import dao.interfaces.RoomDao;
import domain.Booking;
import domain.Room;
import exception.*;
import service.interfaces.BookingService;
import util.Messages;

import java.time.LocalDate;
import java.util.List;

public class BookingServicelmpl implements BookingService {

    private final BookingDao bookingDao;
    private final RoomDao roomDao;

    // We inject the DAO dependencies
    public BookingServicelmpl(BookingDao bookingDao, RoomDao roomDao) {
        this.bookingDao = bookingDao;
        this.roomDao = roomDao;
    }

    @Override
    public void createBooking(Booking booking) {
        // Validate mandatory data
        if (booking.getIdRoom() <= 0 || booking.getDate() == null || booking.getStartTime() == null ||
                booking.getEndTime() == null || booking.getOrganizer() == null || booking.getOrganizer().isBlank()) {
            throw new BadRequestException("All booking fields are required.");
        }
        if (booking.getStartTime().isAfter(booking.getEndTime()) || booking.getStartTime().equals(booking.getEndTime())) {
            throw new BadRequestException("Start time must be before end time.");
        }

        try {
            // Verifica existencia y disponibilidad de la sala
            Room room = roomDao.findById(booking.getIdRoom())
                    .orElseThrow(() -> new NotFoundException("Room with ID " + booking.getIdRoom() + " does not exist."));

            if (!room.isAvailable()) {
                throw new ConflictException("Room with ID " + booking.getIdRoom() + " is currently out of service.");
            }

            List<Booking> existingBookings = bookingDao.findByFilters(booking.getDate(), booking.getDate(), booking.getIdRoom());
            for (Booking existing : existingBookings) {
                if (booking.overlappingBookings(existing)) {
                    throw new ConflictException("Time slot conflict: The room is already booked in the selected interval.");
                }
            }

            bookingDao.create(booking);

        } catch (DataAccessException e) {
            throw new ServiceException("A technical error occurred while creating the booking.", e);
        } finally {
            System.out.println("Finished 'create booking' operation attempt.");
        }
    }

    @Override
    public Booking searchBooking(int idBooking) {
        try {
            return bookingDao.findById(idBooking)
                    .orElseThrow(() -> new NotFoundException("Booking with ID " + idBooking + " not found."));
        } catch (DataAccessException e) {
            throw new ServiceException("A technical error occurred while searching for the booking", e);
        }
    }

    @Override
    public void cancelBooking(int idBooking) {
        try {
            // First, we make sure the reservation exists before trying to delete it
            searchBooking(idBooking);
            bookingDao.delete(idBooking);
        } catch (DataAccessException e) {
            throw new ServiceException("A technical error occurred while canceling the booking", e);
        }
    }

    @Override
    public List<Booking> listBookings() {
        try {
            return bookingDao.findAll();
        } catch (DataAccessException e) {
            throw new ServiceException("A technical error occurred while listing the bookings", e);
        }
    }

    @Override
    public List<Booking> listBookingsByFilter(LocalDate startDate, LocalDate endDate, Integer idRoom) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new BadRequestException("Start date must be before or equal to end date");
        }
        try {
            return bookingDao.findByFilters(startDate, endDate, idRoom);
        } catch (DataAccessException dae) {
            // Wrapping
            throw new ServiceException("A technical error occurred while filtering bookings", dae);
        } finally {
            // finally visible (requisito del enunciado)
            System.out.println("Finished 'listBookingsByFilter' operation attempt");
            //Messages.showInfoMessage("Finished 'listBookingsByFilter' operation attempt.", "Info");
        }
    }
}