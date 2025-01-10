package com.example.myproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.myproject.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.posts")
    List<Board> findAllWithPosts();
}