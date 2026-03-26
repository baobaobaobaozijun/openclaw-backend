-- 初始化测试数据

-- 分类
INSERT IGNORE INTO categories (name, slug, description) VALUES 
('技术', 'tech', '技术文章'),
('生活', 'life', '生活随笔'),
('教程', 'tutorial', '教程指南');

-- 标签
INSERT IGNORE INTO tags (name, slug) VALUES 
('Java', 'java'),
('Vue', 'vue'),
('Spring Boot', 'spring-boot'),
('前端', 'frontend'),
('后端', 'backend');

-- 测试用户（密码: 123456，BCrypt 加密）
INSERT IGNORE INTO users (username, email, password) VALUES 
('admin', 'admin@baozipu.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi');

-- 测试文章
INSERT INTO articles (title, content, summary, author_id, status, view_count) VALUES 
('欢迎来到包子铺博客', '# 欢迎\n\n这是包子铺博客系统的第一篇文章。\n\n## 功能介绍\n\n- 文章发布与管理\n- 分类和标签\n- 用户认证\n- Markdown 支持', '包子铺博客系统正式上线，欢迎体验！', 1, 'PUBLISHED', 100),
('Spring Boot 3 实战指南', '# Spring Boot 3 实战\n\n## 1. 项目初始化\n\n使用 Spring Initializr 创建项目...\n\n## 2. 数据库配置\n\nMyBatis-Plus 集成配置...\n\n## 3. RESTful API\n\n统一响应格式设计...', 'Spring Boot 3 从零开始搭建后端服务', 1, 'PUBLISHED', 56),
('Vue 3 组合式 API 入门', '# Vue 3 Composition API\n\n## setup 函数\n\n```javascript\nimport { ref, onMounted } from \"vue\"\n\nexport default {\n  setup() {\n    const count = ref(0)\n    return { count }\n  }\n}\n```\n\n## 响应式数据\n\n使用 ref 和 reactive...', 'Vue 3 组合式 API 的核心概念和使用方法', 1, 'PUBLISHED', 42),
('Docker 部署最佳实践', '# Docker 部署\n\n## Dockerfile 编写\n\n```dockerfile\nFROM eclipse-temurin:21-jre\nCOPY target/app.jar app.jar\nEXPOSE 8081\nENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n```\n\n## docker-compose\n\n多服务编排...', 'Docker 容器化部署的实用技巧', 1, 'PUBLISHED', 28),
('我的博客搭建之旅', '# 搭建博客\n\n记录从零开始搭建包子铺博客的过程。\n\n从需求分析到架构设计，从代码开发到部署上线，这是一段充满挑战和收获的旅程。', '从零搭建一个完整的博客系统', 1, 'PUBLISHED', 15);
