package com.example.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.lms.dto.EnrollmentsDTO;
import com.example.lms.entity.Enrollments;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.repository.CoursesRepository;
import com.example.lms.repository.EnrollmentsRepository;
import com.example.lms.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentsService {
	
    private final EnrollmentsRepository enrollmentsRepository;
    private final CoursesRepository coursesRepository;
    private final UsersRepository usersRepository;
    
    // 목록 조회
    public List<EnrollmentsDTO> getAllEnrollments() {
        List<Enrollments> enrollments = enrollmentsRepository.findAll();
        return enrollments.stream()
                .map(enrollment -> EnrollmentsDTO.builder()
                        .id(enrollment.getId())
                        .courseId(enrollment.getCourse().getId())
                        .courseTitle(coursesRepository.getTitleById(enrollment.getCourse().getId())) // Courses의 title을 DTO에 대입
                        .studentId(enrollment.getStudent().getId())
                        .studentName(enrollment.getStudent().getName())
                        .status(enrollment.getStatus().name()) // Enum 값을 문자열로 변환
                        .build())
                .collect(Collectors.toList());
    }

    // 상세 정보 조회
    public Optional<Enrollments> getEnrollmentById(Long id) {
        return enrollmentsRepository.findById(id);
    }

    // 수강 추가
    public Enrollments createEnrollment(Enrollments enrollment) {
        return enrollmentsRepository.save(enrollment);
    }

    // 수강 수정
    public Optional<Enrollments> updateEnrollment(Long id, Enrollments updatedEnrollment) {
        return enrollmentsRepository.findById(id).map(enrollment -> {
            enrollment.setCourse(updatedEnrollment.getCourse());
            enrollment.setStudent(updatedEnrollment.getStudent());
            enrollment.setStatus(updatedEnrollment.getStatus());
            return enrollment;
        });
    }

    // 수강 삭제
    public void deleteEnrollment(Long id) {
        enrollmentsRepository.deleteById(id);
    }

    // course_id로 수강 목록 조회
    public List<Enrollments> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentsRepository.findByCourseId(courseId);
    }

    // student_id/course_id 벌로 수강 목록 조회
    public Enrollments getEnrollmentsByStudentId(Long studentId, Long courseId) {
        return enrollmentsRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for studentId: " + studentId + " and courseId: " + courseId));
    }
    
    // studentId로 수강 목록 조회
    public List<EnrollmentsDTO> getEnrollmentsByCourseIdAndStudentId(Long studentId) {
        List<Enrollments> enrollments = enrollmentsRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(enrollment -> EnrollmentsDTO.builder()
                        .id(enrollment.getId())
                        .courseId(enrollment.getCourse().getId())
                        .courseTitle(coursesRepository.getTitleById(enrollment.getCourse().getId())) // Courses의 title을 DTO에 대입
                        .studentId(enrollment.getStudent().getId())
                        .studentName(enrollment.getStudent().getName())
                        .status(enrollment.getStatus().name()) // Enum 값을 문자열로 변환
                        .build())
                .collect(Collectors.toList());
    }
}