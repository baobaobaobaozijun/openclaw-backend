package com.openclaw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import com.openclaw.service.ArticleQueryService;

/**
 * 文章管理控制器
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleQueryService articleQueryService;

    /**
     * 获取文章列表（分页）
     *
     * @param page 页码
     * @param size 每页数量
     * @return 分页的文章列表
     */
    @GetMapping
    public Map<String, Object> getArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        // 调用服务层获取分页数据
        Map<String, Object> result = articleQueryService.getArticles(page, size);
        
        return Map.of(
            "code", 200,
            "data", result
        );
    }

    /**
     * 获取文章详情
     *
     * @param id 文章 ID
     * @return 文章详情
     */
    @GetMapping("/{id}")
    public Map<String, Object> getArticle(@PathVariable Long id) {
        // 调用服务层获取文章详情
        Map<String, Object> article = articleQueryService.getArticleById(id);
        
        return Map.of(
            "code", 200,
            "data", article
        );
    }

    /**
     * 创建文章
     *
     * @param requestBody 请求体，包含文章信息
     * @return 创建结果
     */
    @PostMapping
    public Map<String, Object> createArticle(@RequestBody Map<String, Object> requestBody) {
        // 从请求体中提取文章信息
        String title = (String) requestBody.get("title");
        String content = (String) requestBody.get("content");
        Long categoryId = (Long) requestBody.get("categoryId");
        List<String> tags = (List<String>) requestBody.get("tags");

        // 调用服务层创建文章
        Map<String, Object> result = articleQueryService.createArticle(title, content, categoryId, tags);

        return Map.of(
            "code", 200,
            "data", result
        );
    }

    /**
     * 更新文章
     *
     * @param id 文章 ID
     * @param requestBody 请求体，包含要更新的文章信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Map<String, Object> updateArticle(@PathVariable Long id, 
                                           @RequestBody Map<String, Object> requestBody) {
        // 从请求体中提取文章信息
        String title = (String) requestBody.get("title");
        String content = (String) requestBody.get("content");
        Long categoryId = (Long) requestBody.get("categoryId");
        List<String> tags = (List<String>) requestBody.get("tags");

        // 调用服务层更新文章
        Map<String, Object> result = articleQueryService.updateArticle(id, title, content, categoryId, tags);

        return Map.of(
            "code", 200,
            "data", result
        );
    }

    /**
     * 删除文章
     *
     * @param id 文章 ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteArticle(@PathVariable Long id) {
        // 调用服务层删除文章
        Map<String, Object> result = articleQueryService.deleteArticle(id);

        return result;
    }
}
