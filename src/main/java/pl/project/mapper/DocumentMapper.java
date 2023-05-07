package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.DocumentDTO;
import pl.project.entity.Document;

@Component
public class DocumentMapper {

    public DocumentDTO mapToDto(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(document.getId());
        documentDTO.setState(document.getState());
        documentDTO.setCreatedOffer(document.isCreatedOffer());
        documentDTO.setCreatedReservation(document.isCreatedReservation());
        documentDTO.setCreatedInvoice(document.isCreatedInvoice());
        documentDTO.setCustomerId(document.getCustomer().getId());

        return documentDTO;
    }

    public Document mapFromDto(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setId(documentDTO.getId());
        document.setState(documentDTO.getState());
        document.setCreatedOffer(documentDTO.isCreatedOffer());
        document.setCreatedReservation(documentDTO.isCreatedReservation());
        document.setCreatedInvoice(documentDTO.isCreatedInvoice());

        return document;
    }
}


