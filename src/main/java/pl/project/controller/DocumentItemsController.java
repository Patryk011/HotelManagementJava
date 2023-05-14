package pl.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.project.dto.DocumentItemsDTO;
import pl.project.mapper.DocumentItemsMapper;
import pl.project.mapper.RoomMapper;
import pl.project.services.DocumentService;
import pl.project.services.RoomService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("documentItems/")
public class DocumentItemsController {

    private RoomService roomService;

    private DocumentService documentService;




    public DocumentItemsController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping
    public DocumentItemsDTO saveDocumentItems(@RequestBody DocumentItemsDTO documentItemsDTO) {
        if(checkRoomIsFree(documentItemsDTO.getId())) {

            return saveDocumentItems(documentItemsDTO);
        }
        throw new RuntimeException();
    }


    @GetMapping
    public boolean checkRoomIsFree(Long roomId){
       return roomService.isFree(roomId);
    }
}
