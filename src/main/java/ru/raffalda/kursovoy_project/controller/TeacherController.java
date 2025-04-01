package ru.raffalda.kursovoy_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raffalda.kursovoy_project.dto.TeacherDTO;
import ru.raffalda.kursovoy_project.service.ChairService;
import ru.raffalda.kursovoy_project.service.PostService;
import ru.raffalda.kursovoy_project.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final ChairService chairService;
    private final PostService postService;

    @Autowired
    public TeacherController(TeacherService teacherService, ChairService chairService, PostService postService) {
        this.teacherService = teacherService;
        this.chairService = chairService;
        this.postService = postService;
    }

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teachers";
    }

    @GetMapping("/new")
    public String newTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("chairs", chairService.getAllChairs());
        model.addAttribute("posts", postService.getAllPosts());
        return "teacher-form";
    }

    @PostMapping
    public String createTeacher(@ModelAttribute TeacherDTO teacherDTO) {
        teacherService.createTeacher(teacherDTO);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editTeacherForm(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacher(id));
        model.addAttribute("chairs", chairService.getAllChairs());
        model.addAttribute("posts", postService.getAllPosts());
        return "teacher-form";
    }

    @PostMapping("/edit/{id}")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute TeacherDTO teacherDTO) {
        teacherService.updateTeacher(id, teacherDTO);
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
} 