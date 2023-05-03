package pl.project.services;

import org.springframework.stereotype.Service;
import pl.project.repository.PromotionNotificationRepository;
import pl.project.repository.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService {


    private final PromotionRepository promotionRepository;

    private final PromotionNotificationRepository notificationRepository;


    public PromotionServiceImpl(PromotionRepository promotionRepository, PromotionNotificationRepository notificationRepository) {
        this.promotionRepository = promotionRepository;
        this.notificationRepository = notificationRepository;
    }
}
