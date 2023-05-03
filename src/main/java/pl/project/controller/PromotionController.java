package pl.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.services.PromotionService;

@RestController
@RequestMapping("/promotions")
public class PromotionController {


    private final PromotionService promotionService;


    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
}
