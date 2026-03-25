package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.entity.Category;
import com.openclaw.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "分类管理", description = "文章分类 CRUD 接口")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "获取所有分类", description = "获取文章分类列表")
    public Result<List<Category>> getAllCategories() {
        log.info("Get all categories");
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取分类", description = "根据分类ID获取分类详情")
    public Result<Category> getCategoryById(@Parameter(description = "分类ID") @PathVariable Long id) {
        log.info("Get category by id: {}", id);
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    @PostMapping
    @Operation(summary = "创建分类", description = "创建新的文章分类")
    public Result<Category> createCategory(@RequestBody Category category) {
        log.info("Create category: {}", category.getName());
        categoryService.save(category);
        return Result.success("分类创建成功", category);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新分类", description = "根据ID更新分类信息")
    public Result<Category> updateCategory(@Parameter(description = "分类ID") @PathVariable Long id,
                                           @RequestBody Category category) {
        log.info("Update category: {}", id);
        category.setId(id);
        categoryService.updateById(category);
        return Result.success("分类更新成功", category);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除分类", description = "根据ID删除分类")
    public Result<Void> deleteCategory(@Parameter(description = "分类ID") @PathVariable Long id) {
        log.info("Delete category: {}", id);
        categoryService.removeById(id);
        return Result.success("分类删除成功", null);
    }
}