package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}