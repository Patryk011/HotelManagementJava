package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Promotion_Notification;

public interface PromotionNotificationRepository extends JpaRepository<Promotion_Notification, Long> {
}
