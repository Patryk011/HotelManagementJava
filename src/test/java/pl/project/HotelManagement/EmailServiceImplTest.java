package pl.project.HotelManagement;

import pl.project.services.*;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.project.dto.EmailDTO;
import pl.project.email.EmailReceiver;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private EmailReceiver emailReceiver;

    private EmailServiceImpl emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailServiceImpl(mailSender, emailReceiver);
    }

    @Test
    public void testSendEmail() {
        // Given
        String to = "example@example.com";
        String subject = "Test Email";
        String message = "This is a test email.";

        // When
        emailService.sendEmail(to, subject, message);

        // Then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testGetEmails() throws MessagingException, IOException {
        // Given
        List<EmailDTO> expectedEmails = Collections.singletonList(new EmailDTO());
        when(emailReceiver.receiveEmails()).thenReturn(expectedEmails);

        // When
        List<EmailDTO> result = emailService.getEmails();

        // Then
        assertEquals(expectedEmails, result);
        verify(emailReceiver, times(1)).receiveEmails();
    }

    @Test
    public void testDeleteEmail() throws MessagingException {
        // Given
        int index = 0;

        // When
        emailService.deleteEmail(index);

        // Then
        verify(emailReceiver, times(1)).deleteEmail(index);
    }
}