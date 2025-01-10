package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}