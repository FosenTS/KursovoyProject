package ru.raffalda.kursovoy_project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.raffalda.kursovoy_project.dto.FacultyDTO;
import ru.raffalda.kursovoy_project.model.Faculty;
import ru.raffalda.kursovoy_project.repository.FacultyRepository;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;

    @Test
    void whenGetFaculty_thenReturnFacultyDTO() {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setNameFaculty("Test Faculty");
        faculty.setShortNameFaculty("TF");

        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        FacultyDTO result = facultyService.getFaculty(1L);

        assertEquals(faculty.getId(), result.getId());
        assertEquals(faculty.getNameFaculty(), result.getNameFaculty());
        assertEquals(faculty.getShortNameFaculty(), result.getShortNameFaculty());
    }
} 