package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.DocumentDTO;
import pl.project.entity.Document;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<DocumentDTO> mapToDto(Collection<Document> documents) {
        return documents.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Document mapFromDto(DocumentDTO documentDTO) {
        Document document = new Document();

        document.setState(documentDTO.getState());
        document.setCreatedOffer(documentDTO.isCreatedOffer());
        document.setCreatedReservation(documentDTO.isCreatedReservation());
        document.setCreatedInvoice(documentDTO.isCreatedInvoice());

        return document;
    }
}


