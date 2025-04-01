package ru.raffalda.kursovoy_project.dto;

public class ChairDTO {
    private Long id;
    private Long facultyId;
    private String nameChair;
    private String shortNameChair;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public String getNameChair() {
        return nameChair;
    }

    public String getShortNameChair() {
        return shortNameChair;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public void setNameChair(String nameChair) {
        this.nameChair = nameChair;
    }

    public void setShortNameChair(String shortNameChair) {
        this.shortNameChair = shortNameChair;
    }
} 