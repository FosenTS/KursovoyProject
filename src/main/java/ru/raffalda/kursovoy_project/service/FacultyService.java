package ru.raffalda.kursovoy_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raffalda.kursovoy_project.dto.FacultyDTO;
import ru.raffalda.kursovoy_project.model.Faculty;
import ru.raffalda.kursovoy_project.repository.FacultyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<FacultyDTO> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FacultyDTO getFaculty(Long id) {
        return convertToDTO(facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found")));
    }

    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setNameFaculty(facultyDTO.getNameFaculty());
        faculty.setShortNameFaculty(facultyDTO.getShortNameFaculty());
        return convertToDTO(facultyRepository.save(faculty));
    }

    public FacultyDTO updateFaculty(Long id, FacultyDTO facultyDTO) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        faculty.setNameFaculty(facultyDTO.getNameFaculty());
        faculty.setShortNameFaculty(facultyDTO.getShortNameFaculty());
        return convertToDTO(facultyRepository.save(faculty));
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    private FacultyDTO convertToDTO(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setNameFaculty(faculty.getNameFaculty());
        dto.setShortNameFaculty(faculty.getShortNameFaculty());
        return dto;
    }
} 