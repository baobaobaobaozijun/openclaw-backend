package com.openclaw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openclaw.entity.Category;
import com.openclaw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类 Controller
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类列表（支持分页）
     */
    @GetMapping("/page")
    public Page<Category> getCategoriesByPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return categoryService.page(new Page<>(pageNum, pageSize));
    }

    /**
     * 获取分类树
     */
    @GetMapping("/tree")
    public List<Category> getCategoryTree() {
        return categoryService.listTree();
    }

    /**
     * 根据父 ID 获取子分类
     */
    @GetMapping("/byParent")
    public List<Category> getCategoriesByParentId(@RequestParam Long parentId) {
        return categoryService.listByParentId(parentId);
    }

    /**
     * 创建分类
     */
    @PostMapping("/")
    public Category createCategory(@RequestBody Category category) {
        categoryService.save(category);
        return category;
    }

    /**
     * 更新分类
     */
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        categoryService.updateById(category);
        return category;
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.removeById(id);
    }
}
