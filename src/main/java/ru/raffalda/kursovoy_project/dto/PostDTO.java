package ru.raffalda.kursovoy_project.dto;

public class PostDTO {
    private Long id;
    private String namePost;

    public Long getId() {
        return id;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }
} 