package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.DocumentItemsDTO;
import pl.project.entity.DocumentItems;
import pl.project.entity.Room;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentItemsMapper {


    public DocumentItemsDTO mapToDto(DocumentItems documentItems) {
        DocumentItemsDTO documentItemsDTO = new DocumentItemsDTO();
        documentItemsDTO.setId(documentItems.getId());
        documentItemsDTO.setRoomId(documentItems.getRoom().getId());
        documentItemsDTO.setQuantity(documentItems.getQuantity());
        documentItemsDTO.setAmount(documentItems.getAmount());

        return documentItemsDTO;
    }

    public List<DocumentItemsDTO> mapToDto(Collection<DocumentItems> documentItems) {
        return documentItems.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public DocumentItems mapFromDto(DocumentItemsDTO documentItemsDTO) {
        DocumentItems documentItems = new DocumentItems();

        Room room = new Room();
        room.setId(documentItemsDTO.getRoomId());

        documentItems.setRoom(room);
        documentItems.setQuantity(documentItemsDTO.getQuantity());
        documentItems.setAmount(documentItemsDTO.getAmount());

        return documentItems;
    }

    public DocumentItems mapFromDto(DocumentItems documentItems, DocumentItemsDTO documentItemsDTO) {

        Room room = new Room();
        room.setId(documentItemsDTO.getRoomId());

        documentItems.setRoom(room);
        documentItems.setQuantity(documentItemsDTO.getQuantity());
        documentItems.setAmount(documentItemsDTO.getAmount());

        return documentItems;
    }
}

