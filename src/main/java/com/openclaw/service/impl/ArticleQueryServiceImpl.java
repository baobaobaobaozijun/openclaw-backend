package com.openclaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openclaw.entity.Article;
import com.openclaw.mapper.ArticleMapper;
import com.openclaw.service.ArticleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleQueryServiceImpl implements ArticleQueryService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据分类ID查询文章列表
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
}