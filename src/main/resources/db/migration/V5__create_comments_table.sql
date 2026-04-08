-- V5__create_comments_table.sql
-- 创建评论表

CREATE TABLE IF NOT EXISTS comments (
  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '评论 ID',
  article_id BIGINT NOT NULL COMMENT '文章 ID',
  user_id BIGINT NOT NULL COMMENT '用户 ID',
  content TEXT NOT NULL COMMENT '评论内容',
  parent_id BIGINT DEFAULT NULL COMMENT '父评论 ID（用于回复）',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标志',
  
  INDEX idx_article_id (article_id),
  INDEX idx_user_id (user_id),
  INDEX idx_parent_id (parent_id),
  CONSTRAINT fk_comments_article FOREIGN KEY (article_id) REFERENCES articles(id),
  CONSTRAINT fk_comments_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';
