package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}