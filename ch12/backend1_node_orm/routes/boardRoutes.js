const express = require('express');
const router = express.Router();
const boardController = require('../controllers/boardController');

// 글 목록 조회
router.get('/list', boardController.list);

// 글 상세 조회
router.get('/detail/:no', boardController.detail);

// 글 작성
router.post('/insert', boardController.create);

// 글 수정
router.put('/update/:no', boardController.update);

// 글 삭제
router.delete('/delete/:no', boardController.delete);

module.exports = router;