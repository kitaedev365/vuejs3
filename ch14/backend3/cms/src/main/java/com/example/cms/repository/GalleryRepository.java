package com.example.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
	List<Gallery> findAllByContentId(Long contentId);
}