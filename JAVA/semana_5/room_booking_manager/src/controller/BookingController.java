package controller;

import dao.impl.BookingDaoImpl;
import service.impl.BookingServicelmpl;
import service.interfaces.BookingService;

public class BookingController {

    private static final BookingService bookingService = new BookingServicelmpl(new BookingDaoImpl());
    private static final RoomDao roomDao;
}
