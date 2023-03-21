package pl.project.services;

import pl.project.entity.Reservation;

import java.util.List;

public interface ReservationService {


    List<Reservation> getAllReservations();

    Reservation getReservationById(int id);

    void saveOrUpdateReservation(Reservation reservation);

    void deleteReservation(int id);
}
