package com.example.lms.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.dto.CourseInfoDTO;
import com.example.lms.entity.CourseInfo;
import com.example.lms.entity.Courses;
import com.example.lms.repository.CourseInfoRepository;
import com.example.lms.repository.CoursesRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseInfoService {

    private final CourseInfoRepository courseInfoRepository;
    private final CoursesRepository coursesRepository;
       
    public List<CourseInfo> getAllCourseInfos() {
        return courseInfoRepository.findAll();
    }

    public CourseInfo getCourseInfoById(Long id) {
        return courseInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseInfo not found with id: " + id));
    }

    public CourseInfo saveCourseInfo(CourseInfo courseInfo) {
        return courseInfoRepository.save(courseInfo);
    }

    public List<CourseInfo> saveCourseInfos(List<CourseInfo> courseInfos) {
        return courseInfoRepository.saveAll(courseInfos);
    }

    public CourseInfo updateCourseInfo(Long id, CourseInfo updatedCourseInfo) {
        CourseInfo courseInfo = getCourseInfoById(id);
        courseInfo.setTopic(updatedCourseInfo.getTopic());
        courseInfo.setDetails(updatedCourseInfo.getDetails());
        courseInfo.setDurationHours(updatedCourseInfo.getDurationHours());
        return courseInfoRepository.save(courseInfo);
    }

    public void deleteCourseInfo(Long id) {
        courseInfoRepository.deleteById(id);
    }
    
    @Transactional
    public void deleteCourseInfosByCourseId(Long courseId) {
        courseInfoRepository.deleteByCourseId(courseId);
    }
    
    public void deleteCourseInfosByCourseIds(List<Long> courseIds) {
        courseInfoRepository.deleteAllByCourseIdIn(courseIds);
    }
    
    // 특정 Course의 CourseInfo 목록 조회
    public List<CourseInfoDTO> getCourseInfosByCourseId(Long courseId) {
        List<CourseInfo> courseInfos = courseInfoRepository.findByCourseId(courseId);
        return courseInfos.stream().map(courseInfo -> CourseInfoDTO.builder()
                .id(courseInfo.getId())
                .courseId(courseInfo.getCourse().getId())
                .topic(courseInfo.getTopic())
                .details(courseInfo.getDetails())
                .mediafile(courseInfo.getMediafile())
                .durationHours(courseInfo.getDurationHours())
                .build())
                .collect(Collectors.toList());
    }

    // 특정 Course의 CourseInfo 입력/수정
    @Transactional
    public void saveOrUpdateCourseInfos(Long courseId, List<CourseInfoDTO> courseInfoDTOs) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        // 기존 데이터 삭제
        courseInfoRepository.deleteByCourseId(courseId);

        // 새 데이터 저장
        List<CourseInfo> courseInfos = courseInfoDTOs.stream().map(dto -> {
            CourseInfo courseInfo = new CourseInfo();
            courseInfo.setCourse(course);
            courseInfo.setTopic(dto.getTopic());
            courseInfo.setDetails(dto.getDetails());
            courseInfo.setMediafile(dto.getMediafile());
            courseInfo.setDurationHours(dto.getDurationHours());
            return courseInfo;
        }).collect(Collectors.toList());

        courseInfoRepository.saveAll(courseInfos);
    }

    public void saveCourseInfos(
    	    List<Long> courseIds,
    	    List<String> topics, 
    	    List<String> details, 
    	    List<Integer> durationHours, 
    	    List<MultipartFile> mediafiles) {
    	    
    	    for (int i = 0; i < topics.size(); i++) {
    	        // media file 저장
    	        String mediaFilePath = saveFile(mediafiles.get(i)); 
    	        
    	        // Course 객체를 courseId를 통해 조회
    	        Long courseId = courseIds.get(i);
    	        Courses course = coursesRepository.findById(courseId)
    	                .orElseThrow(() -> new RuntimeException("Course not found with id " + courseId));
    	        
    	        // CourseInfo 객체 생성
    	        CourseInfo courseInfo = CourseInfo.builder()
    	                .course(course)  // Course 객체 설정
    	                .topic(topics.get(i))
    	                .details(details.get(i))
    	                .durationHours(durationHours.get(i))
    	                .mediafile(mediaFilePath)
    	                .build();
    	        
    	        // CourseInfo 저장
    	        courseInfoRepository.save(courseInfo);
    	    }
    	}

    private String uploadDir = "C:/uploads";

    private String saveFile(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);

        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }

        return fileName;
    }
}