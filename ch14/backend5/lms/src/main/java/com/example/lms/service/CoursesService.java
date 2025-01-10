package com.example.lms.service;

import com.example.lms.entity.Courses;
import com.example.lms.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CoursesRepository coursesRepository;

    // 목록 조회
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    // 상세 정보 조회
    public Optional<Courses> getCourseById(Long id) {
        return coursesRepository.findById(id);
    }

    // 강의 추가
    public Courses createCourse(Courses course) {
        return coursesRepository.save(course);
    }

    // 강의 수정
    @Transactional
    public Optional<Courses> updateCourse(Long id, Courses updatedCourse) {
        return coursesRepository.findById(id).map(course -> {
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setLector(updatedCourse.getLector());
            course.setStartDate(updatedCourse.getStartDate());
            course.setEndDate(updatedCourse.getEndDate());
            course.setInstructor(updatedCourse.getInstructor());
            return course;
        });
    }

    // 강의 삭제
    public void deleteCourse(Long id) {
        coursesRepository.deleteById(id);
    }

	public Optional<Courses> getLastCourse() {
		return coursesRepository.findTopByOrderByCreatedAtDesc();
	}
}