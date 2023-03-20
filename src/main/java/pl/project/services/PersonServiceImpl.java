package pl.project.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entity.Person;
import pl.project.repository.PersonRepository;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return null;
    }

    @Override
    public Person getPersonById(int id) {
        return null;
    }

    @Override
    public void saveOrUpdatePerson(Person person) {

    }

    @Override
    public void deletePerson(int id) {

    }
}
