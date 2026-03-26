package com.baozipu.service.impl;

import com.baozipu.service.CategoryService;
import com.baozipu.entity.Category;
import com.baozipu.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<Category> list() {
        return categoryMapper.selectList(null);
    }
    
    @Override
    public Category getById(Long id) {
        return categoryMapper.selectById(id);
    }
    
    @Override
    public Category create(Category category) {
        categoryMapper.insert(category);
        return category;
    }
    
    @Override
    public void update(Long id, Category category) {
        category.setId(id);
        categoryMapper.updateById(category);
    }
    
    @Override
    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }
}