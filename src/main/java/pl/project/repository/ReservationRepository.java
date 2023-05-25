package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
