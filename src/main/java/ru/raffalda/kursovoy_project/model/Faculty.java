package ru.raffalda.kursovoy_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name_faculty", nullable = false)
    private String nameFaculty;
    
    @Column(name = "short_name_faculty", nullable = false)
    private String shortNameFaculty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    public String getShortNameFaculty() {
        return shortNameFaculty;
    }

    public void setShortNameFaculty(String shortNameFaculty) {
        this.shortNameFaculty = shortNameFaculty;
    }
} 