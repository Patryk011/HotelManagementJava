package pl.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class HotelManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelManagementApplication.class, args);
    }

}