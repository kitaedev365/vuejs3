package com.company.app1.ctrl;

import com.company.app1.biz.BoardService;
import com.company.app1.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/detail/{no}")
    public ResponseEntity<Board> getBoardById(@PathVariable("no") int no) {
        Optional<Board> board = boardService.getBoardById(no);
        return board.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public Board createBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }

    @PutMapping("/update/{no}")
    public ResponseEntity<Board> updateBoard(@PathVariable("no") int no, @RequestBody Board board) {
        Board updatedBoard = boardService.updateBoard(no, board);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/delete/{no}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("no") int no) {
        boardService.deleteBoard(no);
        return ResponseEntity.noContent().build();
    }
}