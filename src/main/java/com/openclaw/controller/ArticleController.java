package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.dto.ArticleCreateDTO;
import com.openclaw.dto.ArticleResponseDTO;
import com.openclaw.dto.ArticleUpdateDTO;
import com.openclaw.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 文章 Controller
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Slf4j
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 创建文章
     * 
     * POST /api/articles
     */
    @PostMapping
    public Result<ArticleResponseDTO> createArticle(@Valid @RequestBody ArticleCreateDTO dto,
                                                     @RequestHeader("X-User-Id") Long authorId) {
        log.info("Create article request: {}", dto);
        ArticleResponseDTO result = articleService.createArticle(dto, authorId);
        return Result.success("Article created successfully", result);
    }

    /**
     * 根据 ID 获取文章
     * 
     * GET /api/articles/{id}
     */
    @GetMapping("/{id}")
    public Result<ArticleResponseDTO> getArticleById(@PathVariable Long id) {
        log.info("Get article by id: {}", id);
        ArticleResponseDTO result = articleService.getArticleById(id);
        return Result.success(result);
    }

    /**
     * 获取所有文章
     * 
     * GET /api/articles
     */
    @GetMapping
    public Result<List<ArticleResponseDTO>> getAllArticles() {
        log.info("Get all articles");
        List<ArticleResponseDTO> result = articleService.getAllArticles();
        return Result.success(result);
    }

    /**
     * 分页获取文章
     * 
     * GET /api/articles/page?pageNum=1&pageSize=10
     */
    @GetMapping("/page")
    public Result<Page<ArticleResponseDTO>> getArticlesByPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("Get articles by page: {}, {}", pageNum, pageSize);
        Page<ArticleResponseDTO> result = articleService.getArticlesByPage(pageNum, pageSize);
        return Result.success(result);
    }

    /**
     * 更新文章
     * 
     * PUT /api/articles/{id}
     */
    @PutMapping("/{id}")
    public Result<ArticleResponseDTO> updateArticle(@PathVariable Long id,
                                                     @Valid @RequestBody ArticleUpdateDTO dto) {
        log.info("Update article: {}", id);
        ArticleResponseDTO result = articleService.updateArticle(id, dto);
        return Result.success("Article updated successfully", result);
    }

    /**
     * 删除文章
     * 
     * DELETE /api/articles/{id}
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteArticle(@PathVariable Long id) {
        log.info("Delete article: {}", id);
        articleService.deleteArticle(id);
        return Result.success("Article deleted successfully", null);
    }

    /**
     * 根据作者 ID 获取文章
     * 
     * GET /api/articles/author/{authorId}
     */
    @GetMapping("/author/{authorId}")
    public Result<List<ArticleResponseDTO>> getArticlesByAuthor(@PathVariable Long authorId) {
        log.info("Get articles by author: {}", authorId);
        List<ArticleResponseDTO> result = articleService.getArticlesByAuthor(authorId);
        return Result.success(result);
    }

    /**
     * 根据状态获取文章
     * 
     * GET /api/articles/status/{status}
     */
    @GetMapping("/status/{status}")
    public Result<List<ArticleResponseDTO>> getArticlesByStatus(@PathVariable String status) {
        log.info("Get articles by status: {}", status);
        List<ArticleResponseDTO> result = articleService.getArticlesByStatus(status);
        return Result.success(result);
    }
}
