package pl.project.HotelManagement;
import pl.project.services.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.project.dto.HotelDTO;
import pl.project.entity.Hotel;
import pl.project.mapper.HotelMapper;
import pl.project.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    private HotelServiceImpl hotelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        hotelService = new HotelServiceImpl(hotelRepository, hotelMapper);
    }

    @Test
    public void testGetAllHotels() {
        // Given
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel());
        when(hotelRepository.findAll()).thenReturn(hotels);

        List<HotelDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new HotelDTO());
        when(hotelMapper.mapToDto(hotels)).thenReturn(expectedDTOs);

        // When
        List<HotelDTO> result = hotelService.getAllHotels();

        // Then
        assertEquals(expectedDTOs, result);
        verify(hotelRepository, times(1)).findAll();
        verify(hotelMapper, times(1)).mapToDto(hotels);
    }

    @Test
    public void testGetHotelById_existingHotel() {
        // Given
        Long hotelId = 1L;
        Hotel hotel = new Hotel();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        HotelDTO expectedDTO = new HotelDTO();
        when(hotelMapper.mapToDTO(hotel)).thenReturn(expectedDTO);

        // When
        HotelDTO result = hotelService.getHotelById(hotelId);

        // Then
        assertEquals(expectedDTO, result);
        verify(hotelRepository, times(1)).findById(hotelId);
        verify(hotelMapper, times(1)).mapToDTO(hotel);
    }

//    @Test
//    public void testGetHotelById_nonExistingHotel() {
//        // Given
//        Long hotelId = 1L;
//        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());
//
//        // When
//        NoSuchElementException exception = assertThrows(
//                NoSuchElementException.class,
//                () -> hotelService.getHotelById(hotelId)
//        );
//
//        // Then
//        assertEquals("Hotel with ID " + hotelId + " not found.", exception.getMessage());
//        verify(hotelRepository, times(1)).findById(hotelId);
//        verify(hotelMapper, never()).mapToDTO(any());
//    }

    @Test
    public void testSaveHotel() {
        // Given
        HotelDTO hotelDTO = new HotelDTO();
        Hotel hotel = new Hotel();
        when(hotelMapper.mapFromDTO(hotelDTO)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        HotelDTO expectedDTO = new HotelDTO();
        when(hotelMapper.mapToDTO(hotel)).thenReturn(expectedDTO);

        // When
        HotelDTO result = hotelService.saveHotel(hotelDTO);

        // Then
        assertEquals(expectedDTO, result);
        verify(hotelMapper, times(1)).mapFromDTO(hotelDTO);
        verify(hotelRepository, times(1)).save(hotel);
        verify(hotelMapper, times(1)).mapToDTO(hotel);
    }

    @Test
    public void testDeleteHotelById() {
        // Given
        Long hotelId = 1L;

        // When
        hotelService.deleteHotelById(hotelId);

        // Then
        verify(hotelRepository, times(1)).deleteById(hotelId);
    }

    @Test
    public void testUpdateHotel_existingHotel() {
        // Given
        Long hotelId = 1L;
        HotelDTO hotelDTO = new HotelDTO();

        Hotel existingHotel = new Hotel();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotel));

        Hotel updatedHotel = new Hotel();
        when(hotelMapper.mapFromDTO(existingHotel, hotelDTO)).thenReturn(updatedHotel);
        when(hotelRepository.save(updatedHotel)).thenReturn(updatedHotel);

        HotelDTO expectedDTO = new HotelDTO();
        when(hotelMapper.mapToDTO(updatedHotel)).thenReturn(expectedDTO);

        // When
        HotelDTO result = hotelService.updateHotel(hotelId, hotelDTO);

        // Then
        assertEquals(expectedDTO, result);
        verify(hotelRepository, times(1)).findById(hotelId);
        verify(hotelMapper, times(1)).mapFromDTO(existingHotel, hotelDTO);
        verify(hotelRepository, times(1)).save(updatedHotel);
        verify(hotelMapper, times(1)).mapToDTO(updatedHotel);
    }



}