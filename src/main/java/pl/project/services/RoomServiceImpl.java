package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.dto.RoomDTO;
import pl.project.entity.Room;
import pl.project.mapper.RoomMapper;
import pl.project.repository.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoomServiceImpl implements RoomService{


    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomMapper.mapToDto(roomRepository.findAll());
    }

    @Override
    public List<RoomDTO> findByHotelId(Long hotelId) {
        List<Room> rooms = roomRepository.findByHotelId(hotelId);
        return roomMapper.mapToDto(rooms);
    }

    @Override
    public RoomDTO findByType(String type) {
        Room room = roomRepository.findByType(type);
        return roomMapper.mapToDto(room);
    }

    @Override
    public RoomDTO getById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return roomMapper.mapToDto(room);
    }

    @Override
    public RoomDTO addRoom(RoomDTO roomDTO) {

        Room existingRoom = roomRepository.findByHotelIdAndNumber(roomDTO.getHotelId(), roomDTO.getNumber());
        if (existingRoom != null) {
            throw new IllegalArgumentException("Room with this number " + roomDTO.getNumber() + " exist in this Hotel.");
        }

        Room room = roomMapper.mapFromDto(roomDTO);
        room.setFree(true);
        room = roomRepository.save(room);

        return roomMapper.mapToDto(room);
    }

    @Override
    public boolean isFree(Long id)
    {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        if(room.isFree()==true) return true;
        return false;
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);

    }
}
