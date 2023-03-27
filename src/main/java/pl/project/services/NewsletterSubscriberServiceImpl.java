package pl.project.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.project.entity.NewsletterSubscriber;
import pl.project.repository.NewsletterSubscriberRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class NewsletterSubscriberServiceImpl implements NewsletterSubscriberService{



    private final NewsletterSubscriberRepository newsletterSubscriberRepository;

    public NewsletterSubscriberServiceImpl(NewsletterSubscriberRepository newsletterSubscriberRepository) {
        this.newsletterSubscriberRepository = newsletterSubscriberRepository;
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
    public void saveOrUpdateSubscriber(NewsletterSubscriber subscriber) {
        newsletterSubscriberRepository.save(subscriber);

    }

    @Override
    public void deleteSubscriber(int id) {

    }
}
