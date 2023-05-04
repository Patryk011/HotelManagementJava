package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.DocumentItems;
@Repository
public interface DocumentItemsRepository extends JpaRepository<DocumentItems, Long> {
}
