package ru.raffalda.kursovoy_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raffalda.kursovoy_project.dto.TeacherDTO;
import ru.raffalda.kursovoy_project.service.ChairService;
import ru.raffalda.kursovoy_project.service.PostService;
import ru.raffalda.kursovoy_project.service.TeacherService;

import java.security.Principal;
import java.util.List;

@Controller
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

    @GetMapping("/teacher/dashboard")
    public String dashboard(Model model, Principal principal) {
        if (principal != null) {
            List<TeacherDTO> teachers = teacherService.getAllTeachers();
            if (!teachers.isEmpty()) {
                model.addAttribute("teacher", teachers.get(0));
            }
        }
        return "teacher/dashboard";
    }

    @GetMapping({"/teachers", "/teacher"})
    public String listTeachers(Model model) {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    @GetMapping({"/teachers/new", "/teacher/new"})
    public String newTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("chairs", chairService.getAllChairs());
        model.addAttribute("posts", postService.getAllPosts());
        return "teacher-form";
    }

    @PostMapping({"/teachers", "/teacher"})
    public String createTeacher(@ModelAttribute TeacherDTO teacherDTO) {
        teacherService.createTeacher(teacherDTO);
        return "redirect:/teachers";
    }

    @GetMapping({"/teachers/edit/{id}", "/teacher/edit/{id}"})
    public String editTeacherForm(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacher(id));
        model.addAttribute("chairs", chairService.getAllChairs());
        model.addAttribute("posts", postService.getAllPosts());
        return "teacher-form";
    }

    @PostMapping({"/teachers/edit/{id}", "/teacher/edit/{id}"})
    public String updateTeacher(@PathVariable Long id, @ModelAttribute TeacherDTO teacherDTO) {
        teacherService.updateTeacher(id, teacherDTO);
        return "redirect:/teachers";
    }

    @GetMapping({"/teachers/delete/{id}", "/teacher/delete/{id}"})
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
} 