package pl.project.controller;

import org.springframework.web.bind.annotation.*;
import pl.project.entity.Reservation;
import pl.project.services.PersonService;
import pl.project.services.ReservationService;

import java.util.List;

@RestController
public class ReservationController {


    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping("/reservations")
    public void saveOrUpdateReservation(@RequestBody Reservation reservation) {
        reservationService.saveOrUpdateReservation(reservation);
    }

    @DeleteMapping("{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }



}
