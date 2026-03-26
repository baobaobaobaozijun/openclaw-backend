package com.baozipu.entity;

import java.time.LocalDateTime;

public class AgentStatus {
    private String name;
    private String status; // online, idle, offline
    private LocalDateTime lastActivity;
    private String currentTask;
    
    public AgentStatus() {}
    
    public AgentStatus(String name, String status, LocalDateTime lastActivity, String currentTask) {
        this.name = name;
        this.status = status;
        this.lastActivity = lastActivity;
        this.currentTask = currentTask;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getLastActivity() { return lastActivity; }
    public void setLastActivity(LocalDateTime lastActivity) { this.lastActivity = lastActivity; }
    public String getCurrentTask() { return currentTask; }
    public void setCurrentTask(String currentTask) { this.currentTask = currentTask; }
}