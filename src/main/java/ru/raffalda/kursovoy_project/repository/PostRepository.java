package ru.raffalda.kursovoy_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raffalda.kursovoy_project.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
} 