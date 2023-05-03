package pl.project.services;

import pl.project.entity.Customer;
import pl.project.entity.Room;

import java.util.List;

public interface RoomService {



    List<Room> getAllRooms();

    Room findByType(String type);

    Room getById(Long id);

    Room addRoom(Room room);

    void deleteRoom(Long id);

}
