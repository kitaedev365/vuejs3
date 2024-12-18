package com.example.lms.controller;

import com.example.lms.entity.Questions;
import com.example.lms.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;

    // 전체 문제 목록 조회
    @GetMapping
    public ResponseEntity<List<Questions>> getAllQuestions() {
        List<Questions> questions = questionsService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // 문제 상세 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Questions> getQuestionById(@PathVariable("id") Long id) {
        Optional<Questions> question = questionsService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 문제 추가
    @PostMapping
    public ResponseEntity<Questions> createQuestion(@RequestBody Questions question) {
        Questions savedQuestion = questionsService.createQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    // 문제 수정
    @PutMapping("/{id}")
    public ResponseEntity<Questions> updateQuestion(@PathVariable("id") Long id, @RequestBody Questions updatedQuestion) {
        Optional<Questions> question = questionsService.updateQuestion(id, updatedQuestion);
        return question.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 문제 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
        questionsService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    // course_id로 문제 목록 조회
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Questions>> getQuestionsByCourseId(@PathVariable("courseId") Long courseId) {
        List<Questions> questions = questionsService.getQuestionsByCourseId(courseId);
        return ResponseEntity.ok(questions);
    }
}