-- ============================================================
-- OpenClaw Blog System - Database Schema
-- Version: 1.0.0
-- Author: 酱肉 (Jiangrou) + 灌汤 (PM Review)
-- Created: 2026-03-24
-- Database: MySQL 5.7+ / 8.0+
-- Charset: utf8mb4
-- ============================================================

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `openclaw`
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE `openclaw`;

-- ============================================================
-- 1. 用户表 (users)
-- ============================================================
CREATE TABLE IF NOT EXISTS `users` (
  `id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username`   VARCHAR(50)  NOT NULL                COMMENT '用户名',
  `password`   VARCHAR(255) NOT NULL                COMMENT '密码（BCrypt加密）',
  `email`      VARCHAR(100) DEFAULT NULL            COMMENT '邮箱',
  `phone`      VARCHAR(20)  DEFAULT NULL            COMMENT '手机号',
  `avatar`     VARCHAR(500) DEFAULT NULL            COMMENT '头像URL',
  `role`       VARCHAR(20)  NOT NULL DEFAULT 'USER' COMMENT '角色：USER-普通用户，ADMIN-管理员',
  `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted`    TINYINT      NOT NULL DEFAULT 0      COMMENT '逻辑删除：0-正常，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_email` (`email`),
  KEY `idx_role` (`role`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ============================================================
-- 2. 分类表 (categories)
-- ============================================================
CREATE TABLE IF NOT EXISTS `categories` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name`        VARCHAR(100) NOT NULL                COMMENT '分类名称',
  `slug`        VARCHAR(100) NOT NULL                COMMENT '分类slug（URL友好）',
  `description` VARCHAR(500) DEFAULT NULL            COMMENT '分类描述',
  `parent_id`   BIGINT       DEFAULT NULL            COMMENT '父分类ID',
  `sort_order`  INT          NOT NULL DEFAULT 0      COMMENT '排序顺序',
  `status`      TINYINT      NOT NULL DEFAULT 1      COMMENT '状态：0-禁用，1-启用',
  `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted`     TINYINT      NOT NULL DEFAULT 0      COMMENT '逻辑删除：0-正常，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_slug` (`slug`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

-- ============================================================
-- 3. 标签表 (tags)
-- ============================================================
CREATE TABLE IF NOT EXISTS `tags` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name`        VARCHAR(50)  NOT NULL                COMMENT '标签名称',
  `slug`        VARCHAR(100) NOT NULL                COMMENT '标签slug（URL友好）',
  `usage_count` INT          NOT NULL DEFAULT 0      COMMENT '使用次数',
  `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted`     TINYINT      NOT NULL DEFAULT 0      COMMENT '逻辑删除：0-正常，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  UNIQUE KEY `uk_slug` (`slug`),
  KEY `idx_usage_count` (`usage_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- ============================================================
-- 4. 文章表 (articles)
-- ============================================================
CREATE TABLE IF NOT EXISTS `articles` (
  `id`           BIGINT        NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title`        VARCHAR(200)  NOT NULL                COMMENT '文章标题',
  `content`      LONGTEXT      NOT NULL                COMMENT '文章内容（Markdown）',
  `summary`      VARCHAR(500)  DEFAULT NULL            COMMENT '文章摘要',
  `author_id`    BIGINT        NOT NULL                COMMENT '作者ID',
  `category_id`  BIGINT        DEFAULT NULL            COMMENT '分类ID',
  `status`       VARCHAR(20)   NOT NULL DEFAULT 'DRAFT' COMMENT '状态：DRAFT-草稿，PUBLISHED-已发布',
  `access_level` TINYINT       NOT NULL DEFAULT 0      COMMENT '访问级别：0-公开，1-登录可见，2-VIP可见',
  `view_count`   INT           NOT NULL DEFAULT 0      COMMENT '浏览次数',
  `created_at`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`      TINYINT       NOT NULL DEFAULT 0      COMMENT '逻辑删除：0-正常，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_author_id` (`author_id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`),
  KEY `idx_view_count` (`view_count`),
  CONSTRAINT `fk_article_author` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- ============================================================
-- 5. 文章-标签关联表 (article_tags)
-- ============================================================
CREATE TABLE IF NOT EXISTS `article_tags` (
  `id`         BIGINT   NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `article_id` BIGINT   NOT NULL                COMMENT '文章ID',
  `tag_id`     BIGINT   NOT NULL                COMMENT '标签ID',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_article_tag` (`article_id`, `tag_id`),
  KEY `idx_tag_id` (`tag_id`),
  CONSTRAINT `fk_at_article` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_at_tag` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章-标签关联表';

-- ============================================================
-- 初始数据
-- ============================================================

-- 默认管理员（密码: admin123，BCrypt 加密）
INSERT INTO `users` (`username`, `password`, `email`, `role`) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'admin@openclaw.com', 'ADMIN')
ON DUPLICATE KEY UPDATE `username` = VALUES(`username`);

-- 默认分类
INSERT INTO `categories` (`name`, `slug`, `description`, `sort_order`) VALUES
('未分类', 'uncategorized', '默认分类', 0),
('技术', 'tech', '技术相关文章', 1),
('生活', 'life', '生活随笔', 2)
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

-- 默认标签
INSERT INTO `tags` (`name`, `slug`) VALUES
('Java', 'java'),
('Spring Boot', 'spring-boot'),
('Vue', 'vue'),
('TypeScript', 'typescript')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);
