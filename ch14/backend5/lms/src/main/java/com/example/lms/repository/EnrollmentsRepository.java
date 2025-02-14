package com.example.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.Enrollments;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {
    // course_id로 수강 신청 목록 조회
    List<Enrollments> findByCourseId(Long courseId);

    // student_id로 수강 신청 목록 조회
    @Query("SELECT e FROM Enrollments e WHERE e.student.id = ?1")
    List<Enrollments> findByStudentId(Long studentId);

    @Query("SELECT e FROM Enrollments e WHERE e.student.id = ?1 AND e.course.id = ?2")
    Optional<Enrollments> findByStudentIdAndCourseId(Long studentId, Long courseId);
}