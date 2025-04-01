public class Chair {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    
    // getters and setters
} 