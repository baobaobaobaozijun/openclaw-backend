CREATE TABLE IF NOT EXISTS articles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '文章ID，主键',
    title VARCHAR(255) NOT NULL COMMENT '文章标题',
    content TEXT NOT NULL COMMENT '文章原始内容（Markdown格式）',
    content_html TEXT NOT NULL COMMENT '文章HTML内容',
    author_id BIGINT NOT NULL COMMENT '作者ID，关联users表',
    status VARCHAR(50) NOT NULL DEFAULT 'draft' COMMENT '文章状态：draft, published, archived',
    access_level VARCHAR(50) NOT NULL DEFAULT 'public' COMMENT '访问级别：public, private, internal',
    view_count INT DEFAULT 0 COMMENT '浏览次数',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 外键约束
    CONSTRAINT fk_articles_author FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE,
    
    -- 索引
    INDEX idx_author (author_id),
    INDEX idx_status (status),
    INDEX idx_created_at (created_at),
    INDEX idx_access_level (access_level),
    INDEX idx_updated_at (updated_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';