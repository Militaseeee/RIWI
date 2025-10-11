package controller;

import domain.Room;
import exception.NotFoundException;
import exception.ServiceException;
import service.interfaces.RoomService;
import java.util.List;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public List<Room> findAll() {
        return this.roomService.findAll();
    }

    public void updateRoomAvailability(int roomId, boolean isAvailable) {
        // This method simply delegates the call to the service layer
        try {
            this.roomService.updateRoomAvailability(roomId, isAvailable);
        } catch (NotFoundException | ServiceException e) {
            throw e;
        }
    }
}