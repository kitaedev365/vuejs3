package com.example.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.lms.dto.ReservationsDTO;
import com.example.lms.entity.Reservations;
import com.example.lms.repository.CoursesRepository;
import com.example.lms.repository.ReservationsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationsService {
    private final ReservationsRepository reservationsRepository;
    private final CoursesRepository coursesRepository;
    
    // 전체 예약 목록 조회
    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll();
    }

    // 예약 상세 정보 조회
    public Optional<Reservations> getReservationById(Long id) {
        return reservationsRepository.findById(id);
    }

    // 예약 추가
    public Reservations createReservation(Reservations reservation) {
        return reservationsRepository.save(reservation);
    }

    // 예약 수정
    public Optional<Reservations> updateReservation(Long id, Reservations updatedReservation) {
        return reservationsRepository.findById(id).map(reservation -> {
            reservation.setCourse(updatedReservation.getCourse());
            reservation.setStudent(updatedReservation.getStudent());
            reservation.setStatus(updatedReservation.getStatus());
            return reservation;
        });
    }

    // 예약 삭제
    public void deleteReservation(Long id) {
        reservationsRepository.deleteById(id);
    }

    // course_id로 예약 목록 조회
    public List<Reservations> getReservationsByCourseId(Long courseId) {
        return reservationsRepository.findByCourseId(courseId);
    }

    // student_id로 예약 목록 조회
    public List<Reservations> getReservationsByStudentId(Long studentId) {
        return reservationsRepository.findByStudentId(studentId);
    }
    
    // studentId로 수강 예약 목록 조회
    public List<ReservationsDTO> getReservationsByCourseIdAndStudentId(Long studentId) {
        List<Reservations> reservations = reservationsRepository.findByStudentAndCourseId(studentId); // findByStudentId 호출
        return reservations.stream()
                .map(reservation -> ReservationsDTO.builder()
                        .id(reservation.getId())
                        .courseId(reservation.getCourse().getId())
                        .courseTitle(coursesRepository.getTitleById(reservation.getCourse().getId()))
                        .studentId(reservation.getStudent().getId())
                        .status(reservation.getStatus().name()) // Enum 값을 문자열로 변환
                        .build())
                .collect(Collectors.toList());
    }
}