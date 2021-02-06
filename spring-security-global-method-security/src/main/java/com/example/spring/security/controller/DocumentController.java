package com.example.spring.security.controller;

import com.example.spring.security.model.Document;
import com.example.spring.security.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("document/{documentId}")
    public Document getDocument(@PathVariable String documentId) {
        return documentService.getDocument(documentId);
    }

    @GetMapping("document")
    public List<Document> getAllDocument() {
        return documentService.getAllDocument();
    }

    @PostMapping("document")
    public void addDocuments(@RequestBody List<Document> documents) {
        documentService.addDocuments(documents);
    }

}
