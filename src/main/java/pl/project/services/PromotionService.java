package pl.project.services;

import pl.project.entity.Promotion;
import pl.project.entity.PromotionNotification;

import java.util.List;

public interface PromotionService {


    List<Promotion> getAllPromotions();

    Promotion addPromotion(Promotion promotion);

    Promotion editPromotion(Promotion promotion);

    void deletePromotion(Long promotionId);

    PromotionNotification addNotification(PromotionNotification notification);

    void deleteNotification(Long notificationId);
}
