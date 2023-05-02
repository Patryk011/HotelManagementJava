package pl.project.services;

import pl.project.entity.Hotel;

import java.util.List;

public interface HotelService {


    List<Hotel> getAllHotels();


    Hotel getHotelById(Long id);


    void saveHotel(Hotel hotel);

    void deleteHotelById(Long id);
}
