package org.example.models;

public class Breed {
    private String id;
    private String name;
    private String description;
    private String userId;

    public Breed(String id, String name, String description, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public Breed() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }
}
