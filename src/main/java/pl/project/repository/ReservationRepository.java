package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCustomerId(Long customerId);
}
