package pl.project.HotelManagement;
import pl.project.Exception.ReservationException;
import pl.project.services.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.project.dto.PaymentDTO;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Reservation;
import pl.project.entity.Room;
import pl.project.mapper.ReservationMapper;
import pl.project.repository.CustomerRepository;
import pl.project.repository.HotelRepository;
import pl.project.repository.ReservationRepository;
import pl.project.repository.RoomRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PaymentService paymentService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByCustomerId_existingCustomerId_returnsList() {
        // Given
        Long customerId = 1L;
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationRepository.findByCustomerId(customerId)).thenReturn(reservations);

        List<ReservationDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(new ReservationDTO());
        when(reservationMapper.mapToDTO(reservations)).thenReturn(expectedDTOList);

        // When
        List<ReservationDTO> result = reservationService.findByCustomerId(customerId);

        // Then
        assertEquals(expectedDTOList, result);
        verify(reservationRepository, times(1)).findByCustomerId(customerId);
        verify(reservationMapper, times(1)).mapToDTO(reservations);
    }



    @Test
    void findById_existingReservationId_returnsReservationDTO() {
        // Given
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        ReservationDTO expectedDTO = new ReservationDTO();
        when(reservationMapper.mapToDTO(reservation)).thenReturn(expectedDTO);

        // When
        ReservationDTO result = reservationService.findById(reservationId);

        // Then
        assertEquals(expectedDTO, result);
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(reservationMapper, times(1)).mapToDTO(reservation);
    }



    @Test
    void createReservation_validReservationDTO_returnsReservationDTO() {
        // Given
        ReservationDTO reservationDTO = new ReservationDTO();
        Reservation reservation = new Reservation();
        when(reservationMapper.mapFromDTO(reservationDTO)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        PaymentDTO paymentDTO = new PaymentDTO();
        when(paymentService.createPayment(reservation.getId())).thenReturn(paymentDTO);

        Room room = new Room();
        when(roomRepository.findById(reservationDTO.getRoomId())).thenReturn(Optional.of(room));
        when(roomRepository.save(room)).thenReturn(room);

        ReservationDTO expectedDTO = new ReservationDTO();
        when(reservationMapper.mapToDTO(reservation)).thenReturn(expectedDTO);

        // When
        ReservationDTO result = reservationService.createReservation(reservationDTO);

        // Then
        assertEquals(expectedDTO, result);
        assertEquals("Pending", reservation.getStatus());
        assertFalse(room.isFree());
        verify(reservationMapper, times(1)).mapFromDTO(reservationDTO);
        verify(reservationRepository, times(1)).save(reservation);
        verify(paymentService, times(1)).createPayment(reservation.getId());
        verify(roomRepository, times(1)).findById(reservationDTO.getRoomId());
        verify(roomRepository, times(1)).save(room);
        verify(reservationMapper, times(1)).mapToDTO(reservation);
    }








    @Test
    void updateReservation_nonExistingReservationId_throwsReservationException() {
        // Given
        Long reservationId = 1L;
        ReservationDTO reservationDTO = new ReservationDTO();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // When
        ReservationException exception = assertThrows(
                ReservationException.class,
                () -> reservationService.updateReservation(reservationId, reservationDTO)
        );

        // Then
        assertEquals("Reservation with ID " + reservationId + " not found.", exception.getMessage());
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(reservationMapper, never()).mapFromDTO(any(), any());
        verify(reservationRepository, never()).save(any());
        verify(reservationMapper, never()).mapToDTO((Reservation) any());
    }




    @Test
    void getAllReservations_returnsReservationDTOList() {
        // Given
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<ReservationDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(new ReservationDTO());
        when(reservationMapper.mapToDTO(reservations)).thenReturn(expectedDTOList);

        // When
        List<ReservationDTO> result = reservationService.getAllReservations();

        // Then
        assertEquals(expectedDTOList, result);
        verify(reservationRepository, times(1)).findAll();
        verify(reservationMapper, times(1)).mapToDTO(reservations);
    }
}