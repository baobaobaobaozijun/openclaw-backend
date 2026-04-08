package com.baozipu.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Agent 状态 API
 */
@RestController
@RequestMapping("/api/agent")
public class AgentStatusController {

    /**
     * 获取所有 Agent 状态
     */
    @GetMapping("/status")
    public List<Map<String, Object>> getAgentStatus() {
        List<Map<String, Object>> agents = new ArrayList<>();
        
        // 酱肉
        Map<String, Object> jiangrou = new HashMap<>();
        jiangrou.put("id", "jiangrou");
        jiangrou.put("name", "酱肉 🍖");
        jiangrou.put("status", "active");
        jiangrou.put("lastActivity", new Date().toString());
        jiangrou.put("currentTask", "Plan-003 R3 自动提交逻辑");
        agents.add(jiangrou);
        
        // 豆沙
        Map<String, Object> dousha = new HashMap<>();
        dousha.put("id", "dousha");
        dousha.put("name", "豆沙 🍡");
        dousha.put("status", "active");
        dousha.put("lastActivity", new Date().toString());
        dousha.put("currentTask", "Plan-003 R5 前端组件");
        agents.add(dousha);
        
        // 酸菜
        Map<String, Object> suancai = new HashMap<>();
        suancai.put("id", "suancai");
        suancai.put("name", "酸菜 🥬");
        suancai.put("status", "active");
        suancai.put("lastActivity", new Date().toString());
        suancai.put("currentTask", "Plan-003 R5 健康检查脚本");
        agents.add(suancai);
        
        return agents;
    }
}