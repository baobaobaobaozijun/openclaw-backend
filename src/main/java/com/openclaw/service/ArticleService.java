package com.openclaw.service;

import com.openclaw.dto.ArticleCreateDTO;
import com.openclaw.dto.ArticleResponseDTO;
import com.openclaw.dto.ArticleUpdateDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 文章 Service 接口
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
public interface ArticleService {

    /**
     * 创建文章
     * 
     * @param dto 文章创建 DTO
     * @param authorId 作者 ID
     * @return 创建的文章
     */
    ArticleResponseDTO createArticle(ArticleCreateDTO dto, Long authorId);

    /**
     * 根据 ID 获取文章
     * 
     * @param id 文章 ID
     * @return 文章详情
     */
    ArticleResponseDTO getArticleById(Long id);

    /**
     * 获取所有文章
     * 
     * @return 文章列表
     */
    List<ArticleResponseDTO> getAllArticles();

    /**
     * 分页获取文章
     * 
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    Page<ArticleResponseDTO> getArticlesByPage(Integer pageNum, Integer pageSize);

    /**
     * 更新文章
     * 
     * @param id 文章 ID
     * @param dto 文章更新 DTO
     * @return 更新后的文章
     */
    ArticleResponseDTO updateArticle(Long id, ArticleUpdateDTO dto);

    /**
     * 删除文章
     * 
     * @param id 文章 ID
     */
    void deleteArticle(Long id);

    /**
     * 根据作者 ID 获取文章
     * 
     * @param authorId 作者 ID
     * @return 文章列表
     */
    List<ArticleResponseDTO> getArticlesByAuthor(Long authorId);

    /**
     * 根据状态获取文章
     * 
     * @param status 文章状态
     * @return 文章列表
     */
    List<ArticleResponseDTO> getArticlesByStatus(String status);
}
