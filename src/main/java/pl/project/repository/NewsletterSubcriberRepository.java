package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.NewsletterSubscriber;

public interface NewsletterSubcriberRepository extends JpaRepository<NewsletterSubscriber, Integer> {


}
