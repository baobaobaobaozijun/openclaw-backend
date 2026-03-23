package com.openclaw.service.impl;

import com.openclaw.dto.ArticleCreateDTO;
import com.openclaw.dto.ArticleResponseDTO;
import com.openclaw.entity.Article;
import com.openclaw.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void createArticle_shouldReturnArticle() {
        ArticleCreateDTO dto = new ArticleCreateDTO();
        dto.setTitle("Test Article");
        dto.setContent("# Hello World");

        when(articleMapper.insert(any(Article.class))).thenReturn(1);

        assertDoesNotThrow(() -> articleService.createArticle(dto, 1L));
        verify(articleMapper, times(1)).insert(any(Article.class));
    }

    @Test
    void getArticleById_shouldReturnArticle() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("Test");
        article.setContent("Content");
        article.setAuthorId(1L);

        when(articleMapper.selectById(1L)).thenReturn(article);

        ArticleResponseDTO result = articleService.getArticleById(1L);
        assertNotNull(result);
    }

    @Test
    void getArticleById_notFound_shouldThrow() {
        when(articleMapper.selectById(999L)).thenReturn(null);

        assertThrows(Exception.class, () -> articleService.getArticleById(999L));
    }

    @Test
    void deleteArticle_shouldCallMapper() {
        when(articleMapper.deleteById(1L)).thenReturn(1);

        assertDoesNotThrow(() -> articleService.deleteArticle(1L));
        verify(articleMapper, times(1)).deleteById(1L);
    }
}