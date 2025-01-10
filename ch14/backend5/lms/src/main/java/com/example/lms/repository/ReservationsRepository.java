package com.example.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lms.dto.ReservationsDTO;
import com.example.lms.entity.Reservations;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    // course_id로 예약 목록 조회
    List<Reservations> findByCourseId(Long courseId);

    // student_id로 예약 목록 조회
    List<Reservations> findByStudentId(Long studentId);
    
    @Query("SELECT r FROM Reservations r WHERE r.student.id = ?1")
    List<Reservations> findByStudentAndCourseId(Long studentId);
}