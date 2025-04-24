package ru.raffalda.kursovoy_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raffalda.kursovoy_project.dto.TeacherDTO;
import ru.raffalda.kursovoy_project.model.Chair;
import ru.raffalda.kursovoy_project.model.Post;
import ru.raffalda.kursovoy_project.model.Teacher;
import ru.raffalda.kursovoy_project.repository.ChairRepository;
import ru.raffalda.kursovoy_project.repository.PostRepository;
import ru.raffalda.kursovoy_project.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ChairRepository chairRepository;
    private final PostRepository postRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, 
                         ChairRepository chairRepository, 
                         PostRepository postRepository) {
        this.teacherRepository = teacherRepository;
        this.chairRepository = chairRepository;
        this.postRepository = postRepository;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }

    public TeacherDTO getTeacher(Long id) {
        return convertToDTO(teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found")));
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        updateTeacherFromDTO(teacher, teacherDTO);
        return convertToDTO(teacherRepository.save(teacher));
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        updateTeacherFromDTO(teacher, teacherDTO);
        return convertToDTO(teacherRepository.save(teacher));
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public long countTeachers() {
        return teacherRepository.count();
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setSecondName(teacher.getSecondName());
        dto.setPhone(teacher.getPhone());
        dto.setEmail(teacher.getEmail());
        
        if (teacher.getChair() != null) {
            dto.setChairId(teacher.getChair().getId());
            dto.setChairName(teacher.getChair().getNameChair());
        }
        
        if (teacher.getPost() != null) {
            dto.setPostId(teacher.getPost().getId());
            dto.setPostName(teacher.getPost().getNamePost());
        }
        
        return dto;
    }

    private void updateTeacherFromDTO(Teacher teacher, TeacherDTO teacherDTO) {
        Chair chair = chairRepository.findById(teacherDTO.getChairId())
                .orElseThrow(() -> new RuntimeException("Chair not found"));
        Post post = postRepository.findById(teacherDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setSecondName(teacherDTO.getSecondName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setPhone(teacherDTO.getPhone());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setChair(chair);
        teacher.setPost(post);
    }
} 