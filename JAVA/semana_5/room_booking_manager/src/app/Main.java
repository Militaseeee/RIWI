package app;

import controller.BookingController;
import controller.RoomController;
import dao.impl.BookingDaoImpl;
import dao.impl.RoomDaoImpl;
import dao.interfaces.BookingDao;
import dao.interfaces.RoomDao;
import service.impl.BookingServicelmpl;
import service.impl.RoomServicelmpl;
import service.interfaces.BookingService;
import service.interfaces.RoomService;
import view.BookingView;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de los DAO
        BookingDao bookingDao = new BookingDaoImpl();
        RoomDao roomDao = new RoomDaoImpl();

        // Crear instancia del Servicio, inyectando los DAO
        BookingService bookingService = new BookingServicelmpl(bookingDao, roomDao); // Corregido
        RoomService roomService = new RoomServicelmpl(roomDao);

        // Crear instancia del Controlador, inyectando el Servicio
        BookingController bookingController = new BookingController(bookingService);
        RoomController roomController = new RoomController(roomService);

        // Crear instancia de la Vista, inyectando el Controlador
        BookingView bookingView = new BookingView(bookingController, roomController);

        // Iniciar la aplicación
        bookingView.showMenu();
    }
}