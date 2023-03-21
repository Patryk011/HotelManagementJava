package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.project.entity.Reservation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    @Query("SELECT r FROM Reservation r WHERE r.arrivalDate = :arrivalDate AND r.persons = :persons AND r.room NOT IN (SELECT r2.room FROM Reservation r2 WHERE r2.arrivalDate = :arrivalDate AND r2.persons = :persons)")
    List<Reservation> findAvailableRooms(@Param("arrivalDate") Date arrivalDate, @Param("persons") int persons);

}



