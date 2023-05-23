package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.DocumentItemsDTO;
import pl.project.entity.DocumentItems;
import pl.project.entity.Room;
import pl.project.repository.RoomRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentItemsMapper {


    private final RoomRepository roomRepository;

    public DocumentItemsMapper(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public DocumentItemsDTO mapToDto(DocumentItems documentItems) {
        DocumentItemsDTO documentItemsDTO = new DocumentItemsDTO();
        documentItemsDTO.setId(documentItems.getId());
        documentItemsDTO.setRoomNumber(documentItems.getRoom().getNumber());
        documentItemsDTO.setQuantity(documentItems.getQuantity());
        documentItemsDTO.setAmount(documentItems.getAmount());

        return documentItemsDTO;
    }

    public List<DocumentItemsDTO> mapToDto(Collection<DocumentItems> documentItems) {
        return documentItems.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public DocumentItems mapFromDto(DocumentItemsDTO documentItemsDTO) {
        DocumentItems documentItems = new DocumentItems();

        Room room = roomRepository.findByRoomNumber(documentItemsDTO.getRoomNumber());


        documentItems.setRoom(room);
        documentItems.setQuantity(documentItemsDTO.getQuantity());
        documentItems.setAmount(documentItemsDTO.getAmount());

        return documentItems;
    }

    public DocumentItems mapFromDto(DocumentItems documentItems, DocumentItemsDTO documentItemsDTO) {

        Room room = roomRepository.findByRoomNumber(documentItemsDTO.getRoomNumber());

        documentItems.setRoom(room);
        documentItems.setQuantity(documentItemsDTO.getQuantity());
        documentItems.setAmount(documentItemsDTO.getAmount());

        return documentItems;
    }
}

