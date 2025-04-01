package ru.raffalda.kursovoy_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raffalda.kursovoy_project.model.Chair;

public interface ChairRepository extends JpaRepository<Chair, Long> {
} 