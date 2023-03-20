package pl.project.services;

import pl.project.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();
    Person getPersonById(int id);
    void saveOrUpdatePerson(Person person);
    void deletePerson(int id);
}


