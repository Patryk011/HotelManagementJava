package pl.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.dto.EmailMessageDTO;
import pl.project.services.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {


    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public EmailMessageDTO sendEmail(@RequestBody EmailMessageDTO emailMessageDTO) {
        return emailService.sendEmail(emailMessageDTO);
    }

}
