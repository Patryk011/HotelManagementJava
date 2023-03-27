package pl.project.services;

import pl.project.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    List<Person> getSubscribers();
    Person findByEmailAddress(String email);

    void addNewsletterSubscriber(Person person, boolean subscribe);

    Person getPersonById(int id);
    void deletePerson(int id);
}


