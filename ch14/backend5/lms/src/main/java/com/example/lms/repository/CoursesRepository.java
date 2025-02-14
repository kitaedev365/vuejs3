package com.example.lms.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    @Query("SELECT c.title FROM Courses c WHERE c.id = ?1")
    String getTitleById(Long id);
    
    /*
    @Query("SELECT c FROM Course c ORDER BY c.createdAt DESC")
    Optional<Courses> getLastCourse();
    */
    Optional<Courses> findTopByOrderByCreatedAtDesc();
}