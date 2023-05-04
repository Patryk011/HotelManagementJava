package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.entity.Document;
import pl.project.entity.DocumentItems;
import pl.project.repository.DocumentItemsRepository;
import pl.project.repository.DocumentRepository;

import java.util.NoSuchElementException;

@Service
public class DocumentServiceImpl implements DocumentService {



    private final DocumentRepository documentRepository;

    private final DocumentItemsRepository documentItemsRepository;


    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentItemsRepository documentItemsRepository) {
        this.documentRepository = documentRepository;
        this.documentItemsRepository = documentItemsRepository;
    }

    @Transactional
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

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    public DocumentItems saveDocumentItems(DocumentItems documentItems) {
        return documentItemsRepository.save(documentItems);
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }

    public DocumentItems getDocumentItemsById(Long id) {
        return documentItemsRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public void deleteDocumentItemsById(Long id) {
        documentItemsRepository.deleteById(id);
    }

    @Transactional
    public void updateDocumentItems(DocumentItems documentItems) {
        DocumentItems editedItems = documentItemsRepository.findById(documentItems.getId()).orElseThrow(() -> new NoSuchElementException());
        editedItems.setRoom(documentItems.getRoom());
        editedItems.setQuantity(documentItems.getQuantity());
        editedItems.setAmount(documentItems.getAmount());


        documentItemsRepository.save(editedItems);
    }
}







