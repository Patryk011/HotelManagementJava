package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entity.Promotion;
import pl.project.repository.PromotionNotificationRepository;
import pl.project.repository.PromotionRepository;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {


    private final PromotionRepository promotionRepository;

    private final PromotionNotificationRepository notificationRepository;

    @Autowired

    public PromotionServiceImpl(PromotionRepository promotionRepository, PromotionNotificationRepository notificationRepository) {
        this.promotionRepository = promotionRepository;
        this.notificationRepository = notificationRepository;
    }


    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();

    }
}
