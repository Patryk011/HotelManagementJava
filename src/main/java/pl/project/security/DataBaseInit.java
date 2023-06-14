package pl.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.project.entity.Role;
import pl.project.entity.User;
import pl.project.repository.UserRepository;

@Component
public class DataBaseInit implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("adminPassword"));
        admin.setRole(Role.ADMIN);

        User worker = new User();
        worker.setUsername("worker");
        worker.setPassword(passwordEncoder.encode("workerPassword"));
        worker.setRole(Role.WORKER);

        userRepository.save(admin);
        userRepository.save(worker);
    }
}

