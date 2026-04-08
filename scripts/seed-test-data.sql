USE openclaw;

DELETE FROM articles;
DELETE FROM users;

INSERT INTO users (username, password, email, role) 
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@example.com', 'ADMIN');

INSERT INTO articles (title, content, summary, author_id, status, access_level, view_count) 
VALUES ('测试文章', '这是测试内容', '测试摘要', 1, 'PUBLISHED', 0, 0);

SELECT id, username, role FROM users;
SELECT id, title, status FROM articles;
