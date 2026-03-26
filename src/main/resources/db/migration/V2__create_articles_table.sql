CREATE TABLE articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL COMMENT '文章标题',
    content LONGTEXT NOT NULL COMMENT '文章内容',
    author_id BIGINT NOT NULL COMMENT '作者 ID',
    status TINYINT DEFAULT 0 COMMENT '状态：0-草稿，1-已发布',
    view_count INT DEFAULT 0 COMMENT '浏览量',
    published_at DATETIME NULL COMMENT '发布时间',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_author_id (author_id),
    INDEX idx_status (status)
);