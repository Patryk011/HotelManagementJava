package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.PromotionNotification;

@Repository
public interface PromotionNotificationRepository extends JpaRepository<PromotionNotification, Long> {
}
