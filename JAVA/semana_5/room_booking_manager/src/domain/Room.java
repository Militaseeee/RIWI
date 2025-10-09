package domain;

public class Room {
    private int idRoom;
    private boolean available;

    public Room() {
    }

    public Room(int idRoom, boolean available) {
        this.idRoom = idRoom;
        this.available = available;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
