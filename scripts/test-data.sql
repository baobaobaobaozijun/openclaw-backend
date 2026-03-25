-- Test Data (aligned with Entity fields)
-- Fixed: 2026-03-24 by PM

INSERT INTO users (username, password, email, phone, avatar, role) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'admin@openclaw.com', '13800138001', 'https://via.placeholder.com/150', 'ADMIN'),
('alice', '$2a$10$rOzJmTMQDY1a.MU6yHknde4V9HqQV0WVh.K.qH7.wPB8h.l6.bjgC', 'alice@example.com', '13800138002', 'https://via.placeholder.com/150', 'USER'),
('bob', '$2a$10$rOzJmTMQDY1a.MU6yHknde4V9HqQV0WVh.K.qH7.wPB8h.l6.bjgC', 'bob@example.com', '13800138003', 'https://via.placeholder.com/150', 'USER');

INSERT INTO categories (id, name, slug, description, parent_id) VALUES
(1, 'Tech', 'tech', 'Technology articles', NULL),
(2, 'Life', 'life', 'Life notes', NULL),
(3, 'Essay', 'essay', 'Personal essays', NULL),
(4, 'Tutorial', 'tutorial', 'Tutorials', NULL),
(5, 'Notice', 'notice', 'System notices', NULL),
(6, 'Java', 'java', 'Java tech', 1),
(7, 'Frontend', 'frontend', 'Frontend tech', 1);

INSERT INTO tags (name, slug) VALUES
('Java', 'java'), ('Spring Boot', 'spring-boot'), ('Vue', 'vue'), ('Frontend', 'frontend'),
('Backend', 'backend'), ('Docker', 'docker'), ('Redis', 'redis'), ('MySQL', 'mysql');

INSERT INTO articles (title, content, summary, author_id, status, access_level, view_count) VALUES
('Spring Boot 3.2 Features', '# Spring Boot 3.2\n\nVirtual threads support.\n\n```java\nExecutors.newVirtualThreadPerTaskExecutor();\n```', 'New features in Spring Boot 3.2', 1, 'PUBLISHED', 0, 128),
('Vue 3 Composition API', '# Vue 3\n\n```typescript\nconst count = ref(0)\n```', 'Vue 3 composition API guide', 2, 'PUBLISHED', 0, 256),
('Docker Best Practices', '# Docker\n\nMulti-stage builds.', 'Docker deployment tips', 1, 'PUBLISHED', 0, 89),
('My Coding Journey', '# My Journey\n\nFirst Hello World.', 'Learning to code', 3, 'PUBLISHED', 0, 45),
('Redis Cache (Draft)', '# Redis Cache\n\nCache aside pattern.', 'Redis caching', 1, 'DRAFT', 0, 0),
('Archived Deploy Doc', 'Archived. See new guide.', 'Old deploy doc', 1, 'DRAFT', 0, 12);

INSERT INTO article_tags (article_id, tag_id) VALUES
(1, 1), (1, 2), (2, 3), (2, 4), (3, 6), (4, 1), (4, 5), (5, 7), (5, 5), (6, 6);
