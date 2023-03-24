package pl.project.services;

import org.springframework.data.repository.query.Param;
import pl.project.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {


    List<Reservation> getAllReservations();

    Reservation getReservationById(int id);

    void saveOrUpdateReservation(Reservation reservation);



    void deleteReservation(int id);
}
