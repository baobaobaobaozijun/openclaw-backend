-- Plan-000 验证脚本：验证计划表结构
-- 验证表结构
DESC plan_progress;
DESC step_execution;
DESC session_lock;

-- 查询当前计划进度
SELECT * FROM plan_progress;