package ru.raffalda.kursovoy_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raffalda.kursovoy_project.dto.PostDTO;
import ru.raffalda.kursovoy_project.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new PostDTO());
        return "post-form";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostDTO postDTO) {
        postService.createPost(postDTO);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post-form";
    }

    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostDTO postDTO) {
        postService.updatePost(id, postDTO);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
} 