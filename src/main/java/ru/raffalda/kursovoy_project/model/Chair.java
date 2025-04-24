package ru.raffalda.kursovoy_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "chair")
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_faculty", nullable = false)
    private Faculty faculty;
    
    @Column(name = "name_chair", nullable = false)
    private String nameChair;
    
    @Column(name = "short_name_chair", nullable = false)
    private String shortNameChair;

    public Long getId() {
        return id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getNameChair() {
        return nameChair;
    }

    public String getShortNameChair() {
        return shortNameChair;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setNameChair(String nameChair) {
        this.nameChair = nameChair;
    }

    public void setShortNameChair(String shortNameChair) {
        this.shortNameChair = shortNameChair;
    }
} 