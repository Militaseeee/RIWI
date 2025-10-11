package service.interfaces;

import domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    void updateRoomAvailability(int roomId, boolean isAvailable);
}
