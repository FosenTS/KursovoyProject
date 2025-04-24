package ru.raffalda.kursovoy_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.raffalda.kursovoy_project.dto.ChairDTO;
import ru.raffalda.kursovoy_project.model.Chair;
import ru.raffalda.kursovoy_project.model.Faculty;
import ru.raffalda.kursovoy_project.repository.ChairRepository;
import ru.raffalda.kursovoy_project.repository.FacultyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChairServiceTest {

    @Mock
    private ChairRepository chairRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private ChairService chairService;

    private Chair chair;
    private ChairDTO chairDTO;
    private Faculty faculty;

    @BeforeEach
    void setUp() {
        faculty = new Faculty();
        faculty.setId(1L);
        faculty.setNameFaculty("Test Faculty");

        chair = new Chair();
        chair.setId(1L);
        chair.setNameChair("Test Chair");
        chair.setShortNameChair("TC");
        chair.setFaculty(faculty);

        chairDTO = new ChairDTO();
        chairDTO.setId(1L);
        chairDTO.setNameChair("Test Chair");
        chairDTO.setShortNameChair("TC");
        chairDTO.setFacultyId(1L);
        chairDTO.setFacultyName("Test Faculty");
    }

    @Test
    void getAllChairs_ShouldReturnListOfChairDTOs() {
        when(chairRepository.findAll()).thenReturn(Arrays.asList(chair));

        List<ChairDTO> result = chairService.getAllChairs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chairDTO.getNameChair(), result.get(0).getNameChair());
        assertEquals(chairDTO.getShortNameChair(), result.get(0).getShortNameChair());
        verify(chairRepository).findAll();
    }

    @Test
    void getChair_WithValidId_ShouldReturnChairDTO() {
        when(chairRepository.findById(1L)).thenReturn(Optional.of(chair));

        ChairDTO result = chairService.getChair(1L);

        assertNotNull(result);
        assertEquals(chairDTO.getNameChair(), result.getNameChair());
        assertEquals(chairDTO.getShortNameChair(), result.getShortNameChair());
        verify(chairRepository).findById(1L);
    }

    @Test
    void getChair_WithInvalidId_ShouldThrowException() {
        when(chairRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> chairService.getChair(999L));
        verify(chairRepository).findById(999L);
    }

    @Test
    void createChair_WithValidData_ShouldReturnCreatedChairDTO() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        when(chairRepository.save(any(Chair.class))).thenReturn(chair);

        ChairDTO result = chairService.createChair(chairDTO);

        assertNotNull(result);
        assertEquals(chairDTO.getNameChair(), result.getNameChair());
        assertEquals(chairDTO.getShortNameChair(), result.getShortNameChair());
        verify(facultyRepository).findById(1L);
        verify(chairRepository).save(any(Chair.class));
    }

    @Test
    void updateChair_WithValidData_ShouldReturnUpdatedChairDTO() {
        when(chairRepository.findById(1L)).thenReturn(Optional.of(chair));
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        when(chairRepository.save(any(Chair.class))).thenReturn(chair);

        ChairDTO result = chairService.updateChair(1L, chairDTO);

        assertNotNull(result);
        assertEquals(chairDTO.getNameChair(), result.getNameChair());
        assertEquals(chairDTO.getShortNameChair(), result.getShortNameChair());
        verify(chairRepository).findById(1L);
        verify(facultyRepository).findById(1L);
        verify(chairRepository).save(any(Chair.class));
    }

    @Test
    void deleteChair_ShouldCallRepositoryDeleteById() {
        chairService.deleteChair(1L);

        verify(chairRepository).deleteById(1L);
    }
} 