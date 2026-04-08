package com.baozipu.scheduler;

import com.baozipu.service.LogParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * 工作日志自动提交调度器
 * 每日 18:00 自动执行，将工作日志提交为博客文章
 */
@Component
public class LogAutoSubmitScheduler {

    private static final Logger log = LoggerFactory.getLogger(LogAutoSubmitScheduler.class);

    @Autowired
    private LogParser logParser;

    /**
     * 每日 18:00 执行
     * Cron: 秒 分 时 日 月 周
     */
    @Scheduled(cron = "0 0 18 * * ?")
    public void autoSubmitLogs() {
        log.info("开始执行工作日志自动提交任务...");
        try {
            Map<String, Map<String, Object>> allLogs = logParser.parseAllAgents();
            for (Map.Entry<String, Map<String, Object>> entry : allLogs.entrySet()) {
                String agent = entry.getKey();
                Map<String, Object> logData = entry.getValue();
                if (!logData.isEmpty()) {
                    log.info("解析到 {} 的日志：{}", agent, logData.get("parsedAt"));
                    // TODO: 调用 ArticleService 创建文章
                }
            }
            log.info("工作日志自动提交完成");
        } catch (Exception e) {
            log.error("工作日志自动提交失败", e);
        }
    }
}