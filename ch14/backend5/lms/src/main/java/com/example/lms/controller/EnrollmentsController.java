package com.example.lms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dto.EnrollmentsDTO;
import com.example.lms.entity.Enrollments;
import com.example.lms.service.EnrollmentsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentsController {
	private static final Logger log = LoggerFactory.getLogger(EnrollmentsController.class);
	
    private final EnrollmentsService enrollmentsService;

    // 전체 수강 목록 조회
    @GetMapping
    public ResponseEntity<List<EnrollmentsDTO>> getAllEnrollments() {
        List<EnrollmentsDTO> enrollments = enrollmentsService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    // 수강 상세 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Enrollments> getEnrollmentById(@PathVariable("id") Long id) {
        Optional<Enrollments> enrollment = enrollmentsService.getEnrollmentById(id);
        return enrollment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 수강 추가
    @PostMapping
    public ResponseEntity<Enrollments> createEnrollment(@RequestBody Enrollments enrollment) {
        Enrollments savedEnrollment = enrollmentsService.createEnrollment(enrollment);
        return ResponseEntity.ok(savedEnrollment);
    }

    // 수강 수정
    @PutMapping("/{id}")
    public ResponseEntity<Enrollments> updateEnrollment(@PathVariable("id") Long id, @RequestBody Enrollments updatedEnrollment) {
        Optional<Enrollments> enrollment = enrollmentsService.updateEnrollment(id, updatedEnrollment);
        return enrollment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 수강 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable("id") Long id) {
        enrollmentsService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    // course_id로 수강 목록 조회
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollments>> getEnrollmentsByCourseId(@PathVariable("courseId") Long courseId) {
        List<Enrollments> enrollments = enrollmentsService.getEnrollmentsByCourseId(courseId);
        return ResponseEntity.ok(enrollments);
    }

 // student_id별 course_id별로 수강 내역 조회
    @GetMapping("/student/{studentId}/{courseId}")
    public ResponseEntity<EnrollmentsDTO> getEnrollmentsByStudentId(
            @PathVariable("studentId") Long studentId, 
            @PathVariable("courseId") Long courseId) {
        
        Enrollments enrollment = enrollmentsService.getEnrollmentsByStudentId(studentId, courseId);
        // 엔티티를 DTO로 변환
        EnrollmentsDTO enrollmentDTO;
        if (enrollment != null) {
            // 엔티티를 DTO로 변환
            enrollmentDTO = EnrollmentsDTO.builder()
                    .id(enrollment.getId())
                    .courseId(enrollment.getCourse().getId())
                    .courseTitle(enrollment.getCourse().getTitle())
                    .studentId(enrollment.getStudent().getId())
                    .status(enrollment.getStatus().name())
                    .build();
        } else {
            // 기본값을 가진 DTO 반환
            enrollmentDTO = EnrollmentsDTO.builder()
                    .id(0L) // 기본값 설정
                    .courseId(courseId)
                    .courseTitle("N/A") // 기본값 또는 빈 문자열 설정
                    .studentId(studentId)
                    .status("NOT_FOUND") // 기본 상태 설정
                    .build();
        }
        
        return ResponseEntity.ok(enrollmentDTO);
    }
    
    // student_id별 수강(course) 목록 조회
    @GetMapping("/student/course/{studentId}")
    public ResponseEntity<List<EnrollmentsDTO>> getEnrollmentsByCourseIdAndStudentId(@PathVariable("studentId") Long studentId) {
        List<EnrollmentsDTO> enrollments = enrollmentsService.getEnrollmentsByCourseIdAndStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }
}