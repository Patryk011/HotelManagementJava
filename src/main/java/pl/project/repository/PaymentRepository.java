package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


    List<Payment> findByReservationId(Long reservationId);

}
