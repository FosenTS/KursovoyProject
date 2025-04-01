package ru.raffalda.kursovoy_project.dto;

public class FacultyDTO {
    private Long id;
    private String nameFaculty;
    private String shortNameFaculty;

    // Getters and Setters
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