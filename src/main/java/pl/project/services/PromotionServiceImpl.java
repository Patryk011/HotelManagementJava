package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.entity.Promotion;
import pl.project.entity.PromotionNotification;
import pl.project.repository.PromotionNotificationRepository;
import pl.project.repository.PromotionRepository;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Promotion addPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Transactional
    public Promotion editPromotion(Promotion promotion) {
        Promotion existingPromotion = promotionRepository.findById(promotion.getId())
                .orElseThrow(() -> new NoSuchElementException());

        existingPromotion.setTitle(promotion.getTitle());
        existingPromotion.setDescription(promotion.getDescription());
        existingPromotion.setStartDate(promotion.getStartDate());
        existingPromotion.setEndDate(promotion.getEndDate());
        existingPromotion.setActive(promotion.isActive());

        return promotionRepository.save(existingPromotion);
    }


    public void deletePromotion(Long promotionId) {
        promotionRepository.deleteById(promotionId);
    }


    public PromotionNotification addNotification(PromotionNotification notification) {
        return notificationRepository.save(notification);
    }


    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}

