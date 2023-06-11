package pl.project.HotelManagement;
import pl.project.services.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.project.dto.PaymentDTO;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Payment;
import pl.project.entity.Reservation;
import pl.project.mapper.PaymentMapper;
import pl.project.repository.PaymentRepository;
import pl.project.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationService reservationService;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPayments() {
        // Given
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment());
        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<PaymentDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(new PaymentDTO());
        when(paymentMapper.mapToDto(paymentList)).thenReturn(expectedDTOList);

        // When
        List<PaymentDTO> result = paymentService.getAllPayments();

        // Then
        assertEquals(expectedDTOList, result);
        verify(paymentRepository, times(1)).findAll();
        verify(paymentMapper, times(1)).mapToDto(paymentList);
    }

    @Test
    public void testFindById_existingPayment() {
        // Given
        Long paymentId = 1L;
        Payment payment = new Payment();
        when(paymentRepository.findById(paymentId)).thenReturn(java.util.Optional.of(payment));

        PaymentDTO expectedDTO = new PaymentDTO();
        when(paymentMapper.mapToDTO(payment)).thenReturn(expectedDTO);

        // When
        PaymentDTO result = paymentService.findById(paymentId);

        // Then
        assertEquals(expectedDTO, result);
        verify(paymentRepository, times(1)).findById(paymentId);
        verify(paymentMapper, times(1)).mapToDTO(payment);
    }

    @Test
    public void testFindById_nonExistingPayment() {
        // Given
        Long paymentId = 1L;
        when(paymentRepository.findById(paymentId)).thenReturn(java.util.Optional.empty());

        // When
        NoSuchElementException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchElementException.class,
                () -> paymentService.findById(paymentId)
        );

        // Then
        assertEquals("Payment with ID " + paymentId + " not found.", exception.getMessage());
        verify(paymentRepository, times(1)).findById(paymentId);
        verify(paymentMapper, never()).mapToDTO(any());
    }

    @Test
    public void testDeletePayment() {
        // Given
        Long paymentId = 1L;

        // When
        paymentService.deletePayment(paymentId);

        // Then
        verify(paymentRepository, times(1)).deleteById(paymentId);
    }

//    @Test
//    public void testCalculateTotalCost() {
//        // Given
//        Long customerId = 1L;
//        Date startDate = new Date();
//        Date endDate = new Date(System.currentTimeMillis() + 86400000); // Dodanie 1 dnia do bieżącej daty
//        List<ReservationDTO> reservations = new ArrayList<>();
//        reservations.add(new ReservationDTO(1L, customerId, startDate, endDate, 1L));
//        when(reservationService.findByCustomerId(customerId)).thenReturn(reservations);
//
//        Long roomId = 1L;
//        double roomPrice = 100.0;
//        when(roomService.getRoomPrice(roomId)).thenReturn(roomPrice);
//
//        // When
//        double totalCost = paymentService.calculateTotalCost(customerId);
//
//        // Then
//        int expectedDuration = 1; // Duration calculated based on dates
//        double expectedTotalCost = roomPrice * expectedDuration;
//        assertEquals(expectedTotalCost, totalCost, 0.01); // Check if values are approximately equal
//        verify(reservationService, times(1)).findByCustomerId(customerId);
//        verify(roomService, times(1)).getRoomPrice(roomId);
//    }


}
