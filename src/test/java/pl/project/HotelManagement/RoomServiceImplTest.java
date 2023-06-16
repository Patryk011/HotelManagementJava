package pl.project.HotelManagement;
import pl.project.services.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.project.dto.RoomDTO;
import pl.project.entity.Hotel;
import pl.project.entity.Room;
import pl.project.mapper.RoomMapper;
import pl.project.repository.HotelRepository;
import pl.project.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRooms() {
        // Given
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room();
        Room room2 = new Room();
        rooms.add(room1);
        rooms.add(room2);

        List<RoomDTO> expectedRoomDTOs = new ArrayList<>();
        RoomDTO roomDTO1 = new RoomDTO();
        RoomDTO roomDTO2 = new RoomDTO();
        expectedRoomDTOs.add(roomDTO1);
        expectedRoomDTOs.add(roomDTO2);

        when(roomRepository.findAll()).thenReturn(rooms);
        when(roomMapper.mapToDto(rooms)).thenReturn(expectedRoomDTOs);

        // When
        List<RoomDTO> resultRoomDTOs = roomService.getAllRooms();

        // Then
        assertEquals(expectedRoomDTOs, resultRoomDTOs);
        verify(roomRepository, times(1)).findAll();
        verify(roomMapper, times(1)).mapToDto(rooms);
    }

    @Test
    public void testFindByHotelId() {
        // Given
        Long hotelId = 1L;
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room();
        Room room2 = new Room();
        rooms.add(room1);
        rooms.add(room2);

        List<RoomDTO> expectedRoomDTOs = new ArrayList<>();
        RoomDTO roomDTO1 = new RoomDTO();
        RoomDTO roomDTO2 = new RoomDTO();
        expectedRoomDTOs.add(roomDTO1);
        expectedRoomDTOs.add(roomDTO2);

        when(roomRepository.findByHotelId(hotelId)).thenReturn(rooms);
        when(roomMapper.mapToDto(rooms)).thenReturn(expectedRoomDTOs);

        // When
        List<RoomDTO> resultRoomDTOs = roomService.findByHotelId(hotelId);

        // Then
        assertEquals(expectedRoomDTOs, resultRoomDTOs);
        verify(roomRepository, times(1)).findByHotelId(hotelId);
        verify(roomMapper, times(1)).mapToDto(rooms);
    }




    @Test
    public void testGetById() {
        // Given
        Long roomId = 1L;
        Room room = new Room();
        RoomDTO expectedRoomDTO = new RoomDTO();

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(roomMapper.mapToDto(room)).thenReturn(expectedRoomDTO);

        // When
        RoomDTO resultRoomDTO = roomService.getById(roomId);

        // Then
        assertEquals(expectedRoomDTO, resultRoomDTO);
        verify(roomRepository, times(1)).findById(roomId);
        verify(roomMapper, times(1)).mapToDto(room);
    }

    @Test
    public void testAddRoom() {
        // Given
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setHotelId(1L);
        roomDTO.setNumber(Integer.parseInt("101"));

        Room existingRoom = new Room();
        when(roomRepository.findByHotelIdAndNumber(roomDTO.getHotelId(), roomDTO.getNumber())).thenReturn(null);

        Room room = new Room();
        Room savedRoom = new Room();
        RoomDTO expectedRoomDTO = new RoomDTO();
        when(roomMapper.mapFromDto(roomDTO)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(savedRoom);
        when(roomMapper.mapToDto(savedRoom)).thenReturn(expectedRoomDTO);

        // When
        RoomDTO resultRoomDTO = roomService.addRoom(roomDTO);

        // Then
        assertEquals(expectedRoomDTO, resultRoomDTO);
        verify(roomRepository, times(1)).findByHotelIdAndNumber(roomDTO.getHotelId(), roomDTO.getNumber());
        verify(roomRepository, times(1)).save(room);
        verify(roomMapper, times(1)).mapFromDto(roomDTO);
        verify(roomMapper, times(1)).mapToDto(savedRoom);
    }

    @Test
    public void testUpdateRoom() {
        // Given
        Long roomId = 1L;
        RoomDTO roomDTO = new RoomDTO();
        Room existingRoom = new Room();
        Room updatedRoom = new Room();
        RoomDTO expectedRoomDTO = new RoomDTO();

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(existingRoom));
        when(roomMapper.mapFromDto(existingRoom, roomDTO)).thenReturn(updatedRoom);
        when(roomRepository.save(updatedRoom)).thenReturn(updatedRoom);
        when(roomMapper.mapToDto(updatedRoom)).thenReturn(expectedRoomDTO);

        // When
        RoomDTO resultRoomDTO = roomService.updateRoom(roomId, roomDTO);

        // Then
        assertEquals(expectedRoomDTO, resultRoomDTO);
        verify(roomRepository, times(1)).findById(roomId);
        verify(roomRepository, times(1)).save(updatedRoom);
        verify(roomMapper, times(1)).mapFromDto(existingRoom, roomDTO);
        verify(roomMapper, times(1)).mapToDto(updatedRoom);
    }

    @Test
    public void testGetRoomPrice() {
        // Given
        Long roomId = 1L;
        Room room = new Room();
        room.setPrice(100.0);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // When
        double resultPrice = roomService.getRoomPrice(roomId);

        // Then
        assertEquals(100.0, resultPrice);
        verify(roomRepository, times(1)).findById(roomId);
    }

    @Test
    public void testDeleteRoom() {
        // Given
        Long roomId = 1L;

        // When
        roomService.deleteRoom(roomId);

        // Then
        verify(roomRepository, times(1)).deleteById(roomId);
    }
}