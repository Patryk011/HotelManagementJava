package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.Promotion_Notification;

@Repository
public interface PromotionNotificationRepository extends JpaRepository<Promotion_Notification, Long> {
}
