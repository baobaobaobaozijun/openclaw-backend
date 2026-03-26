package com.baozipu.controller;

import com.baozipu.entity.Category;
import com.baozipu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public List<Category> list() {
        return categoryService.list();
    }
    
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }
    
    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }
    
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Category category) {
        categoryService.update(id, category);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}