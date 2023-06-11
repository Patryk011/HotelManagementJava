package pl.project.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.project.Exception.ReservationException;
import pl.project.Exception.RoomException;
import pl.project.dto.PaymentDTO;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Reservation;
import pl.project.entity.Room;
import pl.project.mapper.ReservationMapper;
import pl.project.repository.CustomerRepository;
import pl.project.repository.HotelRepository;
import pl.project.repository.ReservationRepository;
import pl.project.repository.RoomRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final HotelRepository hotelRepository;

    private final CustomerRepository customerRepository;

    private final PaymentService paymentService;

    private final RoomRepository roomRepository;

    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HotelRepository hotelRepository, CustomerRepository customerRepository, PaymentService paymentService, RoomRepository roomRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.customerRepository = customerRepository;
        this.paymentService = paymentService;
        this.roomRepository = roomRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationDTO> findByCustomerId(Long customerId) {
        List<Reservation> reservations = reservationRepository.findByCustomerId(customerId);
        return reservationMapper.mapToDTO(reservations);
    }

    @Override
    public ReservationDTO findById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationException("Room not found"));
        return reservationMapper.mapToDTO(reservation);
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Long roomId = reservationDTO.getRoomId();
        Date startDate = reservationDTO.getStartDate();
        Date endDate = reservationDTO.getEndDate();


        if (!isRoomAvailable(roomId, startDate, endDate)) {
            throw new ReservationException("The room is already booked in this time.");
        }

        Reservation reservation = reservationMapper.mapFromDTO(reservationDTO);
        reservation.setStatus("Pending");
        reservation = reservationRepository.save(reservation);

        PaymentDTO paymentDTO = paymentService.createPayment(reservation.getId());

        Room room = roomRepository.findById(reservationDTO.getRoomId()).orElseThrow(() -> new RoomException("Room not found"));
        room.setFree(false);
        roomRepository.save(room);

        return reservationMapper.mapToDTO(reservation);
    }

    private boolean isRoomAvailable(Long roomId, Date startDate, Date endDate) {
        List<Reservation> reservations = reservationRepository.findOverlappingReservations(roomId, startDate, endDate);
        return reservations.isEmpty();
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException("Reservation with ID " + id + " not found."));

        Date oldStartDate = existingReservation.getStartDate();
        Date oldEndDate = existingReservation.getEndDate();
        Long oldRoomId = existingReservation.getRoom().getId();

        Date newStartDate = reservationDTO.getStartDate();
        Date newEndDate = reservationDTO.getEndDate();
        Long newRoomId = reservationDTO.getRoomId();

        if (!oldStartDate.equals(newStartDate) || !oldEndDate.equals(newEndDate)) {
            PaymentDTO paymentDTO = paymentService.updatePayment(existingReservation.getId(), oldRoomId, newStartDate, newEndDate);


            if (!oldRoomId.equals(newRoomId)) {
                if (!isRoomAvailable(newRoomId, newStartDate, newEndDate)) {
                    throw new ReservationException("The room is already booked in this time.");
                }
                paymentDTO = paymentService.updatePayment(existingReservation.getId(), newRoomId, newStartDate, newEndDate);
            }
        }

        existingReservation = reservationMapper.mapFromDTO(existingReservation, reservationDTO);
        reservationRepository.save(existingReservation);
        return reservationMapper.mapToDTO(existingReservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        ReservationDTO reservationDTO = findById(reservationId);
        if (reservationDTO != null) {
            paymentService.deletePayment(reservationDTO.getId());
            reservationRepository.deleteById(reservationId);
        } else {
            throw new ReservationException("Reservation with ID " + reservationId + " not found.");
        }
    }


    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationMapper.mapToDTO(reservationRepository.findAll());
    }
}
