// server.js
const express = require('express');
const mysql = require('mysql2');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const PORT = 3000;

// CORS 설정
const corsOptions = {
  origin: '*', // 모든 출처 허용 (특정 출처로 제한할 수 있음)
  methods: ['GET', 'POST', 'PUT', 'DELETE'], // 허용할 HTTP 메서드
  allowedHeaders: ['Content-Type'], // 허용할 헤더
};

// Middleware 설정
app.use(cors(corsOptions)); // CORS 미들웨어 사용
app.use(bodyParser.json());

// MySQL 데이터베이스 연결 설정
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '1234',
  database: 'company',
  port: 3307,
});

// 데이터베이스 연결
db.connect((err) => {
  if (err) {
    console.error('데이터베이스 연결 실패:', err);
    return;
  }
  console.log('데이터베이스에 성공적으로 연결되었습니다.');
});


const createTables = () => {
    // 필요한 테이블 생성
    const createBoardTable = `
      CREATE TABLE IF NOT EXISTS board (
        no INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        content TEXT NOT NULL,
        author VARCHAR(100) NOT NULL,
        resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
        hits INT DEFAULT 0
      )`;
  
    const createQnaTable = `
      CREATE TABLE IF NOT EXISTS qna (
        qno INT AUTO_INCREMENT PRIMARY KEY,
        lev INT DEFAULT 0,
        parno INT,
        title VARCHAR(255),
        content TEXT,
        author VARCHAR(255),
        resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
        hits INT DEFAULT 0
      )`;
  
    const createDataroomTable = `
      CREATE TABLE IF NOT EXISTS dataroom (
        dno INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255),
        content TEXT,
        author VARCHAR(255),
        datafile VARCHAR(255),
        resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
        hits INT DEFAULT 0
      )`;
  
    const createProductTable = `
      CREATE TABLE IF NOT EXISTS product (
        pno INT AUTO_INCREMENT PRIMARY KEY,
        cate VARCHAR(255),
        pname VARCHAR(255),
        pcontent TEXT,
        img1 VARCHAR(255),
        img2 VARCHAR(255),
        img3 VARCHAR(255),
        resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
        hits INT DEFAULT 0
      )`;
  
    // 테이블 생성
    db.query(createBoardTable, (err) => {
      if (err) {
        console.error('board 테이블 생성 실패:', err);
      } else {
        console.log('board 테이블 생성 완료.');
      }
    });
  
    db.query(createQnaTable, (err) => {
      if (err) {
        console.error('qna 테이블 생성 실패:', err);
      } else {
        console.log('qna 테이블 생성 완료.');
      }
    });
  
    db.query(createDataroomTable, (err) => {
      if (err) {
        console.error('dataroom 테이블 생성 실패:', err);
      } else {
        console.log('dataroom 테이블 생성 완료.');
      }
    });
  
    db.query(createProductTable, (err) => {
      if (err) {
        console.error('product 테이블 생성 실패:', err);
      } else {
        console.log('product 테이블 생성 완료.');
      }
    });
  };
  
  // 데이터베이스에 연결한 후 테이블 생성 함수 호출
  db.connect((err) => {
    if (err) {
      console.error('데이터베이스 연결 실패:', err);
      return;
    }
    console.log('데이터베이스에 성공적으로 연결되었습니다.');
    createTables(); // 테이블 생성 호출
  });


// 공지사항 (boards) API
app.get('/boards/list', (req, res) => {
  db.query('SELECT * FROM board', (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results);
  });
});

app.get('/boards/detail/:no', (req, res) => {
  const no = req.params.no;
  db.query('SELECT * FROM board WHERE no = ?', [no], (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results[0]);
  });
});

app.post('/boards/insert', (req, res) => {
  const { title, content, author } = req.body;
  db.query('INSERT INTO board (title, content, author) VALUES (?, ?, ?)', [title, content, author], (err, results) => {
    if (err) return res.status(500).send(err);
    res.status(201).json({ id: results.insertId });
  });
});

app.put('/boards/update/:no', (req, res) => {
  const no = req.params.no;
  const { title, content, author } = req.body;
  db.query('UPDATE board SET title = ?, content = ?, author = ? WHERE no = ?', [title, content, author, no], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

app.delete('/boards/delete/:no', (req, res) => {
  const no = req.params.no;
  db.query('DELETE FROM board WHERE no = ?', [no], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

// 질문 및 답변 (qna) API
app.get('/qna/list', (req, res) => {
  db.query('SELECT * FROM qna', (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results);
  });
});

app.get('/qna/detail/:qno', (req, res) => {
  const qno = req.params.qno;
  db.query('SELECT * FROM qna WHERE qno = ?', [qno], (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results[0]);
  });
});

app.post('/qna/insert', (req, res) => {
  const { title, content, author } = req.body;
  db.query('INSERT INTO qna (title, content, author, lev) VALUES (?, ?, ?, ?)', [title, content, author, 1], (err, results) => {
    if (err) return res.status(500).send(err);
    res.status(201).json({ id: results.insertId });
  });
});

app.post('/qna/answer/insert/:parno', (req, res) => {
  const parno = req.params.parno;
  const { title, content, author } = req.body;
  db.query('INSERT INTO qna (title, content, author, lev, parno) VALUES (?, ?, ?, ?, ?)', [title, content, author, 2, parno], (err, results) => {
    if (err) return res.status(500).send(err);
    res.status(201).json({ id: results.insertId });
  });
});

app.put('/qna/update/:qno', (req, res) => {
  const qno = req.params.qno;
  const { title, content, author } = req.body;
  db.query('UPDATE qna SET title = ?, content = ?, author = ? WHERE qno = ?', [title, content, author, qno], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

app.delete('/qna/delete/:qno', (req, res) => {
  const qno = req.params.qno;
  db.query('DELETE FROM qna WHERE qno = ?', [qno], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

// 자료실 (dataroom) API
app.get('/dataroom/list', (req, res) => {
  db.query('SELECT * FROM dataroom', (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results);
  });
});

app.get('/dataroom/detail/:dno', (req, res) => {
  const dno = req.params.dno;
  db.query('SELECT * FROM dataroom WHERE dno = ?', [dno], (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results[0]);
  });
});

app.post('/dataroom/insert', (req, res) => {
  const { title, content, author, datafile } = req.body;
  db.query('INSERT INTO dataroom (title, content, author, datafile) VALUES (?, ?, ?, ?)', [title, content, author, datafile], (err, results) => {
    if (err) return res.status(500).send(err);
    res.status(201).json({ id: results.insertId });
  });
});

app.put('/dataroom/update/:dno', (req, res) => {
  const dno = req.params.dno;
  const { title, content, author, datafile } = req.body;
  db.query('UPDATE dataroom SET title = ?, content = ?, author = ?, datafile = ? WHERE dno = ?', [title, content, author, datafile, dno], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

app.delete('/dataroom/delete/:dno', (req, res) => {
  const dno = req.params.dno;
  db.query('DELETE FROM dataroom WHERE dno = ?', [dno], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

// 상품 (products) API
app.get('/products/list', (req, res) => {
  db.query('SELECT * FROM product', (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results);
  });
});

app.get('/products/detail/:pno', (req, res) => {
  const pno = req.params.pno;
  db.query('SELECT * FROM product WHERE pno = ?', [pno], (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results[0]);
  });
});

app.post('/products/insert', (req, res) => {
  const { cate, pname, pcontent, img1, img2, img3 } = req.body;
  db.query('INSERT INTO product (cate, pname, pcontent, img1, img2, img3) VALUES (?, ?, ?, ?, ?, ?)', [cate, pname, pcontent, img1, img2, img3], (err, results) => {
    if (err) return res.status(500).send(err);
    res.status(201).json({ id: results.insertId });
  });
});

app.put('/products/update/:pno', (req, res) => {
  const pno = req.params.pno;
  const { cate, pname, pcontent, img1, img2, img3 } = req.body;
  db.query('UPDATE product SET cate = ?, pname = ?, pcontent = ?, img1 = ?, img2 = ?, img3 = ? WHERE pno = ?', [cate, pname, pcontent, img1, img2, img3, pno], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

app.delete('/products/delete/:pno', (req, res) => {
  const pno = req.params.pno;
  db.query('DELETE FROM product WHERE pno = ?', [pno], (err) => {
    if (err) return res.status(500).send(err);
    res.status(204).send();
  });
});

// 서버 실행
app.listen(PORT, () => {
  console.log(`서버가 ${PORT} 포트에서 실행되고 있습니다.`);
});