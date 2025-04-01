package ru.raffalda.kursovoy_project.dto;

public class TeacherDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private Long chairId;
    private Long postId;
    private String phone;
    private String email;

    // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getChairId() {
        return chairId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setChairId(Long chairId) {
        this.chairId = chairId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
} 