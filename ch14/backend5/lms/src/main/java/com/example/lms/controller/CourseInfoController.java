package com.example.lms.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.dto.CourseInfoDTO;
import com.example.lms.entity.CourseInfo;
import com.example.lms.entity.Courses;
import com.example.lms.service.CourseInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/course-info")
@RequiredArgsConstructor
public class CourseInfoController {
	private static final Logger log = LoggerFactory.getLogger(CourseInfoController.class);
	
    private final CourseInfoService courseInfoService;

    @GetMapping
    public List<CourseInfoDTO> getAllCourseInfos() {
        return courseInfoService.getAllCourseInfos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/course/{courseId}")
    public List<CourseInfoDTO> getCourseInfosByCourseId(@PathVariable("courseId") Long courseId) {
        return courseInfoService.getCourseInfosByCourseId(courseId);
    }

    @GetMapping("/{id}")
    public CourseInfoDTO getCourseInfoById(@PathVariable("id") Long id) {
        return convertToDTO(courseInfoService.getCourseInfoById(id));
    }

    @PostMapping
    public CourseInfoDTO createCourseInfo(@RequestBody CourseInfoDTO courseInfoDTO) {
        CourseInfo courseInfo = convertToEntity(courseInfoDTO);
        return convertToDTO(courseInfoService.saveCourseInfo(courseInfo));
    }

    @PostMapping("/bulk")
    public List<CourseInfoDTO> createCourseInfos(@RequestBody List<CourseInfoDTO> courseInfoDTOs) {
        List<CourseInfo> courseInfos = courseInfoDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        return courseInfoService.saveCourseInfos(courseInfos).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCourseInfo(
    		@RequestParam("courseId") List<Long> courseId,
            @RequestParam("topic") List<String> topic,
            @RequestParam("details") List<String> details,
            @RequestParam("durationHours") List<Integer> durationHours,
            @RequestParam("mediafiles") List<MultipartFile> mediafiles) {

        if (topic.size() != 5 || details.size() != 5 || durationHours.size() != 5 || mediafiles.size() != 5) {
            return ResponseEntity.badRequest().body("자료가 모두 5건이어야 합니다.");
        }
        courseInfoService.saveCourseInfos(courseId, topic, details, durationHours, mediafiles);
        return ResponseEntity.ok("과정이 성공적으로 등록되었습니다.");
    }

    @PutMapping("/{id}")
    public CourseInfoDTO updateCourseInfo(@PathVariable("id") Long id, @RequestBody CourseInfoDTO courseInfoDTO) {
        CourseInfo courseInfo = convertToEntity(courseInfoDTO);
        return convertToDTO(courseInfoService.updateCourseInfo(id, courseInfo));
    }

    @PutMapping("/bulk")
    public List<CourseInfoDTO> updateCourseInfos(@RequestBody List<CourseInfoDTO> courseInfoDTOs) {
        List<CourseInfo> updatedInfos = courseInfoDTOs.stream()
                .map(dto -> courseInfoService.updateCourseInfo(dto.getId(), convertToEntity(dto)))
                .collect(Collectors.toList());
        return updatedInfos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteCourseInfo(@PathVariable("id") Long id) {
        courseInfoService.deleteCourseInfo(id);
    }

    @DeleteMapping("/course/{courseId}")
    public void deleteCourseInfosByCourseId(@PathVariable("courseId") Long courseId) {
        courseInfoService.deleteCourseInfosByCourseId(courseId);
    }

    private CourseInfoDTO convertToDTO(CourseInfo courseInfo) {
        return new CourseInfoDTO(
                courseInfo.getId(),
                courseInfo.getCourse().getId(),
                courseInfo.getTopic(),
                courseInfo.getDetails(),
                courseInfo.getMediafile(),
                courseInfo.getDurationHours()
        );
    }

    private CourseInfo convertToEntity(CourseInfoDTO courseInfoDTO) {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setId(courseInfoDTO.getId());
        Courses course = new Courses();
        course.setId(courseInfoDTO.getCourseId());
        courseInfo.setCourse(course);
        courseInfo.setTopic(courseInfoDTO.getTopic());
        courseInfo.setDetails(courseInfoDTO.getDetails());
        courseInfo.setMediafile(courseInfoDTO.getMediafile());
        courseInfo.setDurationHours(courseInfoDTO.getDurationHours());
        return courseInfo;
    }
}