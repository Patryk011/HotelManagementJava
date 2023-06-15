package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    String findByRole(String Role);
}
