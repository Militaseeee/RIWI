package view;

import controller.BookingController;
import controller.RoomController;
import domain.Booking;
import domain.Room;
import exception.*;
import util.Inputs;
import util.Messages;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingView {
    private final BookingController bookingController ;
    private final RoomController roomController;

    public BookingView(BookingController bookingController, RoomController roomController) {
        this.bookingController  = bookingController;
        this.roomController = roomController;
    }

    public void showMenu() {
        String option;
        do {
            option = Inputs.requestString(
                    "BOOKING MANAGER\n" +
                            "1. Create Booking\n" +
                            "2. Find Booking by ID\n" +
                            "3. Cancel Booking\n" +
                            "4. List All Bookings\n" +
                            "5. List Bookings by Filters\n" +
                            "6. Exit\n" +
                            "Choose an option:",
                    "Main Menu"
            );
            if (option == null) {
                option = "6";
            }

            switch (option) {
                case "1":
                    createBookingView();
                    break;
                case "2":
                    findBookingView();
                    break;
                case "3":
                    cancelBookingView();
                    break;
                case "4":
                    listAllBookingsView();
                    break;
                case "5":
                    listByFiltersView();
                    break;
                case "6":
                    Messages.showInfoMessage("Goodbye!", "Exit");
                    break;
                default:
                    Messages.showWarningMessage("Invalid option, please try again", "Warning");
                    break;
            }
        } while (!"6".equals(option));
    }

    private void createBookingView() {
        try {
            // Get and display the list of rooms
            List<Room> rooms = this.roomController.findAll();
            if (rooms.isEmpty()) {
                Messages.showInfoMessage("There are no rooms available to boo", "No Rooms");
                return;
            }

            // Format rooms so that the user understands them
            List<String> roomOptions = rooms.stream()
                    .map(room -> String.format("Room %d (%s)",
                            room.getIdRoom(),
                            room.isAvailable() ? "Available" : "Out of Service"))
                    .collect(Collectors.toList());

            // Display the drop-down menu to select the room
            String selectedRoomStr = Inputs.requestSelection("Choose a room:", "Room Selection", roomOptions);
            if (selectedRoomStr == null) { // El usuario canceló
                Messages.showWarningMessage("Operation cancele", "Canceled");
                return;
            }

            // Extract the ID from the selected String
            int roomId = Integer.parseInt(selectedRoomStr.replaceAll("[^0-9]", ""));
            // extrae el número del texto y lo guarda como entero, lo cual es muy util cuando el texto mostrado en una interfaz (como un combo box) contiene tanto letras como numeros, pero solo necesitas el ID numerico

            // Request the rest of the data
            LocalDate date = LocalDate.parse(Inputs.requestString("Enter Date (YYYY-MM-DD):", "Input"));
            LocalTime startTime = LocalTime.parse(Inputs.requestString("Enter Start Time (HH:MM):", "Input"));
            LocalTime endTime = LocalTime.parse(Inputs.requestString("Enter End Time (HH:MM):", "Input"));
            String organizer = Inputs.requestString("Enter Organizer's Name:", "Input");

            Booking newBooking = new Booking(0, roomId, date, startTime, endTime, organizer);
            bookingController.createBooking(newBooking);

            Messages.showSuccessMessage("STATUS 201: Booking created successfully!", "Success");

        } catch (DateTimeParseException | NumberFormatException e) {
            Messages.showErrorMessage("STATUS 400: Invalid format.", "Format Error");
        } catch (BadRequestException e) {
            Messages.showErrorMessage("STATUS 400: " + e.getMessage(), "Bad Request");
        } catch (NotFoundException e) {
            Messages.showErrorMessage("STATUS 404: " + e.getMessage(), "Not Found");
        } catch (ConflictException e) {
            Messages.showErrorMessage("STATUS 409: " + e.getMessage(), "Conflict");
        } catch (ServiceException e) {
            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
            if (e.getCause() != null) e.getCause().printStackTrace();
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                Messages.showWarningMessage("Operation canceled", "Canceled");
            } else {
                Messages.showErrorMessage("An unexpected error occurred: " + e.getMessage(), "Error");
            }
        }
    }

    private void findBookingView() {
        try {
            int id = Inputs.requestInteger("Enter the Booking ID to search:", "Find Booking");
            Booking booking = bookingController.findById(id);
            String details = String.format(
                    "Booking Found:\nID: %d\nRoom ID: %d\nDate: %s\nTime: %s - %s\nOrganizer: %s",
                    booking.getIdBooking(), booking.getIdRoom(), booking.getDate(),
                    booking.getStartTime(), booking.getEndTime(), booking.getOrganizer()
            );
            Messages.showInfoMessage(details, "Booking Details");
        } catch (NumberFormatException e) {
            Messages.showErrorMessage("STATUS 400: Please enter a valid numeric ID.", "Format Error");
        } catch (NotFoundException e) {
            Messages.showErrorMessage("STATUS 404: " + e.getMessage(), "Not Found");
        } catch (ServiceException e) {
            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                Messages.showWarningMessage("Search canceled", "Canceled");
            } else {
                Messages.showErrorMessage("An unexpected error occurred: " + e.getMessage(), "Error");
            }
        }
    }

    private void cancelBookingView() {
        try {
            int id = Inputs.requestInteger("Enter the Booking ID to cancel:", "Cancel Booking");
            bookingController.cancelBooking(id);
            Messages.showSuccessMessage("STATUS 200: Booking with ID " + id + " canceled successfully.", "Success");
        } catch (NumberFormatException e) {
            Messages.showErrorMessage("STATUS 400: Please enter a valid numeric ID.", "Format Error");
        } catch (NotFoundException e) {
            Messages.showErrorMessage("STATUS 404: " + e.getMessage(), "Not Found");
        } catch (ServiceException e) {
            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                Messages.showWarningMessage("Operation canceled.", "Canceled");
            } else {
                Messages.showErrorMessage("An unexpected error occurred: " + e.getMessage(), "Error");
            }
        }
    }

    private void listByFiltersView() {
        try {
            Messages.showInfoMessage("Enter filter criteria. Leave blank to ignore a filter.", "Filter Bookings");

            String startDateStr = Inputs.requestString("Enter Start Date (YYYY-MM-DD) or leave blank:", "Filter");
            LocalDate startDate = startDateStr.isBlank() ? null : LocalDate.parse(startDateStr);

            String endDateStr = Inputs.requestString("Enter End Date (YYYY-MM-DD) or leave blank:", "Filter");
            LocalDate endDate = endDateStr.isBlank() ? null : LocalDate.parse(endDateStr);

            String roomIdStr = Inputs.requestString("Enter Room ID or leave blank:", "Filter");
            Integer roomId = roomIdStr.isBlank() ? null : Integer.parseInt(roomIdStr);

            // Call the controller with the filters
            List<Booking> bookings = bookingController.listByFilters(startDate, endDate, roomId);

            // Uses the same display method as listAllBookingsView
            displayBookingList(bookings, "Filtered Bookings List");

        } catch (DateTimeParseException | NumberFormatException e) {
            // MULTI-CATCH
            Messages.showErrorMessage("STATUS 400: Invalid format for date or room ID", "Format Error");
        } catch (BadRequestException e) {
            Messages.showErrorMessage("STATUS 400: " + e.getMessage(), "Bad Request");
        } catch (ServiceException e) {
            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
        }
    }

    private void listAllBookingsView() {
        try {
            List<Booking> bookings = bookingController.listAll();
            displayBookingList(bookings, "All Bookings List");
        } catch (ServiceException e) {
            Messages.showErrorMessage("STATUS 500: " + e.getMessage(), "Service Error");
        } catch (Exception e) {
            Messages.showErrorMessage("An unexpected error occurred while listing bookings: " + e.getMessage(), "Error");
        }
    }

    // Helper method to avoid repeating code when displaying lists
    private void displayBookingList(List<Booking> bookings, String title) {
        if (bookings.isEmpty()) {
            Messages.showInfoMessage("No bookings found matching the criteria", title);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Booking booking : bookings) {
            stringBuilder.append(String.format("ID: %d | Room: %d | Date: %s | Time: %s-%s | Organizer: %s\n",
                    booking.getIdBooking(),
                    booking.getIdRoom(),
                    booking.getDate(),
                    booking.getStartTime(),
                    booking.getEndTime(),
                    booking.getOrganizer()));
        }

        JTextArea textArea = new JTextArea(stringBuilder.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }
}