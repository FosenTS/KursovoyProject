package ru.raffalda.kursovoy_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raffalda.kursovoy_project.service.ChairService;
import ru.raffalda.kursovoy_project.service.FacultyService;
import ru.raffalda.kursovoy_project.service.PostService;
import ru.raffalda.kursovoy_project.service.TeacherService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final InMemoryUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final List<UserDetails> userList;
    private final TeacherService teacherService;
    private final FacultyService facultyService;
    private final ChairService chairService;
    private final PostService postService;

    @Autowired
    public AdminController(InMemoryUserDetailsManager userDetailsManager, 
                         PasswordEncoder passwordEncoder,
                         TeacherService teacherService,
                         FacultyService facultyService,
                         ChairService chairService,
                         PostService postService) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.teacherService = teacherService;
        this.facultyService = facultyService;
        this.chairService = chairService;
        this.postService = postService;
        this.userList = new CopyOnWriteArrayList<>();
        
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("user123"))
            .roles("USER")
            .build();
            
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin123"))
            .roles("ADMIN", "USER")
            .build();
            
        userList.add(user);
        userList.add(admin);
    }

    @GetMapping("/users")
    public String userManagement(Model model) {
        model.addAttribute("users", userList);
        return "admin/users";
    }

    @GetMapping("/users/new")
    public String newUserForm() {
        return "admin/user-form";
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam String username, 
                           @RequestParam String password,
                           @RequestParam String role) {
        UserDetails user = User.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .roles(role)
            .build();
        
        userDetailsManager.createUser(user);
        userList.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userDetailsManager.deleteUser(username);
        userList.removeIf(user -> user.getUsername().equals(username));
        return "redirect:/admin/users";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("totalTeachers", teacherService.countTeachers());
        statistics.put("totalFaculties", facultyService.countFaculties());
        statistics.put("totalChairs", chairService.countChairs());
        statistics.put("totalPosts", postService.countPosts());
        model.addAttribute("statistics", statistics);
        return "admin/dashboard";
    }

    @GetMapping({"/settings", "/system-settings"})
    public String systemSettings(Model model) {
        Map<String, Object> settings = new HashMap<>();
        settings.put("databaseUrl", "jdbc:h2:mem:testdb");
        settings.put("emailEnabled", true);
        settings.put("maxUploadSize", "10MB");
        settings.put("debugMode", false);
        model.addAttribute("settings", settings);
        return "admin/settings";
    }
} 