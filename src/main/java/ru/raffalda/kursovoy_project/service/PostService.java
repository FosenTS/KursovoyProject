package ru.raffalda.kursovoy_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raffalda.kursovoy_project.dto.PostDTO;
import ru.raffalda.kursovoy_project.model.Post;
import ru.raffalda.kursovoy_project.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPost(Long id) {
        return convertToDTO(postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found")));
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setNamePost(postDTO.getNamePost());
        return convertToDTO(postRepository.save(post));
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setNamePost(postDTO.getNamePost());
        return convertToDTO(postRepository.save(post));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public long countPosts() {
        return postRepository.count();
    }

    private PostDTO convertToDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setNamePost(post.getNamePost());
        return dto;
    }
} 