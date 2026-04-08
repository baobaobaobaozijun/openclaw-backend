package com.baozipu.controller;

import com.openclaw.entity.Article;
import com.openclaw.mapper.ArticleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArticleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ArticleController articleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Article> articles = Arrays.asList(
            createArticle(1L, "标题 1", "内容 1"),
            createArticle(2L, "标题 2", "内容 2")
        );
        when(articleMapper.selectList(null)).thenReturn(articles);

        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        verify(articleMapper, times(1)).selectList(null);
    }

    @Test
    public void testGetById() throws Exception {
        Article article = createArticle(1L, "标题 1", "内容 1");
        when(articleMapper.selectById(1L)).thenReturn(article);

        mockMvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("标题 1"));

        verify(articleMapper, times(1)).selectById(1L);
    }

    @Test
    public void testCreate() throws Exception {
        when(articleMapper.insert(any(Article.class))).thenReturn(1);

        mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"新文章\",\"content\":\"新内容\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(articleMapper, times(1)).insert(any(Article.class));
    }

    @Test
    public void testUpdate() throws Exception {
        when(articleMapper.updateById(any(Article.class))).thenReturn(1);

        mockMvc.perform(put("/api/articles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"更新标题\",\"content\":\"更新内容\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(articleMapper, times(1)).updateById(any(Article.class));
    }

    @Test
    public void testDelete() throws Exception {
        when(articleMapper.deleteById(1L)).thenReturn(1);

        mockMvc.perform(delete("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(articleMapper, times(1)).deleteById(1L);
    }

    private Article createArticle(Long id, String title, String content) {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        return article;
    }
}
