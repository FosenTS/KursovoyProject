package ru.raffalda.kursovoy_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.raffalda.kursovoy_project.dto.FacultyDTO;
import ru.raffalda.kursovoy_project.model.Faculty;
import ru.raffalda.kursovoy_project.repository.FacultyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;

    private Faculty faculty;
    private FacultyDTO facultyDTO;

    @BeforeEach
    void setUp() {
        faculty = new Faculty();
        faculty.setId(1L);
        faculty.setNameFaculty("Test Faculty");
        faculty.setShortNameFaculty("TF");

        facultyDTO = new FacultyDTO();
        facultyDTO.setId(1L);
        facultyDTO.setNameFaculty("Test Faculty");
        facultyDTO.setShortNameFaculty("TF");
    }

    @Test
    void getAllFaculties_ShouldReturnListOfFacultyDTOs() {
        when(facultyRepository.findAll()).thenReturn(Arrays.asList(faculty));

        List<FacultyDTO> result = facultyService.getAllFaculties();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(facultyDTO.getNameFaculty(), result.get(0).getNameFaculty());
        assertEquals(facultyDTO.getShortNameFaculty(), result.get(0).getShortNameFaculty());
        verify(facultyRepository).findAll();
    }

    @Test
    void getFaculty_WithValidId_ShouldReturnFacultyDTO() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        FacultyDTO result = facultyService.getFaculty(1L);

        assertNotNull(result);
        assertEquals(facultyDTO.getNameFaculty(), result.getNameFaculty());
        assertEquals(facultyDTO.getShortNameFaculty(), result.getShortNameFaculty());
        verify(facultyRepository).findById(1L);
    }

    @Test
    void getFaculty_WithInvalidId_ShouldThrowException() {
        when(facultyRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> facultyService.getFaculty(999L));
        verify(facultyRepository).findById(999L);
    }

    @Test
    void createFaculty_WithValidData_ShouldReturnCreatedFacultyDTO() {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        FacultyDTO result = facultyService.createFaculty(facultyDTO);

        assertNotNull(result);
        assertEquals(facultyDTO.getNameFaculty(), result.getNameFaculty());
        assertEquals(facultyDTO.getShortNameFaculty(), result.getShortNameFaculty());
        verify(facultyRepository).save(any(Faculty.class));
    }

    @Test
    void updateFaculty_WithValidData_ShouldReturnUpdatedFacultyDTO() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        FacultyDTO result = facultyService.updateFaculty(1L, facultyDTO);

        assertNotNull(result);
        assertEquals(facultyDTO.getNameFaculty(), result.getNameFaculty());
        assertEquals(facultyDTO.getShortNameFaculty(), result.getShortNameFaculty());
        verify(facultyRepository).findById(1L);
        verify(facultyRepository).save(any(Faculty.class));
    }

    @Test
    void deleteFaculty_ShouldCallRepositoryDeleteById() {
        facultyService.deleteFaculty(1L);

        verify(facultyRepository).deleteById(1L);
    }
} 