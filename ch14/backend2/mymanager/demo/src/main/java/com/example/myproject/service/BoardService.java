package com.example.myproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.domain.BoardDto;
import com.example.myproject.domain.PostDto;
import com.example.myproject.entity.Board;
import com.example.myproject.entity.Post;
import com.example.myproject.repository.BoardRepository;
import com.example.myproject.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    
    @Autowired
    private PostRepository postRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board updateBoard(Long id, Board board) {
        if (boardRepository.existsById(id)) {
            board.setId(id);
            return boardRepository.save(board);
        }
        return null;
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public List<Board> getAllBoardsWithPosts() {
        return boardRepository.findAllWithPosts();
    }
    
    @Transactional
    public void saveBoardOrder(List<BoardDto> boards) {
        for (BoardDto board : boards) {
            for (PostDto post : board.getPosts()) {
                Post existingPost = postRepository.findById(post.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Post not found: " + post.getId()));
                existingPost.setPosition(post.getPosition());
                postRepository.save(existingPost);  // Update position in DB
            }
        }
    }
}
