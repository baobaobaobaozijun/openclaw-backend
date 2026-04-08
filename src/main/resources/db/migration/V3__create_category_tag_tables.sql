-- 分类表
CREATE TABLE IF NOT EXISTS `categories` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '分类 ID',
    `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
    `slug` VARCHAR(50) NOT NULL COMMENT '别名',
    `description` VARCHAR(200) COMMENT '描述',
    `parent_id` BIGINT DEFAULT NULL COMMENT '父分类 ID',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_slug` (`slug`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表';

-- 标签表
CREATE TABLE IF NOT EXISTS `tags` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '标签 ID',
    `name` VARCHAR(50) NOT NULL COMMENT '标签名称',
    `slug` VARCHAR(50) NOT NULL COMMENT '别名',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_slug` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章标签表';

-- 文章分类关联表
CREATE TABLE IF NOT EXISTS `article_category` (
    `article_id` BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,
    PRIMARY KEY (`article_id`, `category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类关联表';

-- 文章标签关联表
CREATE TABLE IF NOT EXISTS `article_tag` (
    `article_id` BIGINT NOT NULL,
    `tag_id` BIGINT NOT NULL,
    PRIMARY KEY (`article_id`, `tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章标签关联表';