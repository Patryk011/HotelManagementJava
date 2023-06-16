package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.project.entity.Role;
import pl.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}


