package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.domain.Qna;
import com.company.service.QnaService;

@RestController
@RequestMapping("/qna")
public class QnaController {

    private final QnaService qnaService;

    @Autowired
    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Qna>> getQnaList() {
        List<Qna> qnas = qnaService.getQnaList();
        return new ResponseEntity<>(qnas, HttpStatus.OK);
    }

    @GetMapping("/detail/{qno}")
    public ResponseEntity<Qna> getQna(@PathVariable("qno") int qno) {
        Qna qna = qnaService.getQna(qno);
        if (qna != null) {
            return new ResponseEntity<>(qna, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertQuestion(@RequestBody Qna qna) {
        qnaService.insertQuestion(qna);
        return new ResponseEntity<>("Question inserted successfully", HttpStatus.CREATED);
    }

    @PostMapping("/answer/insert/{parno}")
    public ResponseEntity<String> insertAnswer(@PathVariable("parno") int parno, @RequestBody Qna qna) {
        qnaService.insertAnswer(qna);
        return new ResponseEntity<>("Answer inserted successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{qno}")
    public ResponseEntity<String> updateQna(@PathVariable("qno") int qno, @RequestBody Qna qna) {
    	qna.setQno(qno);
        qnaService.update(qna);
        return new ResponseEntity<>("Qna updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{qno}")
    public ResponseEntity<String> deleteQna(@PathVariable("qno") int qno, @RequestParam("lev") int lev) {
        if (lev == 0) {
            qnaService.deleteQuestion(qno);
            return new ResponseEntity<>("Question and its answers deleted successfully", HttpStatus.OK);
        } else {
            qnaService.deleteAnswer(qno);
            return new ResponseEntity<>("Answer deleted successfully", HttpStatus.OK);
        }
    }
}