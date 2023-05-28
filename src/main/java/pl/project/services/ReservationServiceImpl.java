package pl.project.services;

import org.springframework.stereotype.Service;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Customer;
import pl.project.entity.Reservation;
import pl.project.entity.Room;
import pl.project.mapper.ReservationMapper;
import pl.project.repository.CustomerRepository;
import pl.project.repository.HotelRepository;
import pl.project.repository.ReservationRepository;
import pl.project.repository.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final HotelRepository hotelRepository;

    private final CustomerRepository customerRepository;

    private final RoomRepository roomRepository;

    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HotelRepository hotelRepository, CustomerRepository customerRepository, RoomRepository roomRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.customerRepository = customerRepository;
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
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return reservationMapper.mapToDTO(reservation);
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.mapFromDTO(reservationDTO);
        reservation = reservationRepository.save(reservation);

        return reservationMapper.mapToDTO(reservation);
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation with ID " + id + " not found."));

        existingReservation = reservationMapper.mapFromDTO(existingReservation, reservationDTO);
        reservationRepository.save(existingReservation);
        return reservationMapper.mapToDTO(existingReservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationMapper.mapToDTO(reservationRepository.findAll());
    }
}
