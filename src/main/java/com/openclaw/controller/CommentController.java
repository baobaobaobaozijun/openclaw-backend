package com.openclaw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * 评论管理 API 控制器
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    // 模拟存储层，实际项目中应该注入对应的 Service
    private List<Comment> comments = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * 获取文章评论列表
     * @param articleId 文章ID
     * @return 评论列表
     */
    @GetMapping
    public List<Comment> getComments(@RequestParam(required = false) Long articleId) {
        if (articleId != null) {
            // 根据文章ID过滤评论
            List<Comment> filteredComments = new ArrayList<>();
            for (Comment comment : comments) {
                if (comment.getArticleId().equals(articleId)) {
                    filteredComments.add(comment);
                }
            }
            return filteredComments;
        } else {
            // 返回所有评论
            return comments;
        }
    }

    /**
     * 创建评论
     * @param commentRequest 评论请求实体
     * @return 创建的评论
     */
    @PostMapping
    public Comment createComment(@RequestBody CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setId(nextId++);
        comment.setArticleId(commentRequest.getArticleId());
        comment.setAuthorId(commentRequest.getAuthorId());
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        
        comments.add(comment);
        return comment;
    }

    /**
     * 删除评论
     * @param id 评论ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {
        boolean removed = comments.removeIf(comment -> comment.getId().equals(id));
        if (removed) {
            return "Comment deleted successfully";
        } else {
            return "Comment not found";
        }
    }

    /**
     * 评论实体类
     */
    static class Comment {
        private Long id;
        private Long articleId;
        private Long authorId;
        private String content;
        private LocalDateTime createdAt;

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getArticleId() {
            return articleId;
        }

        public void setArticleId(Long articleId) {
            this.articleId = articleId;
        }

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }

    /**
     * 评论请求实体类
     */
    static class CommentRequest {
        private Long articleId;
        private Long authorId;
        private String content;

        // Getters and Setters
        public Long getArticleId() {
            return articleId;
        }

        public void setArticleId(Long articleId) {
            this.articleId = articleId;
        }

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}