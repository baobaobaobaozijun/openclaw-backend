-- 创建文章表
CREATE TABLE IF NOT EXISTS `articles` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '文章 ID',
    `title` VARCHAR(200) NOT NULL COMMENT '文章标题',
    `content` LONGTEXT NOT NULL COMMENT '文章内容',
    `summary` VARCHAR(500) NULL COMMENT '文章摘要',
    `author_id` BIGINT NOT NULL COMMENT '作者 ID',
    `status` VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态：DRAFT-草稿，PUBLISHED-已发布',
    `access_level` INT DEFAULT 0 COMMENT '访问级别：0-公开，1-付费，2-会员',
    `view_count` INT DEFAULT 0 COMMENT '浏览量',
    `published_at` DATETIME NULL COMMENT '发布时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_author_id` (`author_id`),
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';
