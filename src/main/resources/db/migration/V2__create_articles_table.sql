CREATE TABLE articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '文章 ID',
    title VARCHAR(200) NOT NULL COMMENT '文章标题',
    content LONGTEXT NOT NULL COMMENT '文章内容（Markdown 格式）',
    summary VARCHAR(500) COMMENT '文章摘要',
    author_id BIGINT NOT NULL COMMENT '作者 ID（关联 users.id）',
    category_id BIGINT COMMENT '分类 ID（关联 categories.id）',
    status TINYINT DEFAULT 0 COMMENT '状态：0=草稿 1=已发布 2=已删除',
    view_count INT DEFAULT 0 COMMENT '阅读次数',
    like_count INT DEFAULT 0 COMMENT '点赞次数',
    comment_count INT DEFAULT 0 COMMENT '评论次数',
    published_at DATETIME COMMENT '发布时间',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_author (author_id),
    INDEX idx_category (category_id),
    INDEX idx_status (status),
    INDEX idx_published_at (published_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';