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
public class NewsletterSubscriberServiceImpl implements NewsletterSubscriberService{



    private final NewsletterSubscriberRepository newsletterSubscriberRepository;
    private final PersonRepository personRepository;



    public NewsletterSubscriberServiceImpl(NewsletterSubscriberRepository newsletterSubscriberRepository, PersonRepository personRepository) {
        this.newsletterSubscriberRepository = newsletterSubscriberRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<NewsletterSubscriber> getAllSubscribers() {
        return newsletterSubscriberRepository.findAll();
    }

    @Override
    public NewsletterSubscriber getSubscriberById(int id) {
        return newsletterSubscriberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public void saveSubscriber(Person person) {
        if (person.isNewsletterSubscriber()) {
            newsletterSubscriberRepository.save(new NewsletterSubscriber(person));

        }
    }

    @Override
    public void deleteSubscriber(int id) {
        newsletterSubscriberRepository.deleteById(id);

    }
}
