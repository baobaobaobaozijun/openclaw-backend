package com.openclaw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GitAutoCommitService {

    @Autowired
    private WorklogParserService worklogParserService;

    /**
     * 自动提交指定 Agent 的修改
     *
     * @param agentId       代理ID
     * @param commitMessage 提交信息
     */
    public void autoCommit(String agentId, String commitMessage) {
        try {
            // 解析工作日志，获取修改的文件
            List<String> modifiedFiles = worklogParserService.parseModifiedFiles(agentId);
            
            // 过滤出Java文件
            List<String> javaFiles = worklogParserService.filterJavaFiles(modifiedFiles);

            if (javaFiles.isEmpty()) {
                System.out.println("没有发现修改的Java文件，跳过提交");
                return;
            }

            // 执行Git操作
            executeGitAdd(javaFiles);
            executeGitCommit(commitMessage);
            executeGitPush();

            System.out.println("自动提交完成");
        } catch (Exception e) {
            System.err.println("自动提交过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 执行Git Add命令
     */
    private void executeGitAdd(List<String> files) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("git", "add");
        pb.command().addAll(files);
        Process process = pb.start();
        
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Git add failed");
        }
    }

    /**
     * 执行Git Commit命令
     */
    private void executeGitCommit(String commitMessage) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("git", "commit", "-m", commitMessage);
        Process process = pb.start();
        
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Git commit failed");
        }
    }

    /**
     * 执行Git Push命令
     */
    private void executeGitPush() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("git", "push");
        Process process = pb.start();
        
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Git push failed");
        }
    }
}