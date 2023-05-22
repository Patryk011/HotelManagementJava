package pl.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.dto.HotelDTO;
import pl.project.dto.RoomDTO;
import pl.project.services.HotelService;
import pl.project.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    private final HotelService hotelService;

    private final RoomService roomService;

    @Autowired
    public HotelController(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/{id}/rooms")
    public List<RoomDTO> findByHotelId(@PathVariable Long id) {
        return roomService.findByHotelId(id);
    }

    @PostMapping
    public HotelDTO saveHotel(@RequestBody HotelDTO hotelDTO) {
       return hotelService.saveHotel(hotelDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
    }
}

