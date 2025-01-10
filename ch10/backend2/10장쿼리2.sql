USE company;

CREATE TABLE dataroom (
    dno INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    author VARCHAR(255),
    datafile VARCHAR(255),
    resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
    hits INT DEFAULT 0
);

-- dataroom 테이블에 대한 더미 데이터 삽입
INSERT INTO dataroom (title, content, author, datafile) VALUES
('Data 1', 'This is the first data.', 'Alice', 'file1.pdf'),
('Data 2', 'This is the second data.', 'Bob', 'file2.pdf'),
('Data 3', 'This is the third data.', 'Charlie', 'file3.pdf'),
('Data 4', 'This is the fourth data.', 'David', 'file4.pdf'),
('Data 5', 'This is the fifth data.', 'Eve', 'file5.pdf');

COMMIT;

SHOW TABLES;

DESC board_post;

DROP TABLE board_post;

USE company;

CREATE TABLE board_post(id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255), content VARCHAR(255));

INSERT INTO board_post VALUES (DEFAULT, '제목1', '더미 데이터 내용1');
INSERT INTO board_post VALUES (DEFAULT, '제목2', '더미 데이터 내용2');
INSERT INTO board_post VALUES (DEFAULT, '제목3', '더미 데이터 내용3');
INSERT INTO board_post VALUES (DEFAULT, '제목4', '더미 데이터 내용4');
INSERT INTO board_post VALUES (DEFAULT, '제목5', '더미 데이터 내용5');

COMMIT;