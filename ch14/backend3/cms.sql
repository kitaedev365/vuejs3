-- 데이터베이스 생성
CREATE DATABASE cms;
USE cms;

-- 회원 테이블
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role ENUM('USER', 'ADMIN') DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 공지사항 테이블
CREATE TABLE notices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    hits BIGINT DEFAULT 0
);

-- 콘텐츠 테이블
CREATE TABLE contents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    content TEXT NOT NULL,
    author_id BIGINT NULL,  -- NULL을 허용하여 ON DELETE SET NULL 동작 가능
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE SET NULL
);

-- 갤러리 테이블
CREATE TABLE gallery (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content_id BIGINT NULL,
    file_path VARCHAR(255) NOT NULL,
    file_type ENUM('IMAGE', 'VIDEO') DEFAULT 'IMAGE',
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE
);


-- pass1234   -> $2a$10$VVRXHd5kGV6fl5v8nJ/mS.dHXBmcC7/hV3Inl/qe6U6Jh8zA1U2AC
-- admin1234  -> $2a$10$e6N9cPZ4bgW6f.xKaRQExeMvA9dTmd1T4OnbSiWBn4GjSTiVsGq/i
-- user5678   -> $2a$10$N9nnr8ZYuTz/kCnT1ecqBO9qB1m69/3OUoHz9XaEnD6Tl0TpnAwS.
-- testabcd   -> $2a$10$HzP8HxPpD6pOz5D8P2FOTuTbmkYQf5ymv6RI.Z6LIMeGv4jQmK04W
-- securepass -> $2a$10$YCM9P5nGGfS1kz/XNOM3OuEobHdBjdjwiNBkzXc8Bt4nmHrb3pUka


-- 회원 테이블 데이터 삽입
INSERT INTO users (username, password, email, role) VALUES
('user1', '$2a$10$VVRXHd5kGV6fl5v8nJ/mS.dHXBmcC7/hV3Inl/qe6U6Jh8zA1U2AC', 'user1@example.com', 'USER'),
('admin1', '$2a$10$e6N9cPZ4bgW6f.xKaRQExeMvA9dTmd1T4OnbSiWBn4GjSTiVsGq/i', 'admin1@example.com', 'ADMIN'),
('user2', '$2a$10$N9nnr8ZYuTz/kCnT1ecqBO9qB1m69/3OUoHz9XaEnD6Tl0TpnAwS.', 'user2@example.com', 'USER'),
('user3', '$2a$10$HzP8HxPpD6pOz5D8P2FOTuTbmkYQf5ymv6RI.Z6LIMeGv4jQmK04W', 'user3@example.com', 'USER'),
('user4', '$2a$10$YCM9P5nGGfS1kz/XNOM3OuEobHdBjdjwiNBkzXc8Bt4nmHrb3pUka', 'user4@example.com', 'USER');

UPDATE users SET PASSWORD='$2b$10$gjPQwFft67HLj/gbjPevuXzjmHHW0Zn5c/R6xjRYhD6qJtJHME.Vu' WHERE username='admin1';

-- 공지사항 테이블 데이터 삽입
INSERT INTO notices (title, content) VALUES
('시스템 점검 안내', '시스템 점검으로 인한 서비스 일시 중단 안내입니다.'),
('신규 기능 추가', '이번 업데이트에서 신규 기능이 추가되었습니다.'),
('이용 약관 변경', '새로운 이용 약관이 적용되었습니다.'),
('정기 점검 일정 공지', '다음 주에 정기 점검이 예정되어 있습니다.'),
('보안 업데이트 안내', '보안 업데이트가 완료되었습니다.');

-- 콘텐츠 테이블 데이터 삽입
INSERT INTO contents (title, description, content, author_id) VALUES
('첫 번째 글', '이것은 첫 번째 더미 글입니다.', '첫 번째 글의 본문입니다.', 1),
('두 번째 글', '이것은 두 번째 더미 글입니다.', '두 번째 글의 본문입니다.', 2),
('세 번째 글', '이것은 세 번째 더미 글입니다.', '세 번째 글의 본문입니다.', 3),
('네 번째 글', '이것은 네 번째 더미 글입니다.', '네 번째 글의 본문입니다.', 4),
('다섯 번째 글', '이것은 다섯 번째 더미 글입니다.', '다섯 번째 글의 본문입니다.', 5);

-- 갤러리 테이블 데이터 삽입
INSERT INTO gallery (content_id, file_path, file_type) VALUES
(1, 'image1.jpg', 'IMAGE'),
(2, 'image2.jpg', 'IMAGE'),
(3, 'image3.jpg', 'IMAGE'),
(4, 'image4.jpg', 'IMAGE'),
(5, 'video1.mp4', 'VIDEO');

SHOW TABLES;

SELECT * FROM users;

COMMIT;
