package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.dto.DocumentDTO;
import pl.project.dto.DocumentItemsDTO;
import pl.project.entity.Document;
import pl.project.entity.DocumentItems;
import pl.project.entity.Room;
import pl.project.mapper.DocumentItemsMapper;
import pl.project.mapper.DocumentMapper;
import pl.project.mapper.RoomMapper;
import pl.project.repository.DocumentItemsRepository;
import pl.project.repository.DocumentRepository;
import pl.project.repository.RoomRepository;

import java.util.NoSuchElementException;

@Service
public class DocumentServiceImpl implements DocumentService {



    private final DocumentRepository documentRepository;

    private final DocumentItemsRepository documentItemsRepository;

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;
    private final DocumentMapper documentMapper;

    private final DocumentItemsMapper documentItemsMapper;


    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentItemsRepository documentItemsRepository, RoomRepository roomRepository, RoomMapper roomMapper, DocumentMapper mapper, DocumentItemsMapper documentItemsMapper) {
        this.documentRepository = documentRepository;
        this.documentItemsRepository = documentItemsRepository;
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
        this.documentMapper = mapper;
        this.documentItemsMapper = documentItemsMapper;
    }

//    @Transactional
//    public void updateDocumentState(Long documentId, boolean hasOffer, boolean hasReservation) {
//        Document document = documentRepository.findById(documentId).orElseThrow(() -> new NoSuchElementException());
//
//            int state = 0;
//            if (hasOffer) {
//                state = 1;
//            }
//            if (hasReservation) {
//                state = 2;
//            }
//            document.setState(state);
//            documentRepository.save(document);
//        }

    @Override
    public DocumentDTO saveDocument(DocumentDTO documentDTO) {
        Document document = documentMapper.mapFromDto(documentDTO);
        document = documentRepository.save(document);

        return documentMapper.mapToDto(document);
    }

    @Override
    public DocumentItemsDTO saveDocumentItems(DocumentItemsDTO documentItemsDTO) {
        DocumentItems documentItems = documentItemsMapper.mapFromDto(documentItemsDTO);
        documentItems = documentItemsRepository.save(documentItems);
        return documentItemsMapper.mapToDto(documentItems);
    }

    @Override
    public DocumentDTO getDocumentById(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return documentMapper.mapToDto(document);
    }

    @Override
    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public DocumentItemsDTO getDocumentItemsById(Long id) {
        DocumentItems documentItems = documentItemsRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return documentItemsMapper.mapToDto(documentItems);
    }
    @Override
    public void deleteDocumentItemsById(Long id) {
        documentItemsRepository.deleteById(id);
    }

    @Transactional
    public void updateDocumentItems(DocumentItemsDTO documentItemsDTO) {
        Room room = roomRepository.findById(documentItemsDTO.getRoomId()).orElseThrow(() -> new NoSuchElementException());
        roomMapper.mapToDto(room);
        DocumentItems editedItems = documentItemsRepository.findById(documentItemsDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        editedItems.setRoom(room);
        editedItems.setQuantity(documentItemsDTO.getQuantity());
        editedItems.setAmount(documentItemsDTO.getAmount());


        documentItemsRepository.save(editedItems);
    }
}







