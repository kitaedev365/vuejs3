const express = require('express');
const multer = require('multer');
const router = express.Router();
const productController = require('../controllers/productController');

// 파일 업로드 설정
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, './uploads/products'); // 업로드할 디렉토리 설정
  },
  filename: (req, file, cb) => {
    cb(null, Date.now() + '-' + file.originalname); // 파일 이름 설정
  }
});
const upload = multer({ storage });

// 상품 등록 및 이미지 파일 업로드
router.post('/insert', upload.fields([{ name: 'img1', maxCount: 1 }, { name: 'img2', maxCount: 1 }, { name: 'img3', maxCount: 1 }]), productController.create);

// 상품 정보 수정 및 이미지 파일 변경
router.put('/update/:pno', upload.fields([{ name: 'img1', maxCount: 1 }, { name: 'img2', maxCount: 1 }, { name: 'img3', maxCount: 1 }]), productController.update);

// 상품 삭제 및 이미지 파일 삭제
router.delete('/delete/:pno', productController.delete);

// 상품 목록 조회
router.get('/list', productController.list);

// 상품 상세 조회
router.get('/detail/:pno', productController.detail);

module.exports = router;
