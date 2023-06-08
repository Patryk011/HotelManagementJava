package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCustomerId(Long customerId);

    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId " +
            "AND r.endDate >= :startDate AND r.startDate <= :endDate")
    List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                  @Param("startDate") Date startDate,
                                                  @Param("endDate") Date endDate);
}
