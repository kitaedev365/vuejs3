-- 데이터베이스 생성
CREATE DATABASE sns;
USE sns;

-- 회원 테이블
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    profile_picture VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 게시물 테이블
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content TEXT,
    media_url VARCHAR(255),
    media_type ENUM('TEXT', 'IMAGE', 'VIDEO') DEFAULT 'TEXT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 댓글 테이블
CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 채팅 메시지 테이블
CREATE TABLE messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
);


-- 회원 테이블 더미 데이터
INSERT INTO users (username, password, email, profile_picture) VALUES
('user1', '$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q', 'user1@example.com', 'https://example.com/profile1.jpg'),
('user2', '$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q', 'user2@example.com', 'https://example.com/profile2.jpg'),
('user3', '$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q', 'user3@example.com', 'https://example.com/profile3.jpg'),
('user4', '$2a$10$OZIvZWuUtHZgH9mNZvE8ku/sKr1KkF64wH3yTF9J7wGHJgVYmB7Cm', 'user4@example.com', 'https://example.com/profile4.jpg'),
('user5', '$2a$10$OZIvZWuUtHZgH9mNZvE8ku/sKr1KkF64wH3yTF9J7wGHJgVYmB7Cm', 'user5@example.com', 'https://example.com/profile5.jpg');

-- 게시물 테이블 더미 데이터
INSERT INTO posts (user_id, content, media_file, media_type) VALUES
(1, '첫 번째 게시물입니다.', 'image1.jpg', 'IMAGE'),
(2, '두 번째 게시물입니다.', 'movie2.mp4', 'VIDEO'),
(3, '세 번째 게시물입니다.', 'data3.txt', 'TEXT'),
(4, '네 번째 게시물입니다.', 'image4.jpg', 'IMAGE'),
(5, '다섯 번째 게시물입니다.', 'movie5.mp4', 'VIDEO');

-- 댓글 테이블 더미 데이터
INSERT INTO comments (post_id, user_id, content) VALUES
(1, 2, '첫 번째 게시물의 첫 번째 댓글입니다.'),
(1, 3, '첫 번째 게시물의 두 번째 댓글입니다.'),
(2, 1, '두 번째 게시물의 첫 번째 댓글입니다.'),
(3, 4, '세 번째 게시물의 첫 번째 댓글입니다.'),
(4, 5, '네 번째 게시물의 첫 번째 댓글입니다.');

-- 채팅 메시지 테이블 더미 데이터
INSERT INTO messages (sender_id, receiver_id, content) VALUES
(1, 2, '안녕하세요, user2님!'),
(2, 1, '안녕하세요, user1님!'),
(3, 4, '오늘 약속 시간 괜찮으신가요?'),
(4, 3, '네, 괜찮습니다.'),
(5, 1, '프로젝트 관련해서 질문이 있습니다.');

COMMIT;

SELECT * FROM users;

SELECT * FROM posts;

SELECT * FROM comments;

SELECT * FROM messages;
