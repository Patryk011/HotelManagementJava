package pl.project.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {





//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager get() {
        UserDetails worker = User.withUsername("worker")
                .password(getBcryptPasswordEncoder().encode("worker123"))
                .roles("WORKER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(getBcryptPasswordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(Arrays.asList(worker, admin));
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .headers(headers -> headers.frameOptions().sameOrigin())
               .csrf(csrf -> csrf
                       .disable())
                       .cors(withDefaults())
               .authorizeRequests((autz) -> autz
                       .requestMatchers("/h2-console/**").permitAll()
                       .anyRequest()
                       .authenticated()
                       .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                       .anyRequest()
                       .authenticated())
               .formLogin((formlogin) -> formlogin.permitAll())
               .logout().permitAll();




        return http.build();



    }


//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .anyRequest().authenticated())
//                .headers(headers -> headers.frameOptions().sameOrigin())
//                .httpBasic(Customizer.withDefaults()).build();
//
//
//
//    }




}
