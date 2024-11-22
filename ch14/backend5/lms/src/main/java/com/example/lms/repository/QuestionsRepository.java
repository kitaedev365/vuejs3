package com.example.lms.repository;

import com.example.lms.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    // course_id로 문제 목록 조회
    List<Questions> findByCourseId(Long courseId);
}