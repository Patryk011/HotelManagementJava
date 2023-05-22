package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.project.dto.EmailMessageDTO;
import pl.project.mapper.EmailMessageMapper;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final EmailMessageMapper messageMapper;


    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, EmailMessageMapper messageMapper) {
        this.mailSender = mailSender;
        this.messageMapper = messageMapper;
    }

    @Override
    public EmailMessageDTO sendEmail(EmailMessageDTO emailMessageDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(emailMessageDTO.getTo());
        simpleMailMessage.setSubject(emailMessageDTO.getSubject());
        simpleMailMessage.setText(emailMessageDTO.getMessage());

        mailSender.send(simpleMailMessage);


        return emailMessageDTO;
    }
}
