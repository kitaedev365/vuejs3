package com.example.lms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Courses;
import com.example.lms.service.CoursesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesController {
	private static final Logger log = LoggerFactory.getLogger(CoursesController.class);
	
    private final CoursesService coursesService;

    // 목록 조회
    @GetMapping
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = coursesService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/last")
    public ResponseEntity<Courses> getLastCourse() {
        Optional<Courses> course = coursesService.getLastCourse();
        return course.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // 상세 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable("id") Long id) {
        Optional<Courses> course = coursesService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 강의 추가
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Courses> createCourse(@RequestBody Courses course) {
    	log.info("Courses 컨트롤러");
    	log.info(course.toString());
        Courses savedCourse = coursesService.createCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    // 강의 수정
    @PutMapping("/{id}")
    public ResponseEntity<Courses> updateCourse(@PathVariable("id") Long id, @RequestBody Courses updatedCourse) {
        Optional<Courses> course = coursesService.updateCourse(id, updatedCourse);
        return course.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 강의 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id) {
        coursesService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}