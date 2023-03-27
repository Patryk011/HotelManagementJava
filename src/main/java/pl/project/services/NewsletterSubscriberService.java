package pl.project.services;

import pl.project.entity.NewsletterSubscriber;

import java.util.List;

public interface NewsletterSubscriberService {

    List<NewsletterSubscriber> getAllSubscribers();

    NewsletterSubscriber getSubscriberById(int id);

    void saveOrUpdateSubscriber(NewsletterSubscriber subscriber);



    void deleteSubscriber(int id);



}
