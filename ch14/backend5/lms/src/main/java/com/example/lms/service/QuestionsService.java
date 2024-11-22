package com.example.lms.service;

import com.example.lms.entity.Questions;
import com.example.lms.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionsService {
    private final QuestionsRepository questionsRepository;

    // 전체 문제 목록 조회
    public List<Questions> getAllQuestions() {
        return questionsRepository.findAll();
    }

    // 문제 상세 조회
    public Optional<Questions> getQuestionById(Long id) {
        return questionsRepository.findById(id);
    }

    // 문제 추가
    public Questions createQuestion(Questions question) {
        return questionsRepository.save(question);
    }

    // 문제 수정
    public Optional<Questions> updateQuestion(Long id, Questions updatedQuestion) {
        return questionsRepository.findById(id).map(question -> {
            question.setCourse(updatedQuestion.getCourse());
            question.setQuestionText(updatedQuestion.getQuestionText());
            question.setAnswer(updatedQuestion.getAnswer());
            return question;
        });
    }

    // 문제 삭제
    public void deleteQuestion(Long id) {
        questionsRepository.deleteById(id);
    }

    // course_id로 문제 목록 조회
    public List<Questions> getQuestionsByCourseId(Long courseId) {
        return questionsRepository.findByCourseId(courseId);
    }
}