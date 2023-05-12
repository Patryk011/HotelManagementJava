package pl.project.services;

import pl.project.dto.HotelDTO;
import pl.project.entity.Hotel;

import java.util.List;

public interface HotelService {


    List<HotelDTO> getAllHotels();


    HotelDTO getHotelById(Long id);


    HotelDTO saveHotel(HotelDTO hotelDTO);

    void deleteHotelById(Long id);
}
