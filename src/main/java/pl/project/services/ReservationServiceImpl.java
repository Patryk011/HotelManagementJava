package pl.project.services;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.project.entity.Reservation;
import pl.project.repository.ReservationRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {


    private final ReservationRepository reservationRepository;


    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public void saveOrUpdateReservation(Reservation reservation) {
        reservationRepository.save(reservation);

    }

    @Override
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);

    }
}
