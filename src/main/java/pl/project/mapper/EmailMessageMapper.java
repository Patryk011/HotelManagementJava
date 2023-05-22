package pl.project.mapper;


import org.springframework.stereotype.Component;
import pl.project.dto.DocumentItemsDTO;
import pl.project.dto.EmailMessageDTO;
import pl.project.entity.DocumentItems;
import pl.project.entity.EmailMessage;
import pl.project.entity.Room;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailMessageMapper {


    public EmailMessageDTO mapToDto(EmailMessage emailMessage) {
        EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
        emailMessageDTO.setId(emailMessage.getId());
        emailMessageDTO.setTo(emailMessage.getTo());
        emailMessageDTO.setSubject(emailMessage.getSubject());
        emailMessage.setMessage(emailMessage.getMessage());

        return emailMessageDTO;
    }

    public List<EmailMessageDTO> mapToDto(Collection<EmailMessage> emailMessages) {
        return emailMessages.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public EmailMessage mapFromDto(EmailMessageDTO emailMessageDTO) {
        EmailMessage emailMessage = new EmailMessage();




        emailMessage.setId(emailMessageDTO.getId());
        emailMessage.setTo(emailMessageDTO.getTo());
        emailMessage.setSubject(emailMessageDTO.getSubject());
        emailMessage.setMessage(emailMessageDTO.getMessage());

        return emailMessage;
    }

    public EmailMessage mapFromDto(EmailMessage emailMessage, EmailMessageDTO emailMessageDTO) {


        emailMessage.setId(emailMessageDTO.getId());
        emailMessage.setTo(emailMessageDTO.getTo());
        emailMessage.setSubject(emailMessageDTO.getSubject());
        emailMessage.setMessage(emailMessageDTO.getMessage());

        return emailMessage;
    }
}

