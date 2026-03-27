-- Plan-000 到 Plan-005 的初始化数据
INSERT INTO plan_progress (plan_id, plan_name, status, current_step, total_steps, created_at, updated_at) VALUES
('Plan-000', '数据库表验证 + 初始化', 'RUNNING', 1, 5, NOW(), NOW()),
('Plan-001', '用户认证系统开发', 'PENDING', 0, 10, NOW(), NOW()),
('Plan-002', '博客文章管理API', 'PAUSED', 0, 8, NOW(), NOW()),
('Plan-003', '评论系统开发', 'PENDING', 0, 7, NOW(), NOW()),
('Plan-004', '搜索功能实现', 'PENDING', 0, 6, NOW(), NOW()),
('Plan-005', '报表统计功能', 'PENDING', 0, 9, NOW(), NOW());

-- 插入对应的步骤执行记录
INSERT INTO step_execution (step_id, plan_id, step_name, status, execution_order, started_at, completed_at) VALUES
('Step-000-1', 'Plan-000', '数据库表验证', 'COMPLETED', 1, NOW(), NOW()),
('Step-000-2', 'Plan-000', '初始化数据', 'RUNNING', 2, NOW(), NULL);