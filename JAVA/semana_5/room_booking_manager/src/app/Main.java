package app;

public class Main {
    public static void main(String[] args) {
        // 1. Crear instancias de los DAO
        BookingDao bookingDao = new BookingDaoImpl();
        RoomDao roomDao = new RoomDaoImpl(); // Corregido el typo

        // 2. Crear instancia del Servicio, inyectando los DAO
        BookingService bookingService = new BookingServiceImpl(bookingDao, roomDao); // Corregido

        // 3. Crear instancia del Controlador, inyectando el Servicio
        BookingController bookingController = new BookingController(bookingService);

        // 4. Crear instancia de la Vista, inyectando el Controlador
        BookingView bookingView = new BookingView(bookingController);

        // 5. Iniciar la aplicación
        bookingView.showMenu();
    }
}