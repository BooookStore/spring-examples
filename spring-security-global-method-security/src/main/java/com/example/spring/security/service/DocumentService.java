package com.example.spring.security.service;

import com.example.spring.security.model.Document;
import com.example.spring.security.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DocumentService {

    private final Logger logger = LoggerFactory.getLogger(DocumentService.class.getName());

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostAuthorize("hasPermission(returnObject, 'read')")
    public Document getDocument(String documentId) {
        return documentRepository.findById(documentId);
    }

    @PostFilter("filterObject.ownerUsername == authentication.name")
    public List<Document> getAllDocument() {
        return documentRepository.findAll();
    }

    @PreFilter("filterObject.ownerUsername == authentication.name")
    public void addDocuments(Collection<Document> documents) {
        logger.info("add documents {}", documents);
        this.documentRepository.addAll(documents);
    }

}
