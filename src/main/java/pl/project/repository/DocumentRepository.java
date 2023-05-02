package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {


}
