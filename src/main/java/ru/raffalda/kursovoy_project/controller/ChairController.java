package ru.raffalda.kursovoy_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raffalda.kursovoy_project.dto.ChairDTO;
import ru.raffalda.kursovoy_project.service.ChairService;
import ru.raffalda.kursovoy_project.service.FacultyService;

import java.util.List;

@Controller
@RequestMapping("/chairs")
public class ChairController {
    private final ChairService chairService;
    private final FacultyService facultyService;

    @Autowired
    public ChairController(ChairService chairService, FacultyService facultyService) {
        this.chairService = chairService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public String listChairs(Model model) {
        List<ChairDTO> chairs = chairService.getAllChairs();
        model.addAttribute("chairs", chairs);
        return "chairs";
    }

    @GetMapping("/new")
    public String newChairForm(Model model) {
        model.addAttribute("chair", new ChairDTO());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "chair-form";
    }

    @PostMapping
    public String createChair(@ModelAttribute ChairDTO chairDTO) {
        chairService.createChair(chairDTO);
        return "redirect:/chairs";
    }

    @GetMapping("/edit/{id}")
    public String editChairForm(@PathVariable Long id, Model model) {
        model.addAttribute("chair", chairService.getChair(id));
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "chair-form";
    }

    @PostMapping("/edit/{id}")
    public String updateChair(@PathVariable Long id, @ModelAttribute ChairDTO chairDTO) {
        chairService.updateChair(id, chairDTO);
        return "redirect:/chairs";
    }

    @GetMapping("/delete/{id}")
    public String deleteChair(@PathVariable Long id) {
        chairService.deleteChair(id);
        return "redirect:/chairs";
    }
} 