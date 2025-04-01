package ru.raffalda.kursovoy_project.dto;

public class PostDTO {
    private Long id;
    private String namePost;

    // Getters
    public Long getId() {
        return id;
    }

    public String getNamePost() {
        return namePost;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }
} 