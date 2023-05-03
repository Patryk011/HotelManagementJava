package pl.project.services;

import org.springframework.stereotype.Service;
import pl.project.entity.Document;
import pl.project.repository.DocumentItemsRepository;
import pl.project.repository.DocumentRepository;

import java.util.NoSuchElementException;

@Service
public class DocumentServiceImpl implements DocumentService {



    private final DocumentRepository documentRepository;

    private final DocumentItemsRepository documentItemsRepository;


    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentItemsRepository documentItemsRepository) {
        this.documentRepository = documentRepository;
        this.documentItemsRepository = documentItemsRepository;
    }

    public void updateDocumentStage(Long documentId, boolean hasOffer, boolean hasReservation) {
        Document document = documentRepository.findById(documentId).orElseThrow(() -> new NoSuchElementException());

            int stage = 0;
            if (hasOffer) {
                stage = 1;
            }
            if (hasReservation) {
                stage = 2;
            }
            document.setStage(stage);
            documentRepository.save(document);
        }
    }


