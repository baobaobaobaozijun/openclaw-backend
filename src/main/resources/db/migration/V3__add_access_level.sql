-- 任务 1: users 表添加 access_level 字段
ALTER TABLE users ADD COLUMN access_level INT DEFAULT 0 COMMENT '用户等级：0=普通，1=VIP';