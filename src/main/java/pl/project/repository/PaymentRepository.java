package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
