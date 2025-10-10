package view;

import controller.BookingController;
import domain.Booking;
import exception.*;
import util.Inputs;
import util.Messages;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class BookingView {
//    private final BookingController controller;
//
//    public BookingView(BookingController controller) {
//        this.controller = controller;
//    }
//
//    public void showMenu() {
//        String option;
//        do {
//            option = Inputs.requestString(
//                    "BOOKING MANAGER\n" +
//                            "1. Create Booking\n" +
//                            "2. Find Booking by ID\n" +
//                            "3. Cancel Booking\n" +
//                            "4. List All Bookings\n" +
//                            "5. List Bookings by Filters\n" +
//                            "6. Exit\n" +
//                            "Choose an option:",
//                    "Main Menu"
//            );
//
//            // Se valida que la opción no sea nula (si el usuario cierra la ventana)
//            if (option == null) {
//                option = "6"; // Salir si se presiona cancelar o se cierra
//            }
//
//            switch (option) {
//                case "1":
//                    createBookingView();
//                    break;
//                case "2":
//                    findBookingView();
//                    break;
//                case "3":
//                    cancelBookingView(); // <-- NUEVO MÉTODO
//                    break;
//                case "4":
//                    listAllBookingsView();
//                    break;
//                case "5":
//                    listByFiltersView(); // <-- NUEVO MÉTODO
//                    break;
//                case "6":
//                    Messages.showInfoMessage("Goodbye!", "Exit");
//                    break;
//                default:
//                    Messages.showWarningMessage("Invalid option, please try again.", "Warning");
//                    break;
//            }
//        } while (!"6".equals(option));
//    }
//
//    private void createBookingView() {
//        try {
//            Messages.showInfoMessage("Enter the new booking details.", "New Booking");
//
//            int roomId = Inputs.requestInteger("Enter Room ID:", "Input");
//            LocalDate date = LocalDate.parse(Inputs.requestString("Enter Date (YYYY-MM-DD):", "Input"));
//            LocalTime startTime = LocalTime.parse(Inputs.requestString("Enter Start Time (HH:MM):", "Input"));
//            LocalTime endTime = LocalTime.parse(Inputs.requestString("Enter End Time (HH:MM):", "Input"));
//            String organizer = Inputs.requestString("Enter Organizer's Name:", "Input");
//
//            Booking newBooking = new Booking(0, roomId, date, startTime, endTime, organizer);
//            controller.createBooking(newBooking);
//
//            Messages.showSuccessMessage("STATUS 201: Booking created successfully!", "Success");
//
//        } catch (DateTimeParseException | NumberFormatException e) {
//            Messages.showErrorMessage("STATUS 400: Invalid date, time, or number format.", "Format Error");
//        } catch (BadRequestException e) {
//            Messages.showErrorMessage("STATUS 400: " + e.getMessage(), "Bad Request");
//        } catch (NotFoundException e) {
//            Messages.showErrorMessage("STATUS 404: " + e.getMessage(), "Not Found");
//        } catch (ConflictException e) {
//            Messages.showErrorMessage("STATUS 409: " + e.getMessage(), "Conflict");
//        } catch (ServiceException e) {
//            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
//            e.getCause().printStackTrace();
//        } catch (Exception e) {
//            // Captura el caso donde el usuario cierra una ventana de input
//            if (e instanceof NullPointerException) {
//                Messages.showWarningMessage("Operation canceled.", "Canceled");
//            } else {
//                Messages.showErrorMessage("An unexpected error occurred: " + e.getMessage(), "Error");
//            }
//        }
//    }
//
//    private void findBookingView() {
//        try {
//            int id = Inputs.requestInteger("Enter the Booking ID to search:", "Find Booking");
//            Booking booking = controller.findById(id);
//            String details = String.format(
//                    "Booking Found:\nID: %d\nRoom ID: %d\nDate: %s\nTime: %s - %s\nOrganizer: %s",
//                    booking.getIdBooking(), booking.getIdRoom(), booking.getDate(),
//                    booking.getStartTime(), booking.getEndTime(), booking.getOrganizer()
//            );
//            Messages.showInfoMessage(details, "Booking Details");
//        } catch (NumberFormatException e) {
//            Messages.showErrorMessage("STATUS 400: Please enter a valid numeric ID.", "Format Error");
//        } catch (NotFoundException e) {
//            Messages.showErrorMessage("STATUS 404: " + e.getMessage(), "Not Found");
//        } catch (ServiceException e) {
//            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
//        } catch (Exception e) {
//            if (e instanceof NullPointerException) {
//                Messages.showWarningMessage("Search canceled.", "Canceled");
//            } else {
//                Messages.showErrorMessage("An unexpected error occurred: " + e.getMessage(), "Error");
//            }
//        }
//    }
//
//    /**
//     * Muestra todas las reservas existentes en una ventana con barra de desplazamiento.
//     */
//    private void listAllBookingsView() {
//        try {
//            // 1. Llama al controlador para obtener la lista de todas las reservas
//            List<Booking> bookings = controller.li;
//
//            // 2. Verifica si la lista está vacía
//            if (bookings.isEmpty()) {
//                Messages.showInfoMessage("There are no bookings registered yet.", "All Bookings");
//                return; // Termina el método si no hay nada que mostrar
//            }
//
//            // 3. Construye un string largo con la información de todas las reservas
//            StringBuilder sb = new StringBuilder("--- ALL BOOKINGS ---\n\n");
//            for (Booking booking : bookings) {
//                sb.append("ID: ").append(booking.getIdBooking())
//                        .append(" | Room: ").append(booking.getIdRoom())
//                        .append(" | Date: ").append(booking.getDate())
//                        .append(" | Time: ").append(booking.getStartTime()).append("-").append(booking.getEndTime())
//                        .append(" | Organizer: ").append(booking.getOrganizer())
//                        .append("\n-------------------------------------------------\n");
//            }
//
//            // 4. Muestra el resultado en un JTextArea dentro de un JScrollPane
//            JTextArea textArea = new JTextArea(sb.toString());
//            textArea.setEditable(false);
//            JScrollPane scrollPane = new JScrollPane(textArea);
//            scrollPane.setPreferredSize(new Dimension(500, 300)); // Ajusta el tamaño de la ventana
//
//            JOptionPane.showMessageDialog(null, scrollPane, "All Bookings List", JOptionPane.INFORMATION_MESSAGE);
//
//        } catch (ServiceException e) {
//            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
//        } catch (Exception e) {
//            Messages.showErrorMessage("An unexpected error occurred while listing bookings: " + e.getMessage(), "Error");
//        }
//    }
//
//
//
//    private void cancelBookingView() {
//        try {
//            int id = Inputs.requestInteger("Enter the Booking ID to cancel:", "Cancel Booking");
//            controller.cancelBooking(id);
//            Messages.showSuccessMessage("STATUS 200: Booking with ID " + id + " canceled successfully.", "Success");
//        } catch (NumberFormatException e) {
//            Messages.showErrorMessage("STATUS 400: Please enter a valid numeric ID.", "Format Error");
//        } catch (NotFoundException e) {
//            Messages.showErrorMessage("STATUS 404: " + e.getMessage(), "Not Found");
//        } catch (ServiceException e) {
//            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
//        } catch (Exception e) {
//            if (e instanceof NullPointerException) {
//                Messages.showWarningMessage("Operation canceled.", "Canceled");
//            } else {
//                Messages.showErrorMessage("An unexpected error occurred: " + e.getMessage(), "Error");
//            }
//        }
//    }
//
//    private void listByFiltersView() {
//        try {
//            Messages.showInfoMessage("Enter filter criteria. Leave blank to ignore a filter.", "Filter Bookings");
//
//            String startDateStr = Inputs.requestString("Enter Start Date (YYYY-MM-DD) or leave blank:", "Filter");
//            LocalDate startDate = startDateStr.isBlank() ? null : LocalDate.parse(startDateStr);
//
//            String endDateStr = Inputs.requestString("Enter End Date (YYYY-MM-DD) or leave blank:", "Filter");
//            LocalDate endDate = endDateStr.isBlank() ? null : LocalDate.parse(endDateStr);
//
//            String roomIdStr = Inputs.requestString("Enter Room ID or leave blank:", "Filter");
//            Integer roomId = roomIdStr.isBlank() ? null : Integer.parseInt(roomIdStr);
//
//            // Llama al controlador con los filtros
//            List<Booking> bookings = controller.listByFilters(startDate, endDate, roomId);
//
//            // Usa el mismo método de visualización que listAllBookingsView
//            displayBookingList(bookings, "Filtered Bookings List");
//
//        } catch (DateTimeParseException | NumberFormatException e) {
//            // MULTI-CATCH
//            Messages.showErrorMessage("STATUS 400: Invalid format for date or room ID.", "Format Error");
//        } catch (ServiceException e) {
//            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
//        }
//    }
//
//    private void listAllBookingsView() {
//        try {
//            // CORRECCIÓN DEL TYPO: controller.li -> controller.listAll()
//            List<Booking> bookings = controller.listAll();
//            displayBookingList(bookings, "All Bookings List");
//        } catch (ServiceException e) {
//            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
//        } catch (Exception e) {
//            Messages.showErrorMessage("An unexpected error occurred while listing bookings: " + e.getMessage(), "Error");
//        }
//    }
//
//    // Metodo auxiliar para no repetir código al mostrar listas
//    private void displayBookingList(List<Booking> bookings, String title) {
//        if (bookings.isEmpty()) {
//            Messages.showInfoMessage("No bookings found matching the criteria.", title);
//            return;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (Booking booking : bookings) {
//            sb.append(String.format("ID: %d | Room: %d | Date: %s | Time: %s-%s | Organizer: %s\n",
//                    booking.getIdBooking(),
//                    booking.getIdRoom(),
//                    booking.getDate(),
//                    booking.getStartTime(),
//                    booking.getEndTime(),
//                    booking.getOrganizer()));
//            sb.append("-------------------------------------------------\n");
//        }
//
//        JTextArea textArea = new JTextArea(sb.toString());
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        scrollPane.setPreferredSize(new Dimension(600, 400));
//
//        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
}
