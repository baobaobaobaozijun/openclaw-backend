package com.baozipu.controller;

import com.openclaw.entity.Article;
import com.openclaw.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping
    public List<Article> list() {
        return articleMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable Long id) {
        return articleMapper.selectById(id);
    }

    @PostMapping
    public boolean create(@RequestBody Article article) {
        return articleMapper.insert(article) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Article article) {
        article.setId(id);
        return articleMapper.updateById(article) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return articleMapper.deleteById(id) > 0;
    }
}
