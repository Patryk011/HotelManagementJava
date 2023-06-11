package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.Exception.HotelException;
import pl.project.dto.RoomDTO;
import pl.project.entity.Hotel;
import pl.project.entity.Room;
import pl.project.repository.CustomerRepository;
import pl.project.repository.HotelRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class RoomMapper {


    private final HotelRepository hotelRepository;


    public RoomMapper(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public RoomDTO mapToDto(Room room){
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());
        roomDTO.setHotelId(room.getHotel().getId());
        roomDTO.setNumber(room.getNumber());
        roomDTO.setType(room.getType());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setFree(room.isFree());

        return roomDTO;
    }
    public List<RoomDTO> mapToDto(Collection<Room> rooms){
        return rooms.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    public Room mapFromDto(RoomDTO roomDTO){
        Room room = new Room();

        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElseThrow(() -> new HotelException("Hotel not found"));


        room.setHotel(hotel);
        room.setFree(roomDTO.isFree());
        room.setNumber(roomDTO.getNumber());
        room.setType(roomDTO.getType());
        room.setPrice(roomDTO.getPrice());

        return room;
    }
    public Room mapFromDto(Room room, RoomDTO roomDTO){

        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElseThrow(() -> new HotelException("Hotel not found"));


        room.setHotel(hotel);
        room.setFree(roomDTO.isFree());
        room.setNumber(roomDTO.getNumber());
        room.setType(roomDTO.getType());
        room.setPrice(roomDTO.getPrice());

        return room;
    }
}
