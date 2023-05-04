package pl.project.services;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Document;
import pl.project.entity.DocumentItems;

public interface DocumentService {



    void updateDocumentStage(Long documentId, boolean hasOffer, boolean hasReservation);

    Document saveDocument(Document document);

    DocumentItems saveDocumentItems(DocumentItems documentItems);

    Document getDocumentById(Long id);

    void deleteDocumentById(Long id);

    DocumentItems getDocumentItemsById(Long id);

    void deleteDocumentItemsById(Long id);

    void updateDocumentItems(DocumentItems documentItems);
}
