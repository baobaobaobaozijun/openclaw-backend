package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.dto.ArticleCreateDTO;
import com.openclaw.dto.ArticleResponseDTO;
import com.openclaw.dto.ArticleUpdateDTO;
import com.openclaw.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import java.util.List;
import com.openclaw.util.JwtUtil;

/**
 * 文章 Controller
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Slf4j
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Tag(name = "文章管理", description = "文章 CRUD 接口")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 创建文章
     * 
     * POST /api/articles
     */
    @PostMapping
    @Operation(summary = "创建文章", description = "创建新文章，需要提供有效的文章创建信息和用户ID")
    public Result<ArticleResponseDTO> createArticle(@Valid @RequestBody ArticleCreateDTO dto,
                                                     @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return Result.error(401, "未登录");
        }

        String token = authorizationHeader.substring(7);
        Long authorId;
        try {
            authorId = jwtUtil.getUserIdFromToken(token);
            if (authorId == null) {
                return Result.error(401, "未登录");
            }
        } catch (Exception e) {
            return Result.error(401, "未登录");
        }

        log.info("Create article request: {} by user: {}", dto, authorId);
        ArticleResponseDTO result = articleService.createArticle(dto, authorId);
        return Result.success("Article created successfully", result);
    }

    /**
     * 根据 ID 获取文章
     * 
     * GET /api/articles/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取文章", description = "根据文章ID获取文章详细信息")
    public Result<ArticleResponseDTO> getArticleById(@Parameter(description = "文章ID") @PathVariable Long id) {
        log.info("Get article by id: {}", id);
        ArticleResponseDTO result = articleService.getArticleById(id);
        return Result.success(result);
    }

    /**
     * 获取文章列表（支持分页和搜索）
     * 
     * GET /api/articles?page=1&size=10&keyword=xxx&categoryId=1
     */
    @GetMapping
    @Operation(summary = "获取文章列表", description = "获取文章列表，支持分页、关键词搜索和分类筛选")
    public Result<Page<ArticleResponseDTO>> getArticles(
            @Parameter(description = "页码，默认为1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页数量，默认为10") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "搜索关键词") @RequestParam(required = false) String keyword,
            @Parameter(description = "分类ID") @RequestParam(required = false) Long categoryId) {
        log.info("Get articles with pagination: page={}, size={}, keyword={}, categoryId={}", page, size, keyword, categoryId);
        Page<ArticleResponseDTO> result = articleService.getArticlesWithPagination(page, size, keyword, categoryId);
        return Result.success(result);
    }

    /**
     * 分页获取文章
     * 
     * GET /api/articles/page?pageNum=1&pageSize=10
     */
    @GetMapping("/page")
    @Operation(summary = "分页获取文章", description = "分页获取文章列表")
    public Result<Page<ArticleResponseDTO>> getArticlesByPage(
            @Parameter(description = "页码，默认为1") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页数量，默认为10") @RequestParam(defaultValue = "10") Integer pageSize) {
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
    @Operation(summary = "更新文章", description = "根据ID更新文章信息")
    public Result<ArticleResponseDTO> updateArticle(@Parameter(description = "文章ID") @PathVariable Long id,
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
    @Operation(summary = "删除文章", description = "根据ID删除文章")
    public Result<Void> deleteArticle(@Parameter(description = "文章ID") @PathVariable Long id) {
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
    @Operation(summary = "根据作者ID获取文章", description = "根据作者ID获取该作者的所有文章")
    public Result<List<ArticleResponseDTO>> getArticlesByAuthor(@Parameter(description = "作者ID") @PathVariable Long authorId) {
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
    @Operation(summary = "根据状态获取文章", description = "根据文章状态获取文章列表")
    public Result<List<ArticleResponseDTO>> getArticlesByStatus(@Parameter(description = "文章状态") @PathVariable String status) {
        log.info("Get articles by status: {}", status);
        List<ArticleResponseDTO> result = articleService.getArticlesByStatus(status);
        return Result.success(result);
    }
}