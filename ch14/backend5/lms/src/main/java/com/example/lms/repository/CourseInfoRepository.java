package com.example.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.CourseInfo;

@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {
  
    // 특정 Course에 대한 모든 CourseInfo 조회
    List<CourseInfo> findByCourseId(Long courseId);

    // 특정 Course의 모든 CourseInfo 삭제
    void deleteByCourseId(Long courseId);

    @Modifying
    @Query("DELETE FROM CourseInfo ci WHERE ci.course.id IN :courseIds")
    void deleteAllByCourseIdIn(List<Long> courseIds);
}