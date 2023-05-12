package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.dto.HotelDTO;
import pl.project.entity.Hotel;
import pl.project.mapper.HotelMapper;
import pl.project.repository.HotelRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HotelServiceImpl implements HotelService {



    private final HotelRepository hotelRepository;

    private final HotelMapper hotelMapper;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }


    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelMapper.mapToDto(hotelRepository.findAll());
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return hotelMapper.mapToDTO(hotel);
    }

    @Override
    public HotelDTO saveHotel(HotelDTO hotelDTO) {

        Hotel hotel = hotelMapper.mapFromDTO(hotelDTO);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.mapToDTO(hotel);
    }

    @Override
    public void deleteHotelById(Long id) {
        hotelRepository.deleteById(id);
    }
}
