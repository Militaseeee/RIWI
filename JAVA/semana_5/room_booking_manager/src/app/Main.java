package app;

import controller.BookingController;
import dao.impl.BookingDaoImpl;
import dao.impl.RoomDaoImpl;
import dao.interfaces.BookingDao;
import dao.interfaces.RoomDao;
import service.impl.BookingServicelmpl;
import service.interfaces.BookingService;
import view.BookingView;

public class Main {
    public static void main(String[] args) {
        // 1. Crear instancias de los DAO
        BookingDao bookingDao = new BookingDaoImpl();
        RoomDao roomDao = new RoomDaoImpl(); // Corregido el typo

        // 2. Crear instancia del Servicio, inyectando los DAO
        BookingService bookingService = new BookingServicelmpl(bookingDao, roomDao); // Corregido

        // 3. Crear instancia del Controlador, inyectando el Servicio
        BookingController bookingController = new BookingController(bookingService);

        // 4. Crear instancia de la Vista, inyectando el Controlador
        BookingView bookingView = new BookingView(bookingController);

        // 5. Iniciar la aplicación
        bookingView.showMenu();
    }
}