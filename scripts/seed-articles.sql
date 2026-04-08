USE openclaw;

INSERT INTO articles (title, content, summary, author_id, status) 
VALUES ('测试文章', '这是测试内容', '测试摘要', 5, 'PUBLISHED');

SELECT id, title, status FROM articles;
