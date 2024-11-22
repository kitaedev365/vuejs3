const express = require('express');
const router = express.Router();
const qnaController = require('../controllers/qnaController');

// 질문 목록 조회
router.get('/list', qnaController.list);

// 질문 상세 조회
router.get('/detail/:qno', qnaController.detail);

// 질문 작성
router.post('/insert', qnaController.create);

// 답변 조회
router.get('/:qno/answer', qnaController.getAnswer);

// 질문 수정
router.put('/update/:qno', qnaController.update);

// 질문 삭제
router.delete('/delete/:qno', qnaController.delete);

module.exports = router;