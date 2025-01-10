-- 데이터베이스 생성
CREATE DATABASE mymanager;
USE mymanager;

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
    updated_at TIMESTAMP NULL
);

-- 프로젝트 테이블
CREATE TABLE projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL
);

-- 태스크 테이블
CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    project_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status ENUM('TODO', 'IN_PROGRESS', 'DONE') DEFAULT 'TODO',
    due_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

-- 메모 포스트 테이블 (트렐로 스타일)
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    position INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL
);

-- 보드 테이블
CREATE TABLE boards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- 1. users 테이블 더미 데이터
INSERT INTO users (username, password, email, role)
VALUES 
('hong', '$2b$10$Qh.0hUg/WyGpRkMR3eHbFJfjy4lszOslwC1HtOgpJkN3eGNYEoO3S', 'honggildong@example.com', 'USER'),
('kim', '$2b$10$XjbWjpJHZd6bqOgfWbFzyS7ISklnEw5zPRAm8A0d8dOmccQQpkTuW', 'kimyujin@example.com', 'USER'),
('admin', '$2b$10$gjPQwFft67HLj/gbjPevuXzjmHHW0Zn5c/R6xjRYhD6qJtJHME.Vu', 'admin@example.com', 'ADMIN'),
('lee', '$2b$10$2EOBgG8UKGbP02HjZoXZP.PPXLcP07/MX2Ypk1H6fl0XwePMsNREO', 'leesujin@example.com', 'USER'),
('park', '$2b$10$z.H0NezGGhXntzEoSi6nAOOjbyZxIuAfYUk3CR80zYDa4sJt9NYKu', 'parkminsu@example.com', 'USER');

-- Original: 1234 -> Hashed: $2b$10$Qh.0hUg/WyGpRkMR3eHbFJfjy4lszOslwC1HtOgpJkN3eGNYEoO3S
-- Original: 4567 -> Hashed: $2b$10$XjbWjpJHZd6bqOgfWbFzyS7ISklnEw5zPRAm8A0d8dOmccQQpkTuW
-- Original: 1234 -> Hashed: $2b$10$gjPQwFft67HLj/gbjPevuXzjmHHW0Zn5c/R6xjRYhD6qJtJHME.Vu
-- Original: 1111 -> Hashed: $2b$10$2EOBgG8UKGbP02HjZoXZP.PPXLcP07/MX2Ypk1H6fl0XwePMsNREO
-- Original: 222  -> Hashed: $2b$10$z.H0NezGGhXntzEoSi6nAOOjbyZxIuAfYUk3CR80zYDa4sJt9NYKu

-- 2. notices 테이블 더미 데이터
INSERT INTO notices (title, content)
VALUES 
('시스템 점검 안내', '시스템 점검은 토요일 오전 2시부터 5시까지 진행됩니다.'),
('새로운 기능 출시', '새로운 기능이 곧 출시됩니다. 기대해주세요!'),
('휴무일 안내', '국경일에는 휴무입니다.'),
('보안 업데이트', '보안을 강화하기 위해 비밀번호를 업데이트해 주세요.'),
('커뮤니티 행사', '매년 커뮤니티 모임에 참여하세요!');

-- 3. projects 테이블 더미 데이터
INSERT INTO projects (name, description)
VALUES 
('웹사이트 리디자인', '웹사이트의 전체적인 디자인을 새롭게 개편합니다.'),
('모바일 앱 개발', 'iOS와 안드로이드 앱을 새로 개발합니다.'),
('마케팅 캠페인', '소셜 미디어를 통한 마케팅 캠페인을 시작합니다.'),
('제품 출시', '새로운 제품 라인을 출시하기 위한 준비를 합니다.'),
('직원 온보딩', '새로운 직원들을 위한 교육 자료를 만듭니다.');

-- 4. tasks 테이블 더미 데이터
INSERT INTO tasks (project_id, name, description, status, due_date)
VALUES 
(1, '홈페이지 디자인', '홈페이지 디자인을 새롭게 개발합니다.', 'IN_PROGRESS', '2024-11-30'),
(1, '호스팅 설정', '새로운 호스팅 환경을 설정합니다.', 'TODO', '2024-12-01'),
(2, '로그인 기능 개발', '로그인 및 회원가입 기능을 구현합니다.', 'DONE', '2024-10-25'),
(3, '광고 디자인', '소셜 미디어 광고를 디자인합니다.', 'TODO', '2024-11-15'),
(4, '제품 출시 행사 계획', '제품 출시 행사를 위한 장소와 일정을 계획합니다.', 'IN_PROGRESS', '2024-12-10');

-- 5. posts 테이블 더미 데이터
INSERT INTO posts (board_id, title, content, position)
VALUES 
(1, '메모 스타일 공지', '프로젝트 가이드라인을 업데이트해 주세요.', 1),
(1, '작업 후속 조치', '홈페이지 디자인 진행 상황을 확인하세요.', 2),
(2, '고객 피드백', '최근 회의에서 받은 피드백을 반영해 주세요.', 3),
(2, '내부 리뷰', '현재 진행 중인 작업에 대한 내부 리뷰를 진행합니다.', 4),
(3, '아이디어 브레인스토밍', '새로운 캠페인 전략에 대해 논의해 주세요.', 5);

-- 6. boards 테이블 더미 데이터
INSERT INTO boards (name)
VALUES 
('할 일'),
('진행 중'),
('완료'),
('보관'),
('백로그');

COMMIT;

ALTER TABLE users MODIFY email VARCHAR(255) DEFAULT 'example@example.com';

SELECT * FROM users;

SELECT * FROM projects;

SELECT * FROM tasks;

SELECT * FROM boards;

SELECT * FROM posts;

SHOW TABLES;

DROP TABLE board;


select b1_0.id,b1_0.created_at,b1_0.name,p1_0.board_id,p1_0.id,p1_0.content,p1_0.created_at,p1_0.position,p1_0.title,p1_0.updated_at from boards b1_0 left join posts p1_0 on b1_0.id=p1_0.board_id