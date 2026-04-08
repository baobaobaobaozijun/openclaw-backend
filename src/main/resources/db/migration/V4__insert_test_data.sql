-- V4__insert_test_data.sql
-- 插入测试数据

-- 测试用户（密码均为：Test123456）
-- BCrypt 哈希值（salt rounds=10）
INSERT INTO users (username, password, email, phone, role, access_level, status) VALUES
('testuser', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'test@example.com', '13800138001', 'USER', 0, 'ACTIVE'),
('vipuser', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'vip@example.com', '13800138002', 'VIP', 1, 'ACTIVE'),
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@example.com', '13800138000', 'ADMIN', 1, 'ACTIVE');

-- 测试分类
INSERT INTO categories (name, slug, description) VALUES
('技术文章', 'tech', '技术相关的文章'),
('生活随笔', 'life', '生活感悟和随笔'),
('教程', 'tutorial', '各类教程和指南');

-- 测试标签
INSERT INTO tags (name, slug) VALUES
('Java', 'java'),
('Vue', 'vue'),
('OpenClaw', 'openclaw'),
('产品经理', 'pm'),
('前端开发', 'frontend');

-- 测试文章
INSERT INTO articles (title, slug, content, author_id, category_id, status, view_count) VALUES
('OpenClaw 架构设计详解', 'openclaw-architecture', '# OpenClaw 架构设计\n\nOpenClaw 是一个轻量级的 Agent 协作框架...', 1, 1, 'PUBLISHED', 100),
('Vue 3 组合式 API 入门', 'vue3-composition-api', '# Vue 3 组合式 API\n\nVue 3 引入了 Composition API...', 1, 1, 'PUBLISHED', 50),
('产品经理的自我修养', 'pm-self-improvement', '# 产品经理的自我修养\n\n作为一名产品经理...', 1, 3, 'PUBLISHED', 75);

-- 测试评论
INSERT INTO comments (article_id, user_id, content) VALUES
(1, 2, '写得很好，学习了！'),
(1, 3, 'OpenClaw 确实很强大，推荐大家使用。'),
(2, 1, 'Vue 3 的 Composition API 确实更灵活。');
