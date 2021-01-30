package com.example.spring.security.model;

public class Document {

    private String id;

    private String title;

    private String ownerUsername;

    private String reviewerUsername;

    public Document(String id, String title, String ownerUsername, String reviewerUsername) {
        this.id = id;
        this.title = title;
        this.ownerUsername = ownerUsername;
        this.reviewerUsername = reviewerUsername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

}
