package ru.raffalda.kursovoy_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.raffalda.kursovoy_project.dto.TeacherDTO;
import ru.raffalda.kursovoy_project.model.Chair;
import ru.raffalda.kursovoy_project.model.Post;
import ru.raffalda.kursovoy_project.model.Teacher;
import ru.raffalda.kursovoy_project.repository.ChairRepository;
import ru.raffalda.kursovoy_project.repository.PostRepository;
import ru.raffalda.kursovoy_project.repository.TeacherRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private ChairRepository chairRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private TeacherService teacherService;

    private Teacher teacher;
    private TeacherDTO teacherDTO;
    private Chair chair;
    private Post post;

    @BeforeEach
    void setUp() {
        chair = new Chair();
        chair.setId(1L);
        chair.setNameChair("Test Chair");

        post = new Post();
        post.setId(1L);
        post.setNamePost("Test Post");

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setSecondName("Middle");
        teacher.setPhone("+1234567890");
        teacher.setChair(chair);
        teacher.setPost(post);

        teacherDTO = new TeacherDTO();
        teacherDTO.setId(1L);
        teacherDTO.setFirstName("John");
        teacherDTO.setLastName("Doe");
        teacherDTO.setSecondName("Middle");
        teacherDTO.setPhone("+1234567890");
        teacherDTO.setChairId(1L);
        teacherDTO.setPostId(1L);
        teacherDTO.setChairName("Test Chair");
        teacherDTO.setPostName("Test Post");
    }

    @Test
    void getAllTeachers_ShouldReturnListOfTeacherDTOs() {
        when(teacherRepository.findAll()).thenReturn(Arrays.asList(teacher));

        List<TeacherDTO> result = teacherService.getAllTeachers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(teacherDTO.getFirstName(), result.get(0).getFirstName());
        assertEquals(teacherDTO.getLastName(), result.get(0).getLastName());
        verify(teacherRepository).findAll();
    }

    @Test
    void getTeacher_WithValidId_ShouldReturnTeacherDTO() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        TeacherDTO result = teacherService.getTeacher(1L);

        assertNotNull(result);
        assertEquals(teacherDTO.getFirstName(), result.getFirstName());
        assertEquals(teacherDTO.getLastName(), result.getLastName());
        verify(teacherRepository).findById(1L);
    }

    @Test
    void getTeacher_WithInvalidId_ShouldThrowException() {
        when(teacherRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> teacherService.getTeacher(999L));
        verify(teacherRepository).findById(999L);
    }

    @Test
    void createTeacher_WithValidData_ShouldReturnCreatedTeacherDTO() {
        when(chairRepository.findById(1L)).thenReturn(Optional.of(chair));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        TeacherDTO result = teacherService.createTeacher(teacherDTO);

        assertNotNull(result);
        assertEquals(teacherDTO.getFirstName(), result.getFirstName());
        assertEquals(teacherDTO.getLastName(), result.getLastName());
        verify(chairRepository).findById(1L);
        verify(postRepository).findById(1L);
        verify(teacherRepository).save(any(Teacher.class));
    }

    @Test
    void updateTeacher_WithValidData_ShouldReturnUpdatedTeacherDTO() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(chairRepository.findById(1L)).thenReturn(Optional.of(chair));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        TeacherDTO result = teacherService.updateTeacher(1L, teacherDTO);

        assertNotNull(result);
        assertEquals(teacherDTO.getFirstName(), result.getFirstName());
        assertEquals(teacherDTO.getLastName(), result.getLastName());
        verify(teacherRepository).findById(1L);
        verify(chairRepository).findById(1L);
        verify(postRepository).findById(1L);
        verify(teacherRepository).save(any(Teacher.class));
    }

    @Test
    void deleteTeacher_ShouldCallRepositoryDeleteById() {
        teacherService.deleteTeacher(1L);

        verify(teacherRepository).deleteById(1L);
    }
} 