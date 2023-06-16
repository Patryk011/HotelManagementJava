package pl.project.HotelManagement;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import pl.project.email.EmailReceiver;

@SpringBootTest
class HotelManagementApplicationTests {

	@MockBean
	private JavaMailSender javaMailSender;

	@MockBean
	private EmailReceiver emailReceiver;
	@Test
	void contextLoads() {
	}

}
