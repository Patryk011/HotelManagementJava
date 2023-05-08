package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.HotelDTO;
import pl.project.entity.Hotel;
import pl.project.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {
    private final RoomMapper roomMapper;

    public HotelMapper(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }





    public HotelDTO mapToDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setRooms(roomMapper.mapToDto(hotel.getRooms()));
        return hotelDTO;
    }

    public List<HotelDTO> mapToDto(List<Hotel> hotels) {
        return hotels.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Hotel mapFromDTO(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());

        List<Room> rooms = hotelDTO.getRooms().stream()
                .map(roomDTO -> roomMapper.mapFromDto(roomDTO))
                .collect(Collectors.toList());

        hotel.setRooms(rooms);
        return hotel;
    }
}

