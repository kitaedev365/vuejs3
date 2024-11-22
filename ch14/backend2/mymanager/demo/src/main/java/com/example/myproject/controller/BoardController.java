package com.example.myproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.domain.BoardDto;
import com.example.myproject.domain.PostDto;
import com.example.myproject.entity.Board;
import com.example.myproject.entity.Post;
import com.example.myproject.repository.PostRepository;
import com.example.myproject.service.BoardService;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;
        
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @GetMapping("/list")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Optional<Board> getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody Board board) {
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
    
    @GetMapping("/list-with-posts")
    public List<Board> getBoardsWithPosts() {
        return boardService.getAllBoardsWithPosts();
    }
    
    @PostMapping("/save-order")
    public ResponseEntity<String> saveBoardOrder(@RequestBody List<BoardDto> boards) {
        boardService.saveBoardOrder(boards);
        return ResponseEntity.ok("Order saved successfully");
    }
}