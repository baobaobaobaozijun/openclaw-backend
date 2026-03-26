package com.baozipu.service;

import java.util.List;

/**
 * 文章服务接口
 */
public interface ArticleService {
    
    /**
     * 创建文章
     * @param title 标题
     * @param content 内容
     * @param category 分类
     * @return 文章 ID
     */
    Long create(String title, String content, String category);
    
    /**
     * 根据 ID 获取文章
     * @param id 文章 ID
     * @return 文章信息
     */
    ArticleDto getById(Long id);
    
    /**
     * 获取文章列表
     * @param page 页码
     * @param size 每页数量
     * @return 文章列表
     */
    List<ArticleDto> list(int page, int size);
    
    /**
     * 更新文章
     * @param id 文章 ID
     * @param title 标题
     * @param content 内容
     */
    void update(Long id, String title, String content);
    
    /**
     * 删除文章
     * @param id 文章 ID
     */
    void delete(Long id);
    
    /**
     * 文章 DTO
     */
    class ArticleDto {
        private Long id;
        private String title;
        private String content;
        private String summary;
        private Long authorId;
        private String status;
        private String accessLevel;
        private Integer viewCount;
        private java.time.LocalDateTime publishedAt;
        private java.time.LocalDateTime createdAt;
        private java.time.LocalDateTime updatedAt;
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public String getSummary() { return summary; }
        public void setSummary(String summary) { this.summary = summary; }
        public Long getAuthorId() { return authorId; }
        public void setAuthorId(Long authorId) { this.authorId = authorId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getAccessLevel() { return accessLevel; }
        public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
        public Integer getViewCount() { return viewCount; }
        public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }
        public java.time.LocalDateTime getPublishedAt() { return publishedAt; }
        public void setPublishedAt(java.time.LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
        public java.time.LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }
        public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    }
}
