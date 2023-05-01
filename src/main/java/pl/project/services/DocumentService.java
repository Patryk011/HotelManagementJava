package pl.project.services;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentService {



    void updateDocumentStage(Long documentId, boolean hasOffer, boolean hasReservation);
}
