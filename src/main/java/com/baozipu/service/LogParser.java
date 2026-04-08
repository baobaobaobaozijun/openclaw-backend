package com.baozipu.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * 工作日志解析服务
 * 解析 workinglog 目录下的 markdown 日志文件
 */
@Service
public class LogParser {

    private static final String WORKINGLOG_ROOT = "F:\\openclaw\\agent\\workinglog";
    private static final String[] AGENTS = {"guantang", "jiangrou", "dousha", "suancai"};

    /**
     * 解析指定 Agent 的最新日志
     */
    public Map<String, Object> parseLatestLog(String agentId) throws IOException {
        Path agentDir = Paths.get(WORKINGLOG_ROOT, agentId);
        if (!Files.exists(agentDir)) {
            return Collections.emptyMap();
        }

        // 获取最新日志文件
        Optional<Path> latestLog = Files.list(agentDir)
            .filter(p -> p.toString().endsWith(".md"))
            .max(Comparator.comparingLong(p -> p.toFile().lastModified()));

        if (!latestLog.isPresent()) {
            return Collections.emptyMap();
        }

        String content = Files.readString(latestLog.get());
        return parseContent(content);
    }

    private Map<String, Object> parseContent(String content) {
        Map<String, Object> result = new HashMap<>();
        // 简单解析：提取标题和修改文件列表
        result.put("raw", content);
        result.put("parsedAt", new Date().toString());
        return result;
    }

    /**
     * 获取所有 Agent 的最新日志
     */
    public Map<String, Map<String, Object>> parseAllAgents() throws IOException {
        Map<String, Map<String, Object>> results = new HashMap<>();
        for (String agent : AGENTS) {
            results.put(agent, parseLatestLog(agent));
        }
        return results;
    }
}