package com.openclaw.service.impl;

import com.openclaw.dto.ArticleCreateDTO;
import com.openclaw.dto.ArticleResponseDTO;
import com.openclaw.dto.ArticleUpdateDTO;
import com.openclaw.entity.Article;
import com.openclaw.exception.BusinessException;
import com.openclaw.exception.ResourceNotFoundException;
import com.openclaw.mapper.ArticleMapper;
import com.openclaw.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章 Service 实现类
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleResponseDTO createArticle(ArticleCreateDTO dto, Long authorId) {
        log.info("Creating article: {}", dto.getTitle());

        // 参数校验
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new BusinessException(400, "Title is required");
        }
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            throw new BusinessException(400, "Content is required");
        }

        // 转换 DTO 到 Entity
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        article.setAuthorId(authorId);
        
        // 设置默认值
        if (article.getStatus() == null) {
            article.setStatus("DRAFT");
        }
        if (article.getAccessLevel() == null) {
            article.setAccessLevel(0);
        }
        if (article.getViewCount() == null) {
            article.setViewCount(0);
        }

        // 保存到数据库
        int rows = articleMapper.insert(article);
        if (rows == 0) {
            throw new BusinessException("Failed to create article");
        }

        log.info("Article created successfully with id: {}", article.getId());

        // 转换为 Response DTO
        return convertToResponseDTO(article);
    }

    @Override
    public ArticleResponseDTO getArticleById(Long id) {
        log.info("Getting article by id: {}", id);

        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new ResourceNotFoundException("Article", id);
        }

        return convertToResponseDTO(article);
    }

    @Override
    public List<ArticleResponseDTO> getAllArticles() {
        log.info("Getting all articles");

        List<Article> articles = articleMapper.selectList(null);
        return articles.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ArticleResponseDTO> getArticlesByPage(Integer pageNum, Integer pageSize) {
        log.info("Getting articles by page: {}, {}", pageNum, pageSize);

        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Article::getCreatedAt);

        Page<Article> articlePage = articleMapper.selectPage(page, wrapper);

        // 转换为 Response DTO
        List<ArticleResponseDTO> dtoList = articlePage.getRecords().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        Page<ArticleResponseDTO> resultPage = new Page<>(
                articlePage.getCurrent(),
                articlePage.getSize(),
                articlePage.getTotal()
        );
        resultPage.setRecords(dtoList);

        return resultPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleResponseDTO updateArticle(Long id, ArticleUpdateDTO dto) {
        log.info("Updating article: {}", id);

        // 检查文章是否存在
        Article existingArticle = articleMapper.selectById(id);
        if (existingArticle == null) {
            throw new ResourceNotFoundException("Article", id);
        }

        // 更新字段
        if (dto.getTitle() != null) {
            existingArticle.setTitle(dto.getTitle());
        }
        if (dto.getContent() != null) {
            existingArticle.setContent(dto.getContent());
        }
        if (dto.getSummary() != null) {
            existingArticle.setSummary(dto.getSummary());
        }
        if (dto.getStatus() != null) {
            existingArticle.setStatus(dto.getStatus());
        }
        if (dto.getAccessLevel() != null) {
            existingArticle.setAccessLevel(dto.getAccessLevel());
        }

        existingArticle.setUpdatedAt(LocalDateTime.now());

        // 保存到数据库
        int rows = articleMapper.updateById(existingArticle);
        if (rows == 0) {
            throw new BusinessException("Failed to update article");
        }

        log.info("Article updated successfully: {}", id);

        return convertToResponseDTO(existingArticle);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Long id) {
        log.info("Deleting article: {}", id);

        // 检查文章是否存在
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new ResourceNotFoundException("Article", id);
        }

        // 逻辑删除
        int rows = articleMapper.deleteById(id);
        if (rows == 0) {
            throw new BusinessException("Failed to delete article");
        }

        log.info("Article deleted successfully: {}", id);
    }

    @Override
    public List<ArticleResponseDTO> getArticlesByAuthor(Long authorId) {
        log.info("Getting articles by author: {}", authorId);

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getAuthorId, authorId)
                .orderByDesc(Article::getCreatedAt);

        List<Article> articles = articleMapper.selectList(wrapper);
        return articles.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponseDTO> getArticlesByStatus(String status) {
        log.info("Getting articles by status: {}", status);

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, status)
                .orderByDesc(Article::getCreatedAt);

        List<Article> articles = articleMapper.selectList(wrapper);
        return articles.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * 转换 Entity 到 Response DTO
     */
    private ArticleResponseDTO convertToResponseDTO(Article article) {
        ArticleResponseDTO dto = new ArticleResponseDTO();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }
}
