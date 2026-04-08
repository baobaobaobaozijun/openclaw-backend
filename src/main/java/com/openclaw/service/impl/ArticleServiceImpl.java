package com.openclaw.service.impl;

import com.openclaw.service.ArticleService;
import com.openclaw.entity.Article;
import com.openclaw.dto.ArticleQuery;
import com.openclaw.mapper.ArticleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional
    public Article createArticle(Article article) {
        articleMapper.insert(article);
        return article;
    }

    @Override
    @Transactional
    public Article updateArticle(Article article) {
        articleMapper.updateById(article);
        return article;
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) {
        articleMapper.deleteById(id);
    }

    @Override
    public Article getArticle(Long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public List<Article> listArticles(ArticleQuery query) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        
        if (query.getCategoryId() != null) {
            wrapper.eq("category_id", query.getCategoryId());
        }
        if (query.getAuthorId() != null) {
            wrapper.eq("author_id", query.getAuthorId());
        }
        if (query.getStatus() != null && !query.getStatus().isEmpty()) {
            wrapper.eq("status", query.getStatus());
        }
        
        // 分页处理
        if (query.getPage() != null && query.getSize() != null) {
            Page<Article> page = new Page<>(query.getPage(), query.getSize());
            return articleMapper.selectPage(page, wrapper).getRecords();
        } else {
            return articleMapper.selectList(wrapper);
        }
    }

    @Override
    @Transactional
    public void publishArticle(Long id) {
        Article article = new Article();
        article.setId(id);
        article.setStatus("PUBLISHED");
        articleMapper.updateById(article);
    }
}