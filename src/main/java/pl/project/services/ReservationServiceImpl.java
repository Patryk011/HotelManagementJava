package pl.project.services;


import pl.project.entity.Reservation;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {


    private final ReservationService reservationService;

    private final PersonService personService;


    public ReservationServiceImpl(ReservationService reservationService, PersonService personService) {
        this.reservationService = reservationService;
        this.personService = personService;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return null;
    }

    @Override
    public Reservation getReservationById(int id) {
        return null;
    }

    @Override
    public void saveOrUpdateReservation(Reservation reservation) {

    }

    @Override
    public void deleteReservation(int id) {

    }
}
