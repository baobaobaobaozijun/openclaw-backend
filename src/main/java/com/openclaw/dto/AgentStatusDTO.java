package com.openclaw.dto;

import java.time.LocalDateTime;

public class AgentStatusDTO {
    private String agentId;
    private String status;
    private LocalDateTime lastActive;
    private String currentTask;

    public AgentStatusDTO() {
    }

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