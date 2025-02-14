package com.example.sns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sns.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // 메시지 찾기: 특정 사용자 간의 메시지 조회 (대화 내역)
    List<Message> findBySenderIdAndReceiverIdOrderBySentAtAsc(Long senderId, Long receiverId);

    // 모든 메시지 조회 (받은 메시지 기준)
    List<Message> findByReceiverIdOrderBySentAtDesc(Long receiverId);

	List<Message> findBySenderId(Long sendId);
}