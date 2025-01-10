-- 데이터베이스 생성
CREATE DATABASE shop;
USE shop;

SHOW TABLES;

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

-- 상품 테이블
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    img VARCHAR(255)
);

-- 주문 테이블
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status ENUM('PENDING', 'PAID', 'CANCELLED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 결제 테이블
CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    payment_method ENUM('CREDIT_CARD', 'BANK_TRANSFER', 'PAYPAL') NOT NULL,
    payment_amount DECIMAL(10, 2) NOT NULL,
    payment_status ENUM('SUCCESS', 'FAIL') NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

-- 판매 관리 테이블
CREATE TABLE sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- 회원 테이블(users) 더미 데이터
INSERT INTO users (username, password, email, role) VALUES
('user1', '$2b$10$XjbWjpJHZd6bqOgfWbFzyS7ISklnEw5zPRAm8A0d8dOmccQQpkTuW', 'user1@example.com', 'USER'),
('user2', '$2b$10$2EOBgG8UKGbP02HjZoXZP.PPXLcP07/MX2Ypk1H6fl0XwePMsNREO', 'user2@example.com', 'USER'),
('user3', '$2b$10$XjbWjpJHZd6bqOgfWbFzyS7ISklnEw5zPRAm8A0d8dOmccQQpkTuW', 'user3@example.com', 'USER'),
('admin1', '$2a$10$ii8G8c5Gn4qulRfTExhVguEKeFkDlGJRJ5bhi9IPE9bEyx4ma.yhS', 'admin1@example.com', 'ADMIN'),
('admin2', '$2a$10$ii8G8c5Gn4qulRfTExhVguEKeFkDlGJRJ5bhi9IPE9bEyx4ma.yhS', 'admin2@example.com', 'ADMIN');

-- Original: 4567 -> Hashed: $2b$10$XjbWjpJHZd6bqOgfWbFzyS7ISklnEw5zPRAm8A0d8dOmccQQpkTuW
-- Original: 1234 -> Hashed: $2b$10$gjPQwFft67HLj/gbjPevuXzjmHHW0Zn5c/R6xjRYhD6qJtJHME.Vu
-- Original: 1111 -> Hashed: $2b$10$2EOBgG8UKGbP02HjZoXZP.PPXLcP07/MX2Ypk1H6fl0XwePMsNREO

-- 공지사항 테이블(notices) 더미 데이터
INSERT INTO notices (title, content) VALUES
('환영합니다!', '저희 서비스를 이용해 주셔서 감사합니다.'),
('신규 기능 안내', '이번 주 추가된 새로운 기능을 확인해 보세요.'),
('정기 점검 공지', '이번 달 20일에 정기 점검이 예정되어 있습니다.'),
('이용 가이드', '서비스 이용을 위한 가이드를 참고해 주세요.'),
('휴무 안내', '12월 25일 공휴일에는 운영하지 않습니다.');

-- 상품 테이블(products) 더미 데이터
INSERT INTO products (name, description, price, stock) VALUES
('상품 A', '상품 A의 설명입니다.', 19900, 100),
('상품 B', '상품 B의 설명입니다.', 29900, 50),
('상품 C', '상품 C의 설명입니다.', 15900, 150),
('상품 D', '상품 D의 설명입니다.', 49900, 30),
('상품 E', '상품 E의 설명입니다.', 5900, 200);

-- 주문 테이블(orders) 더미 데이터
INSERT INTO orders (user_id, total_amount, status) VALUES
(1, 59700, 'PENDING'),
(2, 29900, 'PAID'),
(3, 49900, 'CANCELLED'),
(1, 15900, 'PAID'),
(2, 119700, 'PENDING');

-- 결제 테이블(payments) 더미 데이터
INSERT INTO payments (order_id, payment_method, payment_amount, payment_status) VALUES
(1, 'CREDIT_CARD', 59700, 'SUCCESS'),
(2, 'PAYPAL', 29900, 'SUCCESS'),
(3, 'BANK_TRANSFER', 49900, 'FAIL'),
(4, 'CREDIT_CARD', 15900, 'SUCCESS'),
(5, 'BANK_TRANSFER', 119700, 'SUCCESS');

-- 판매 관리 테이블(sales) 더미 데이터
INSERT INTO sales (product_id, quantity, total_price) VALUES
(1, 2, 39800),
(2, 1, 29900),
(3, 3, 47900),
(4, 1, 49900),
(5, 5, 29500);

COMMIT;

SELECT * FROM users;

SELECT * FROM cart;

SELECT * FROM products;