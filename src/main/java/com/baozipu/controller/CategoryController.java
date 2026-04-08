package com.baozipu.controller;

import com.baozipu.entity.Category;
import com.baozipu.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping
    public List<Category> list() {
        return categoryMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryMapper.selectById(id);
    }

    @PostMapping
    public boolean create(@RequestBody Category category) {
        return categoryMapper.insert(category) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        return categoryMapper.updateById(category) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return categoryMapper.deleteById(id) > 0;
    }
}