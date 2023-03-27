package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.NewsletterSubscriber;

public interface NewsletterSubscriberRepository extends JpaRepository<NewsletterSubscriber, Integer> {


}
