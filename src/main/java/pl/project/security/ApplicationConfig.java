package pl.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import pl.project.repository.UserRepository;

@Configuration
public class ApplicationConfig {


    private final UserRepository userRepository;


    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
