package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByEmailAddress(String emailAddress);


}
