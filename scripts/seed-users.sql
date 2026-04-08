USE openclaw;

DELETE FROM articles;
DELETE FROM users;

INSERT INTO users (username, password, email, role) 
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@example.com', 'ADMIN');

SELECT id FROM users WHERE username = 'admin';
