package com.openclaw.controller;

import com.openclaw.entity.Article;
import com.openclaw.service.ArticleService;
import com.openclaw.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 创建文章
     */
    @PostMapping
    public Result<Article> createArticle(@Valid @RequestBody ArticleRequest request) {
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setCategoryId(request.getCategoryId());
        article.setStatus(request.getStatus());
        
        Article savedArticle = articleService.createArticle(article);
        return Result.success(savedArticle);
    }

    /**
     * 获取文章详情
     */
    @GetMapping("/{id}")
    public Result<Article> getArticleById(@PathVariable @NotNull Long id) {
        Article article = articleService.findById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        return Result.success(article);
    }

    /**
     * 更新文章
     */
    @PutMapping("/{id}")
    public Result<Article> updateArticle(@PathVariable @NotNull Long id, 
                                         @Valid @RequestBody ArticleRequest request) {
        Article article = articleService.findById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setCategoryId(request.getCategoryId());
        article.setStatus(request.getStatus());
        
        Article updatedArticle = articleService.updateArticle(article);
        return Result.success(updatedArticle);
    }

    /**
     * 删除文章
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteArticle(@PathVariable @NotNull Long id) {
        Article article = articleService.findById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        
        articleService.deleteArticle(id);
        return Result.success();
    }

    /**
     * 查询文章列表（分页 + 筛选）
     */
    @GetMapping
    public Result<List<Article>> getArticles(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        List<Article> articles = articleService.findArticles(categoryId, status, page, size);
        return Result.success(articles);
    }

    /**
     * 发布文章
     */
    @PostMapping("/{id}/publish")
    public Result<Article> publishArticle(@PathVariable @NotNull Long id) {
        Article article = articleService.findById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        
        Article publishedArticle = articleService.publishArticle(id);
        return Result.success(publishedArticle);
    }

    /**
     * 请求体 DTO
     */
    public static class ArticleRequest {
        @NotNull(message = "文章标题不能为空")
        private String title;
        
        @NotNull(message = "文章内容不能为空")
        private String content;
        
        private Long categoryId;
        
        private String status;

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}