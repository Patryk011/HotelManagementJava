package pl.project.services;

import pl.project.dto.ReservationDTO;
import pl.project.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> findByCustomerId(Long customerId);

    ReservationDTO findById(Long id);

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);

    void cancelReservation(Long reservationId);

    List<ReservationDTO> getAllReservations();
}
