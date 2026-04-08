package com.openclaw.service;

import com.openclaw.entity.Article;
import java.util.List;
import java.util.Map;

/**
 * 文章查询服务接口
 */
public interface ArticleQueryService {

    /**
     * 根据分类 ID 查询文章列表
     */
    List<Article> findByCategoryId(Long categoryId);

    /**
     * 查询所有已发布的文章
     */
    List<Article> findPublished();

    /**
     * 获取分页文章列表
     */
    Map<String, Object> getArticles(int page, int size);

    /**
     * 根据 ID 获取文章详情
     */
    Map<String, Object> getArticleById(Long id);

    /**
     * 创建文章
     */
    Map<String, Object> createArticle(String title, String content, Long categoryId, List<String> tags);

    /**
     * 更新文章
     */
    Map<String, Object> updateArticle(Long id, String title, String content, Long categoryId, List<String> tags);

    /**
     * 删除文章
     */
    Map<String, Object> deleteArticle(Long id);
}
