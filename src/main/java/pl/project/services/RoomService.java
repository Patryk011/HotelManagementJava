package pl.project.services;

import pl.project.dto.RoomDTO;
import pl.project.entity.Customer;
import pl.project.entity.Room;

import java.util.List;

public interface RoomService {



    List<RoomDTO> getAllRooms();

    RoomDTO findByType(String type);

    RoomDTO getById(Long id);

    RoomDTO addRoom(RoomDTO roomDTO);


    boolean isFree(Long id);

    void deleteRoom(Long id);

}
