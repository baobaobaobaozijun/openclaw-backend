package com.openclaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openclaw.entity.Article;
import com.openclaw.mapper.ArticleMapper;
import com.openclaw.service.ArticleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class ArticleQueryServiceImpl implements ArticleQueryService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据分类 ID 查询文章列表
     * 通过关联表 article_tag 查询（与 schema.sql 表名一致）
     */
    @Override
    public List<Article> findByCategoryId(Long categoryId) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        return articleMapper.selectList(wrapper);
    }

    /**
     * 查询所有已发布的文章，按创建时间倒序排列
     */
    @Override
    public List<Article> findPublished() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "PUBLISHED")
               .orderByDesc("created_at");
        
        return articleMapper.selectList(wrapper);
    }

    /**
     * 获取分页文章列表
     */
    @Override
    public Map<String, Object> getArticles(int page, int size) {
        Page<Article> articlePage = new Page<>(page + 1, size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "PUBLISHED")
               .orderByDesc("created_at");
        
        Page<Article> result = articleMapper.selectPage(articlePage, wrapper);
        
        Map<String, Object> response = new HashMap<>();
        response.put("list", result.getRecords());
        response.put("total", result.getTotal());
        response.put("current", result.getCurrent());
        response.put("size", result.getSize());
        response.put("pages", result.getPages());
        
        return response;
    }

    /**
     * 根据 ID 获取文章详情
     */
    @Override
    public Map<String, Object> getArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", 404);
            error.put("message", "文章不存在");
            return error;
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", article);
        return response;
    }

    /**
     * 创建文章
     */
    @Override
    public Map<String, Object> createArticle(String title, String content, Long categoryId, List<String> tags) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCategoryId(categoryId);
        article.setStatus("PUBLISHED");
        
        articleMapper.insert(article);
        
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "创建成功");
        response.put("data", article);
        return response;
    }

    /**
     * 更新文章
     */
    @Override
    public Map<String, Object> updateArticle(Long id, String title, String content, Long categoryId, List<String> tags) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", 404);
            error.put("message", "文章不存在");
            return error;
        }
        
        article.setTitle(title);
        article.setContent(content);
        article.setCategoryId(categoryId);
        
        articleMapper.updateById(article);
        
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "更新成功");
        response.put("data", article);
        return response;
    }

    /**
     * 删除文章
     */
    @Override
    public Map<String, Object> deleteArticle(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", 404);
            error.put("message", "文章不存在");
            return error;
        }
        
        articleMapper.deleteById(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "删除成功");
        return response;
    }
}
