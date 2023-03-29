package pl.project.services;

import pl.project.entity.NewsletterSubscriber;
import pl.project.entity.Person;

import java.util.List;

public interface NewsletterSubscriberService {

    List<NewsletterSubscriber> getAllSubscribers();

    NewsletterSubscriber getSubscriberById(int id);

    void saveSubscriber(Person person);



    void deleteSubscriber(int id);



}
