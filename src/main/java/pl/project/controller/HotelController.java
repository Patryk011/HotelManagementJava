package pl.project.controller;


import org.springframework.web.bind.annotation.*;
import pl.project.entity.Hotel;
import pl.project.services.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel) {
        hotelService.saveHotel(hotel);
    }

    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
    }
}

