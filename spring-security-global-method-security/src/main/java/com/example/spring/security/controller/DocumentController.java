package com.example.spring.security.controller;

import com.example.spring.security.model.Document;
import com.example.spring.security.repository.DocumentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    private final DocumentRepository documentRepository;

    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("document/{documentId}")
    public Document getDocument(@PathVariable String documentId) {
        return documentRepository.findById(documentId);
    }

}
