package com.example.spring.security.controller;

import com.example.spring.security.model.Document;
import com.example.spring.security.service.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
