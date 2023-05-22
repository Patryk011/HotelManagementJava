package pl.project.services;


import pl.project.dto.EmailMessageDTO;

public interface EmailService {

    EmailMessageDTO sendEmail(EmailMessageDTO emailMessageDTO);
}
