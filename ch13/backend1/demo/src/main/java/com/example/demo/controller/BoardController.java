package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BoardPost;
import com.example.demo.repository.BoardPostRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class BoardController {
   @Autowired
   private BoardPostRepository boardPostRepository;
   @GetMapping("/api/board")
   public List<BoardPost> getBoardPosts() {
        return boardPostRepository.findAll();
   }
}