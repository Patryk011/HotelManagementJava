package pl.project.mapper;


import org.springframework.stereotype.Component;
import pl.project.dto.PromotionMsgDTO;
import pl.project.entity.PromotionMsg;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PromotionMsgMapper {

    public PromotionMsgDTO mapToDTO(PromotionMsg promotionMsg) {
        PromotionMsgDTO promotionMsgDTO = new PromotionMsgDTO();
        promotionMsgDTO.setId(promotionMsg.getId());
        promotionMsgDTO.setTitle(promotionMsg.getTitle());
        promotionMsgDTO.setDescription(promotionMsg.getDescription());
        return promotionMsgDTO;
    }

    public List<PromotionMsgDTO> mapToDto(Collection<PromotionMsg> messages){
        return messages.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PromotionMsg mapFromDTO(PromotionMsgDTO promotionMsgDTO) {
        PromotionMsg promotionMsg = new PromotionMsg();
        promotionMsg.setTitle(promotionMsgDTO.getTitle());
        promotionMsg.setDescription(promotionMsgDTO.getDescription());

        return promotionMsg;
    }

}
