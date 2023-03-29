package pl.project.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.project.entity.NewsletterSubscriber;
import pl.project.entity.Person;
import pl.project.repository.NewsletterSubscriberRepository;
import pl.project.repository.PersonRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private NewsletterSubscriberRepository subscriberRepository;

    public PersonServiceImpl(PersonRepository personRepository, NewsletterSubscriberRepository subscriberRepository) {
        this.personRepository = personRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }


    @Override
    public Person findByEmailAddress(String email) {
        return personRepository.findByEmailAddress(email);
    }

    @Override
    public void savePerson(Person person, boolean subscribe) {
        if (subscribe) {
            person.setNewsletterSubscriber(true);
        } else {
            person.setNewsletterSubscriber(false);
        }
        personRepository.save(person);
    }

    @Override
    public void addNewsletterSubscriber(Person person) {
        if (person.isNewsletterSubscriber()) {
            subscriberRepository.save(new NewsletterSubscriber(person.getEmailAddress()));

        }
    }


    @Override
    public List<Person> getSubscribers() {
        return personRepository.getSubscribers();
    }


    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }



    @Override
    public void deletePerson(int id) {

        personRepository.deleteById(id);

    }
}
