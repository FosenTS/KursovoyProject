package ru.raffalda.kursovoy_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.raffalda.kursovoy_project.repository.ChairRepository;
import ru.raffalda.kursovoy_project.repository.FacultyRepository;
import ru.raffalda.kursovoy_project.repository.PostRepository;
import ru.raffalda.kursovoy_project.repository.TeacherRepository;

@Controller
public class MainController {

    @Autowired
    private FacultyRepository facultyRepository;
    
    @Autowired
    private ChairRepository chairRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("facultyCount", facultyRepository.count());
        model.addAttribute("chairCount", chairRepository.count());
        model.addAttribute("postCount", postRepository.count());
        model.addAttribute("teacherCount", teacherRepository.count());
        return "index";
    }
} 