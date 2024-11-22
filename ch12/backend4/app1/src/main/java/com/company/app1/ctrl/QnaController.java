package com.company.app1.ctrl;

import com.company.app1.biz.QnaService;
import com.company.app1.entity.Qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @GetMapping("/list")
    public List<Qna> getAllQnas() {
        return qnaService.getAllQnas();
    }

    @GetMapping("/detail/{qno}")
    public ResponseEntity<Qna> getQnaById(@PathVariable("qno") int qno) {
        Optional<Qna> qna = qnaService.getQnaById(qno);
        return qna.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public Qna createQna(@RequestBody Qna qna) {
        if (qna.getLev() == 0) {
            qna.setParno(null); // 질문 글이므로 parno를 null로 설정
        } else {
            qna.setParno(qna.getParno()); // 답글이므로 parno를 설정
        }
        return qnaService.saveQna(qna);
    }

    @PostMapping("/answer/insert/{parno}")
    public Qna createReply(@PathVariable("parno") int parno, @RequestBody Qna qna) {
        qna.setLev(2);
        return qnaService.saveQna(qna);
    }

    @PutMapping("/update/{qno}")
    public ResponseEntity<Qna> updateQna(@PathVariable("qno") int qno, @RequestBody Qna qna) {
        return ResponseEntity.ok(qnaService.updateQna(qno, qna));
    }

    @DeleteMapping("/delete/{qno}")
    public ResponseEntity<Void> deleteQna(@PathVariable("qno") int qno) {
        qnaService.deleteQna(qno);
        return ResponseEntity.noContent().build();
    }
}