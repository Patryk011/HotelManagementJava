package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.project.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByEmailAddress(String emailAddress);

    @Query("SELECT p FROM Person p WHERE p.promotionConsent = true ")
    List<Person> getSubscribers();


}
