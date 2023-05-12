package pl.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.dto.RoomDTO;
import pl.project.entity.Room;
import pl.project.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {


    private final RoomService roomService;


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.getById(id);
    }

    @GetMapping("/type")
    public RoomDTO getRoomByType(@RequestParam String type) {
        return roomService.findByType(type);
    }

    @PostMapping
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.addRoom(roomDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

}

