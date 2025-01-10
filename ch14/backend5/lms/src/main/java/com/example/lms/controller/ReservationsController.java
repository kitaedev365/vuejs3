package com.example.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dto.ReservationsDTO;
import com.example.lms.entity.Reservations;
import com.example.lms.service.ReservationsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationsController {
    private final ReservationsService reservationsService;

    // 전체 예약 목록 조회
    @GetMapping
    public ResponseEntity<List<Reservations>> getAllReservations() {
        List<Reservations> reservations = reservationsService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    // 예약 상세 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable("id") Long id) {
        Optional<Reservations> reservation = reservationsService.getReservationById(id);
        return reservation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 예약 추가
    @PostMapping
    public ResponseEntity<Reservations> createReservation(@RequestBody Reservations reservation) {
        Reservations savedReservation = reservationsService.createReservation(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    // 예약 수정
    @PutMapping("/{id}")
    public ResponseEntity<Reservations> updateReservation(@PathVariable("id") Long id, @RequestBody Reservations updatedReservation) {
        Optional<Reservations> reservation = reservationsService.updateReservation(id, updatedReservation);
        return reservation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 예약 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        reservationsService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    // course_id로 예약 목록 조회
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Reservations>> getReservationsByCourseId(@PathVariable("courseId") Long courseId) {
        List<Reservations> reservations = reservationsService.getReservationsByCourseId(courseId);
        return ResponseEntity.ok(reservations);
    }

    // student_id로 예약 목록 조회
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Reservations>> getReservationsByStudentId(@PathVariable("studentId") Long studentId) {
        List<Reservations> reservations = reservationsService.getReservationsByStudentId(studentId);
        return ResponseEntity.ok(reservations);
    }
    
    // student_id별 수강(course) 목록 조회
    @GetMapping("/student/course/{studentId}")
    public ResponseEntity<List<ReservationsDTO>> getReservationsByCourseIdAndStudentId(@PathVariable("studentId") Long studentId) {
        List<ReservationsDTO> reservations = reservationsService.getReservationsByCourseIdAndStudentId(studentId);
        return ResponseEntity.ok(reservations);
    }
}