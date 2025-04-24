package ru.raffalda.kursovoy_project.dto;

public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String secondName;
    private String phone;
    private String email;
    private String chairName;
    private String postName;
    private Long chairId;
    private Long postId;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getChairName() {
        return chairName;
    }

    public String getPostName() {
        return postName;
    }

    public Long getChairId() {
        return chairId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setChairId(Long chairId) {
        this.chairId = chairId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
} 