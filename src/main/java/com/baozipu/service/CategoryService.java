package com.baozipu.service;

import com.baozipu.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> list();
    Category getById(Long id);
    Category create(Category category);
    void update(Long id, Category category);
    void delete(Long id);
}