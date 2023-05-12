package pl.project.services;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.dto.DocumentDTO;
import pl.project.dto.DocumentItemsDTO;
import pl.project.entity.Document;
import pl.project.entity.DocumentItems;

public interface DocumentService {



//    void updateDocumentState(Long documentId, boolean hasOffer, boolean hasReservation);

    DocumentDTO saveDocument(DocumentDTO documentDTO);

    DocumentItemsDTO saveDocumentItems(DocumentItemsDTO documentItemsDTO);

    DocumentDTO getDocumentById(Long id);

    void deleteDocumentById(Long id);

    DocumentItemsDTO getDocumentItemsById(Long id);

    void deleteDocumentItemsById(Long id);

    void updateDocumentItems(DocumentItemsDTO documentItemsDTO);
}
