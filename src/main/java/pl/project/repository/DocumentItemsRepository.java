package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.Document_items;
@Repository
public interface DocumentItemsRepository extends JpaRepository<Document_items, Long> {
}
