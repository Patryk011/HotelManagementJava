package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.PromotionMsg;
@Repository
public interface PromotionMsgRepository extends JpaRepository<PromotionMsg, Long> {
}
