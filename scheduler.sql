-- 스키마 생성
CREATE SCHEMA scheduler_develop;

-- 스키마 사용
USE scheduler_develop;

-- 테이블 생성
CREATE TABLE user (
    username VARCHAR(20) PRIMARY KEY COMMENT '유저 ID',
    name VARCHAR(20) NOT NULL COMMENT '이름',
    email VARCHAR(254) UNIQUE COMMENT '이메일',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 날짜/시간',
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜/시간'
);

CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '스케줄 ID',
    title VARCHAR(100) COMMENT '제목',
    content TEXT COMMENT '내용',
    username VARCHAR(20) COMMENT '작성자',
    FOREIGN KEY (username) REFERENCES user(username),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 날짜/시간',
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜/시간'
);

-- 테이블 삭제
DROP TABLE schedule;

DROP TABLE user;