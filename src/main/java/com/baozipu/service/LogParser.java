package com.baozipu.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

@Service
public class LogParser {
    
    public List<LogEntry> parseAllAgents() throws IOException {
        List<LogEntry> entries = new ArrayList<>();
        String[] agents = {"jiangrou", "dousha", "suancai", "guantang"};
        
        for (String agent : agents) {
            Path dir = Paths.get("F:/openclaw/agent/workinglog/" + agent);
            if (!Files.exists(dir)) continue;
            
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.md")) {
                for (Path file : stream) {
                    LogEntry entry = parseFile(file);
                    if (entry != null) entries.add(entry);
                }
            }
        }
        return entries;
    }
    
    private LogEntry parseFile(Path file) throws IOException {
        String content = Files.readString(file);
        LogEntry entry = new LogEntry();
        entry.setFileName(file.getFileName().toString());
        entry.setContent(content);
        // 提取修改文件列表
        List<String> files = extractFiles(content);
        entry.setModifiedFiles(files);
        return entry;
    }
    
    private List<String> extractFiles(String content) {
        List<String> files = new ArrayList<>();
        Pattern pattern = Pattern.compile("- `([^`]+)`");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            files.add(matcher.group(1));
        }
        return files;
    }
    
    public static class LogEntry {
        private String fileName;
        private String content;
        private List<String> modifiedFiles;
        // Getters/Setters
        public String getFileName() { return fileName; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public List<String> getModifiedFiles() { return modifiedFiles; }
        public void setModifiedFiles(List<String> modifiedFiles) { this.modifiedFiles = modifiedFiles; }
    }
}