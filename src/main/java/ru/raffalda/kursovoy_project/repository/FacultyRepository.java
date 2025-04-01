package ru.raffalda.kursovoy_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raffalda.kursovoy_project.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
} 