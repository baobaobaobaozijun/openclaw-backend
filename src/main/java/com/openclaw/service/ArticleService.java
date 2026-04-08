package com.openclaw.service;

import com.openclaw.entity.Article;
import com.openclaw.dto.ArticleQuery;
import java.util.List;

public interface ArticleService {
    /**
     * 创建文章
     */
    Article createArticle(Article article);

    /**
     * 更新文章
     */
    Article updateArticle(Article article);

    /**
     * 删除文章
     */
    void deleteArticle(Long id);

    /**
     * 获取文章详情
     */
    Article getArticle(Long id);

    /**
     * 查询文章列表（分页 + 筛选）
     */
    List<Article> listArticles(ArticleQuery query);

    /**
     * 发布文章（DRAFT → PUBLISHED）
     */
    void publishArticle(Long id);
}