package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Document_items;

public interface DocumentItemsRepository extends JpaRepository<Document_items, Long> {
}
