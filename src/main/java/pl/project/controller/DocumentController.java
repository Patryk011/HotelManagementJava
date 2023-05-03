package pl.project.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.services.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {



    private final DocumentService documentService;


    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
}
