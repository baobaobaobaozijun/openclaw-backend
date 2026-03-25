package com.openclaw.service;

import com.openclaw.entity.Article;
import java.util.List;

/**
 * 文章查询服务接口
 */
public interface ArticleQueryService {

    /**
     * 根据分类ID查询文章列表
     */
    List<Article> findByCategoryId(Long categoryId);

    /**
     * 查询所有已发布的文章
     */
    List<Article> findPublished();
}
