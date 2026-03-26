package com.baozipu.controller;

import com.baozipu.entity.AgentStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/agent")
public class AgentStatusController {
    
    @GetMapping("/status")
    public List<AgentStatus> getStatus() {
        List<AgentStatus> statuses = new ArrayList<>();
        statuses.add(new AgentStatus("灌汤", "online", LocalDateTime.now(), "PM 调度中"));
        statuses.add(new AgentStatus("酱肉", "online", LocalDateTime.now(), "Plan-03 开发中"));
        statuses.add(new AgentStatus("豆沙", "idle", LocalDateTime.now(), "等待任务"));
        statuses.add(new AgentStatus("酸菜", "idle", LocalDateTime.now(), "等待任务"));
        return statuses;
    }
}