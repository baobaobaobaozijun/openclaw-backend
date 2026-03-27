package com.openclaw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openclaw.dto.ArticleCreateDTO;
import com.openclaw.dto.ArticleResponseDTO;
import com.openclaw.dto.ArticleUpdateDTO;
import com.openclaw.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章 Controller
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 创建文章
     */
    @PostMapping("/")
    public ArticleResponseDTO createArticle(@RequestBody ArticleCreateDTO dto, @RequestParam Long authorId) {
        return articleService.createArticle(dto, authorId);
    }

    /**
     * 根据 ID 获取单篇文章
     */
    @GetMapping("/{id}")
    public ArticleResponseDTO getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    /**
     * 获取全部文章
     */
    @GetMapping("/")
    public List<ArticleResponseDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    /**
     * 分页查询文章
     */
    @GetMapping("/page")
    public Page<ArticleResponseDTO> getArticlesByPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return articleService.getArticlesByPage(pageNum, pageSize);
    }

    /**
     * 更新文章
     */
    @PutMapping("/{id}")
    public ArticleResponseDTO updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateDTO dto) {
        return articleService.updateArticle(id, dto);
    }

    /**
     * 删除文章
     */
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
