-- 데이터베이스 생성
CREATE DATABASE lms;
USE lms;

SHOW TABLES;

DROP TABLE users;

-- 회원 테이블
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(100),
    role ENUM('STUDENT', 'INSTRUCTOR', 'ADMIN') DEFAULT 'STUDENT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 과정 테이블
CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    instructor_id BIGINT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    lector VARCHAR(255),
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (instructor_id) REFERENCES users(id) ON DELETE SET NULL
);

-- 과정 상세 정보
CREATE TABLE course_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    topic VARCHAR(255) NOT NULL,
    details TEXT,
    duration_hours INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    mediafile VARCHAR(100),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- 수강 테이블
CREATE TABLE enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ENROLLED', 'CANCELLED') DEFAULT 'ENROLLED',
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 수강 예약 테이블
CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('RESERVED', 'CONFIRMED', 'CANCELLED') DEFAULT 'RESERVED',
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 문제 테이블
CREATE TABLE questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    question_text TEXT NOT NULL,
    answer TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);


-- Bcrypt로 '1234'를 암호화한 값 (예시: '$2b$10$...' 형태)
-- 아래 값은 미리 암호화된 값이므로 실제 환경에서는 Bcrypt 라이브러리를 사용해 생성 필요.
-- 비밀번호: 1234 -> '$2b$10$EZzX/IO6Pb1rZOpR2J5X.O00LLHeEde9P0h7GTVE0b8c52p23w99W'

-- users 테이블에 데이터 삽입
INSERT INTO users (username, password, email, name, role) VALUES
('student1', '$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q', 'student1@example.com', '홍길동', 'STUDENT'),
('student2', '$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q', 'student2@example.com', '성춘향', 'STUDENT'),
('instructor1', '$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q', 'instructor1@example.com', '김과장', 'INSTRUCTOR'),
('instructor2', '$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q', 'instructor2@example.com', '박대리', 'INSTRUCTOR'),
('admin', '$2b$10$EZzX/IO6Pb1rZOpR2J5X.O00LLHeEde9P0h7GTVE0b8c52p23w99W', 'admin1@example.com', '관리자', 'ADMIN');

UPDATE users SET PASSWORD='$2a$10$uLzsHxte2v8gvL.WheggOOFJUgCGAuvGdnjtRZDSnAFHtVhdCZZ3q';

-- courses 테이블에 데이터 삽입
INSERT INTO courses (instructor_id, title, DESCRIPTION, lector, start_date, end_date) VALUES
(3, '기초 수학', '기초 수학을 배우는 과정입니다.', '김수학', '2024-01-01', '2024-03-01'),
(3, '고급 수학', '고급 수학을 배우는 과정입니다.', '고수학', '2024-03-15', '2024-06-15'),
(4, '기초 물리', '기초 물리학을 배우는 과정입니다.', '박물리', '2024-02-01', '2024-04-01'),
(4, '고급 물리', '고급 물리학을 배우는 과정입니다.', '서물리', '2024-05-01', '2024-08-01'),
(3, '프로그래밍 입문', '초보자를 위한 프로그래밍 강의입니다.', '송전산', '2024-01-15', '2024-04-15');

-- 기초 수학 과정의 상세 정보
INSERT INTO course_info (course_id, topic, details, duration_hours, created_at, updated_at, mediafile) VALUES
(1, '정수와 소수', '정수와 소수의 기본 개념을 학습합니다.', 3, NOW(), NOW(), 'math_basic1.mp4')
(1, '기본 연산', '덧셈, 뺄셈, 곱셈, 나눗셈을 학습합니다.', 5, NOW(), NOW(), 'math_basic2.mp4'),
(1, '방정식 기초', '기본 방정식 풀이를 학습합니다.', 4, NOW(), NOW(), 'math_basic3.mp4'),
(1, '기하학 기초', '기하학의 기본 개념을 학습합니다.', 6, NOW(), NOW(), 'math_basic4.mp4'),
(1, '확률과 통계', '확률과 통계의 기본 개념을 학습합니다.', 3, NOW(), NOW(), 'math_basic5.mp4');

-- 고급 수학 과정의 상세 정보
INSERT INTO course_info (course_id, topic, details, duration_hours, created_at, updated_at, mediafile) VALUES
(2, '미분', '미분의 개념과 활용을 학습합니다.', 6, NOW(), NOW(), 'math_expert1.mp4'),
(2, '적분', '적분의 개념과 활용을 학습합니다.', 6, NOW(), NOW(), 'math_expert2.mp4'),
(2, '복소수', '복소수의 개념과 연산을 학습합니다.', 4, NOW(), NOW(), 'math_expert3.mp4'),
(2, '행렬과 벡터', '행렬과 벡터의 연산을 학습합니다.', 5, NOW(), NOW(), 'math_expert4.mp4'),
(2, '고급 기하학', '고급 기하학 개념을 학습합니다.', 7, NOW(), NOW(), 'math_expert5.mp4');

-- 기초 물리 과정의 상세 정보
INSERT INTO course_info (course_id, topic, details, duration_hours, created_at, updated_at, mediafile) VALUES
(3, '운동의 법칙', '기본 운동의 법칙을 학습합니다.', 5, NOW(), NOW(), 'physical_basic1.mp4'),
(3, '에너지와 일', '에너지와 일의 개념을 학습합니다.', 4, NOW(), NOW(), 'physical_basic2.mp4'),
(3, '파동과 소리', '파동과 소리의 기초 개념을 학습합니다.', 6, NOW(), NOW(), 'physical_basic3.mp4'),
(3, '열역학 기초', '열역학의 기본 개념을 학습합니다.', 4, NOW(), NOW(), 'physical_basic4.mp4'),
(3, '기본 전자기학', '전자기학의 기본 개념을 학습합니다.', 6, NOW(), NOW(), 'physical_basic5.mp4');

-- 고급 물리 과정의 상세 정보
INSERT INTO course_info (course_id, topic, details, duration_hours, created_at, updated_at, mediafile) VALUES
(4, '양자역학', '양자역학의 기본 개념을 학습합니다.', 8, NOW(), NOW(), 'physical_expert1.mp4'),
(4, '상대성이론', '상대성이론의 기본 개념을 학습합니다.', 8, NOW(), NOW(), 'physical_expert2.mp4'),
(4, '고급 전자기학', '고급 전자기학의 개념을 학습합니다.', 7, NOW(), NOW(), 'physical_expert3.mp4'),
(4, '고급 열역학', '고급 열역학 개념을 학습합니다.', 6, NOW(), NOW(), 'physical_expert4.mp4'),
(4, '고급 파동 이론', '파동 이론의 심화 개념을 학습합니다.', 6, NOW(), NOW(), 'physical_expert5.mp4');

-- 프로그래밍 입문 과정의 상세 정보
INSERT INTO course_info (course_id, topic, details, duration_hours, created_at, updated_at, mediafile) VALUES
(5, '변수와 자료형', '프로그래밍에서 변수와 자료형을 학습합니다.', 4, NOW(), NOW(), 'pg1.mp4'),
(5, '조건문과 반복문', '조건문과 반복문의 사용법을 학습합니다.', 5, NOW(), NOW(), 'pg2.mp4'),
(5, '함수와 메서드', '함수와 메서드의 개념과 작성 방법을 학습합니다.', 5, NOW(), NOW(), 'pg3.mp4'),
(5, '데이터 구조 기초', '리스트, 배열, 맵 등의 기본 개념을 학습합니다.', 6, NOW(), NOW(), 'pg4.mp4'),
(5, '객체지향 프로그래밍', '객체지향 프로그래밍의 기본 개념을 학습합니다.', 6, NOW(), NOW(), 'pg5.mp4');


-- enrollments 테이블에 데이터 삽입
INSERT INTO enrollments (course_id, student_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 1);

-- reservations 테이블에 데이터 삽입
INSERT INTO reservations (course_id, student_id) VALUES
(1, 2),
(2, 2),
(3, 1),
(4, 1),
(5, 2);

-- questions 테이블에 데이터 삽입
INSERT INTO questions (course_id, question_text, answer) VALUES
(1, '2 + 2는 무엇인가요?', '4'),
(1, '3 x 3은 무엇인가요?', '9'),
(2, '미분의 정의는 무엇인가요?', '변화율'),
(3, '뉴턴의 제1법칙은 무엇인가요?', '관성의 법칙'),
(5, '변수란 무엇인가요?', '데이터를 저장하기 위한 공간');

SELECT * FROM users;
SELECT * FROM courses;
SELECT * FROM course_info;
SELECT * FROM enrollments;
SELECT * FROM reservations;
SELECT * FROM questions;

COMMIT;

SELECT e FROM Enrollments e WHERE e.student_id = 1 AND e.course_id = ?2;

DELETE FROM courses WHERE id=13;
