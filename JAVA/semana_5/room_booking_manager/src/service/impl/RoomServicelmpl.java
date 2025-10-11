package service.impl;

import dao.interfaces.RoomDao;
import domain.Room;
import exception.DataAccessException;
import exception.NotFoundException;
import exception.ServiceException;
import service.interfaces.RoomService;

import java.util.List;

public class RoomServicelmpl implements RoomService {

    private final RoomDao roomDao;

    public RoomServicelmpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<Room> findAll() {
        try {
            return roomDao.findAll();
        } catch (DataAccessException e) {
            throw new ServiceException("A technical error occurred while fetching rooms", e);
        }
    }

    @Override
    public void updateRoomAvailability(int roomId, boolean isAvailable) {
        try {
            // Find the room, or throw an exception if it doesn't exist.
            Room room = roomDao.findById(roomId)
                    .orElseThrow(() -> new NotFoundException("Room with ID " + roomId + " not found."));

            room.setAvailable(isAvailable); // Update the status of the object
            roomDao.update(room); // Ask the DAO to persist the change in the database

        } catch (DataAccessException e) {
            // Wrap any database errors in a ServiceException
            throw new ServiceException("A technical error occurred while updating room status.", e);
        } finally {
            System.out.println("Finished 'update room availability' operation attempt.");
        }
    }
}
