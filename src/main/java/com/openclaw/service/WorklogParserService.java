package com.openclaw.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WorklogParserService {

    private static final String WORKING_LOG_DIR = "F:\\\\openclaw\\\\agent\\\\workinglog\\\\";
    
    /**
     * 解析指定 Agent 的最新日志，提取修改的文件路径
     * 
     * @param agentId Agent ID (例如 "jiangrou")
     * @return 修改的文件路径列表
     */
    public List<String> parseModifiedFiles(String agentId) {
        Path logDir = Paths.get(WORKING_LOG_DIR + agentId);
        
        if (!Files.exists(logDir) || !Files.isDirectory(logDir)) {
            System.out.println("日志目录不存在: " + logDir.toString());
            return new ArrayList<>();
        }

        try {
            // 获取最新的日志文件
            Optional<Path> latestLogFile = Files.list(logDir)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".md"))
                    .max(Comparator.comparing(path -> {
                        try {
                            return Files.getLastModifiedTime(path);
                        } catch (IOException e) {
                            return FileTime.fromMillis(0);
                        }
                    }));

            if (!latestLogFile.isPresent()) {
                System.out.println("未找到日志文件");
                return new ArrayList<>();
            }

            System.out.println("解析日志文件: " + latestLogFile.get().toString());
            
            // 读取日志内容
            String content = new String(Files.readAllBytes(latestLogFile.get()));
            
            // 使用正则表达式匹配 "## 修改的文件" 部分
            Pattern pattern = Pattern.compile("## 修改的文件\\s+((?:- `[^`]+`.*\\n?)*)", Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(content);
            
            List<String> extractedFiles = new ArrayList<>();
            
            if (matcher.find()) {
                String filesSection = matcher.group(1).trim();
                
                // 匹配 "- `文件路径` - 描述" 格式的行
                Pattern filePattern = Pattern.compile("- `([^`]+)`(?: - .*)?");
                Matcher fileMatcher = filePattern.matcher(filesSection);
                
                while (fileMatcher.find()) {
                    String filePath = fileMatcher.group(1).trim();
                    extractedFiles.add(filePath);
                }
            }
            
            System.out.println("从日志中提取到的文件数量: " + extractedFiles.size());
            for (String file : extractedFiles) {
                System.out.println("  - " + file);
            }
            
            return extractedFiles;
            
        } catch (IOException e) {
            System.err.println("读取日志文件时发生错误: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * 过滤出 Java 文件
     * 
     * @param files 文件路径列表
     * @return Java 文件路径列表
     */
    public List<String> filterJavaFiles(List<String> files) {
        if (files == null || files.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<String> javaFiles = files.stream()
                .filter(file -> file.toLowerCase().endsWith(".java"))
                .collect(Collectors.toList());
        
        System.out.println("过滤后的 Java 文件数量: " + javaFiles.size());
        for (String file : javaFiles) {
            System.out.println("  - " + file);
        }
        
        return javaFiles;
    }
    
    /**
     * 组合方法：直接解析指定 Agent 的最新日志并过滤出 Java 文件
     * 
     * @param agentId Agent ID (例如 "jiangrou")
     * @return Java 文件路径列表
     */
    public List<String> parseAndFilterJavaFiles(String agentId) {
        List<String> modifiedFiles = parseModifiedFiles(agentId);
        return filterJavaFiles(modifiedFiles);
    }
}