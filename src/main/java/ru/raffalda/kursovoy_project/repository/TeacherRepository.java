package ru.raffalda.kursovoy_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raffalda.kursovoy_project.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
} 