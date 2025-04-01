package ru.raffalda.kursovoy_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raffalda.kursovoy_project.dto.FacultyDTO;
import ru.raffalda.kursovoy_project.service.FacultyService;

@Controller
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String listFaculties(Model model) {
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "faculties";
    }

    @GetMapping("/new")
    public String newFacultyForm(Model model) {
        model.addAttribute("faculty", new FacultyDTO());
        return "faculty-form";
    }

    @PostMapping
    public String createFaculty(@ModelAttribute FacultyDTO facultyDTO) {
        facultyService.createFaculty(facultyDTO);
        return "redirect:/faculties";
    }

    @GetMapping("/edit/{id}")
    public String editFacultyForm(@PathVariable Long id, Model model) {
        model.addAttribute("faculty", facultyService.getFaculty(id));
        return "faculty-form";
    }

    @PostMapping("/edit/{id}")
    public String updateFaculty(@PathVariable Long id, @ModelAttribute FacultyDTO facultyDTO) {
        facultyService.updateFaculty(id, facultyDTO);
        return "redirect:/faculties";
    }

    @GetMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculties";
    }
} 