package com.baozipu.scheduler;

import com.baozipu.service.LogParser;
import com.baozipu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogAutoSubmitScheduler {
    
    private static final Logger log = LoggerFactory.getLogger(LogAutoSubmitScheduler.class);
    
    @Autowired
    private LogParser logParser;
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 每日 18:00 自动执行
     * Cron: 秒 分 时 日 月 周
     */
    @Scheduled(cron = "0 0 18 * * ?")
    public void autoSubmitDailyLogs() {
        log.info("开始执行工作日志自动提交任务...");
        try {
            var entries = logParser.parseAllAgents();
            LocalDateTime now = LocalDateTime.now();
            String dateStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            // 按 Agent 分组
            Map<String, StringBuilder> agentLogs = new HashMap<>();
            for (var entry : entries) {
                String agent = extractAgent(entry.getFileName());
                if (!agentLogs.containsKey(agent)) {
                    agentLogs.put(agent, new StringBuilder());
                }
                agentLogs.get(agent).append("## ")
                    .append(entry.getFileName())
                    .append("\n\n")
                    .append(entry.getContent())
                    .append("\n\n");
            }
            
            // 为每个 Agent 创建文章
            for (Map.Entry<String, StringBuilder> entry : agentLogs.entrySet()) {
                String title = "工作日报 - " + entry.getKey() + " - " + dateStr;
                articleService.create(title, entry.getValue().toString(), "work-log");
                log.info("已创建文章：{}", title);
            }
            
            log.info("工作日志自动提交完成，共处理 {} 个 Agent", agentLogs.size());
        } catch (Exception e) {
            log.error("工作日志自动提交失败", e);
        }
    }
    
    private String extractAgent(String fileName) {
        if (fileName.contains("jiangrou")) return "酱肉";
        if (fileName.contains("dousha")) return "豆沙";
        if (fileName.contains("suancai")) return "酸菜";
        if (fileName.contains("guantang")) return "灌汤";
        return "未知";
    }
}