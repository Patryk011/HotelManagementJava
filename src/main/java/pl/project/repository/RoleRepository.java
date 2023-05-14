package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
