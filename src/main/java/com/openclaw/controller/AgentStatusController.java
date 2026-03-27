package com.openclaw.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Agent 状态 API 控制器
 * 提供 Agent 工作状态的查询接口
 */
@RestController
@RequestMapping("/api/agents")
public class AgentStatusController {

    /**
     * Agent 状态 DTO
     */
    public static class AgentStatusDTO {
        private String agentId;
        private String status;
        private LocalDateTime lastActive;
        private String currentTask;

        public AgentStatusDTO() {}

        public AgentStatusDTO(String agentId, String status, LocalDateTime lastActive, String currentTask) {
            this.agentId = agentId;
            this.status = status;
            this.lastActive = lastActive;
            this.currentTask = currentTask;
        }

        // Getters and Setters
        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public LocalDateTime getLastActive() {
            return lastActive;
        }

        public void setLastActive(LocalDateTime lastActive) {
            this.lastActive = lastActive;
        }

        public String getCurrentTask() {
            return currentTask;
        }

        public void setCurrentTask(String currentTask) {
            this.currentTask = currentTask;
        }
    }

    /**
     * 获取所有 Agent 状态
     * @return Agent 状态列表
     */
    @GetMapping("/status")
    public List<AgentStatusDTO> getAgentStatus() {
        // 返回 mock 数据
        return Arrays.asList(
            new AgentStatusDTO("agent-001", "online", LocalDateTime.now(), "处理用户请求"),
            new AgentStatusDTO("agent-002", "online", LocalDateTime.now().minusMinutes(5), "执行定时任务"),
            new AgentStatusDTO("agent-003", "offline", LocalDateTime.now().minusHours(1), "离线"),
            new AgentStatusDTO("agent-004", "busy", LocalDateTime.now(), "处理复杂计算"),
            new AgentStatusDTO("jiangrou", "online", LocalDateTime.now(), "后端开发任务")
        );
    }
}