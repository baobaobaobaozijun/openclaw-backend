package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.entity.Tag;
import com.openclaw.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@io.swagger.v3.oas.annotations.tags.Tag(name = "标签管理", description = "文章标签 CRUD 接口")
public class TagController {

    private final TagService tagService;

    @GetMapping
    @Operation(summary = "获取所有标签", description = "获取文章标签列表")
    public Result<List<Tag>> getAllTags() {
        log.info("Get all tags");
        List<Tag> tags = tagService.list();
        return Result.success(tags);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取标签", description = "根据标签ID获取标签详情")
    public Result<Tag> getTagById(@Parameter(description = "标签ID") @PathVariable Long id) {
        log.info("Get tag by id: {}", id);
        Tag tag = tagService.getById(id);
        return Result.success(tag);
    }

    @PostMapping
    @Operation(summary = "创建标签", description = "创建新的文章标签")
    public Result<Tag> createTag(@RequestBody Tag tag) {
        log.info("Create tag: {}", tag.getName());
        tagService.save(tag);
        return Result.success("标签创建成功", tag);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除标签", description = "根据ID删除标签")
    public Result<Void> deleteTag(@Parameter(description = "标签ID") @PathVariable Long id) {
        log.info("Delete tag: {}", id);
        tagService.removeById(id);
        return Result.success("标签删除成功", null);
    }
}