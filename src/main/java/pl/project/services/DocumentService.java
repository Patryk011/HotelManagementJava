package pl.project.services;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.dto.CustomerDTO;
import pl.project.dto.DocumentDTO;
import pl.project.dto.DocumentItemsDTO;
import pl.project.entity.Document;
import pl.project.entity.DocumentItems;

import java.util.List;

public interface DocumentService {



//    void updateDocumentState(Long documentId, boolean hasOffer, boolean hasReservation);

    List<DocumentDTO> findAllDocuments();
    DocumentDTO saveDocument(DocumentDTO documentDTO);

    DocumentItemsDTO saveDocumentItems(DocumentItemsDTO documentItemsDTO);

    DocumentDTO getDocumentById(Long id);

    void deleteDocumentById(Long id);

    DocumentItemsDTO getDocumentItemsById(Long id);

    void deleteDocumentItemsById(Long id);

    void updateDocumentItems(DocumentItemsDTO documentItemsDTO);
}
