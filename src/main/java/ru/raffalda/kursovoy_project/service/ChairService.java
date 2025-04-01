package ru.raffalda.kursovoy_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raffalda.kursovoy_project.dto.ChairDTO;
import ru.raffalda.kursovoy_project.model.Chair;
import ru.raffalda.kursovoy_project.model.Faculty;
import ru.raffalda.kursovoy_project.repository.ChairRepository;
import ru.raffalda.kursovoy_project.repository.FacultyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChairService {
    private final ChairRepository chairRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public ChairService(ChairRepository chairRepository, FacultyRepository facultyRepository) {
        this.chairRepository = chairRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<ChairDTO> getAllChairs() {
        return chairRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ChairDTO getChair(Long id) {
        return convertToDTO(chairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chair not found")));
    }

    public ChairDTO createChair(ChairDTO chairDTO) {
        Chair chair = new Chair();
        updateChairFromDTO(chair, chairDTO);
        return convertToDTO(chairRepository.save(chair));
    }

    public ChairDTO updateChair(Long id, ChairDTO chairDTO) {
        Chair chair = chairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chair not found"));
        updateChairFromDTO(chair, chairDTO);
        return convertToDTO(chairRepository.save(chair));
    }

    public void deleteChair(Long id) {
        chairRepository.deleteById(id);
    }

    private ChairDTO convertToDTO(Chair chair) {
        ChairDTO dto = new ChairDTO();
        dto.setId(chair.getId());
        dto.setNameChair(chair.getNameChair());
        dto.setShortNameChair(chair.getShortNameChair());
        dto.setFacultyId(chair.getFaculty().getId());
        return dto;
    }

    private void updateChairFromDTO(Chair chair, ChairDTO chairDTO) {
        Faculty faculty = facultyRepository.findById(chairDTO.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        chair.setNameChair(chairDTO.getNameChair());
        chair.setShortNameChair(chairDTO.getShortNameChair());
        chair.setFaculty(faculty);
    }
} 