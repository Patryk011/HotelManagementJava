package pl.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.entity.Reservation;
import pl.project.services.PersonService;
import pl.project.services.ReservationService;

import java.util.List;

@RestController
public class ReservationController {


    private ReservationService reservationService;


    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }









}
