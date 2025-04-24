package ru.raffalda.kursovoy_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.raffalda.kursovoy_project.dto.PostDTO;
import ru.raffalda.kursovoy_project.model.Post;
import ru.raffalda.kursovoy_project.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post post;
    private PostDTO postDTO;

    @BeforeEach
    void setUp() {
        post = new Post();
        post.setId(1L);
        post.setNamePost("Test Post");

        postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setNamePost("Test Post");
    }

    @Test
    void getAllPosts_ShouldReturnListOfPostDTOs() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(post));

        List<PostDTO> result = postService.getAllPosts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(postDTO.getNamePost(), result.get(0).getNamePost());
        verify(postRepository).findAll();
    }

    @Test
    void getPost_WithValidId_ShouldReturnPostDTO() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        PostDTO result = postService.getPost(1L);

        assertNotNull(result);
        assertEquals(postDTO.getNamePost(), result.getNamePost());
        verify(postRepository).findById(1L);
    }

    @Test
    void getPost_WithInvalidId_ShouldThrowException() {
        when(postRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> postService.getPost(999L));
        verify(postRepository).findById(999L);
    }

    @Test
    void createPost_WithValidData_ShouldReturnCreatedPostDTO() {
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDTO result = postService.createPost(postDTO);

        assertNotNull(result);
        assertEquals(postDTO.getNamePost(), result.getNamePost());
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void updatePost_WithValidData_ShouldReturnUpdatedPostDTO() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDTO result = postService.updatePost(1L, postDTO);

        assertNotNull(result);
        assertEquals(postDTO.getNamePost(), result.getNamePost());
        verify(postRepository).findById(1L);
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void deletePost_ShouldCallRepositoryDeleteById() {
        postService.deletePost(1L);

        verify(postRepository).deleteById(1L);
    }
} 