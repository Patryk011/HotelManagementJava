package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
