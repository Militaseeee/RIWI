package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private int idBooking;
    private int idRoom;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String organizer;

    public Booking() {
    }

    public Booking(int idBooking, int idRoom, LocalDate date, LocalTime startTime, LocalTime endTime, String organizer) {
        this.idBooking = idBooking;
        this.idRoom = idRoom;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    // Method to validate overlapping schedules
    public boolean overlappingBookings(Booking other) {
        if (this.idRoom != other.idRoom || !this.date.equals(other.date)) {
            return false; // They do not overlap if they are in different rooms or on different dates
        }
        // A schedule overlaps if (Start < EndN) and (End > Start)
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }
}
