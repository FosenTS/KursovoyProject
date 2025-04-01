package ru.raffalda.kursovoy_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name_post", nullable = false)
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