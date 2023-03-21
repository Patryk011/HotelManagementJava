package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.project.entity.NewsletterSubscriber;

import java.util.List;

public interface NewsletterRepository extends JpaRepository<NewsletterSubscriber, Integer> {

    @Query("SELECT ns FROM NewsletterSubscriber ns WHERE ns.person.promotionConsent = true")
    List<NewsletterSubscriber> findAllSubscribers();

}
