package com.example.spring.security.repository;

import com.example.spring.security.model.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentRepository {

    @SuppressWarnings("SpellCheckingInspection")
    private final List<Document> documents = List.of(
            new Document("document-1", "経営計画書", "Sato", "Suzuki"),
            new Document("document-2", "営業報告書", "Tanaka", "Suzuki"),
            new Document("document-3", "サービス利用契約書", "Kurihasi", "Maede")
    );

    public Document findById(String documentId) {
        return documents.stream().filter(d -> d.getId().equals(documentId)).findFirst().orElseThrow();
    }

}