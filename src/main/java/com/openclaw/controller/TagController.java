package com.openclaw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openclaw.entity.Tag;
import com.openclaw.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签 Controller
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取标签列表（支持分页）
     */
    @GetMapping("/page")
    public Page<Tag> getTagsByPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return tagService.page(new Page<>(pageNum, pageSize));
    }

    /**
     * 根据文章 ID 获取标签
     */
    @GetMapping("/byArticle")
    public List<Tag> getTagsByArticleId(@RequestParam Long articleId) {
        return tagService.listByArticleId(articleId);
    }

    /**
     * 获取所有标签
     */
    @GetMapping("/")
    public List<Tag> getAllTags() {
        return tagService.list();
    }

    /**
     * 创建标签
     */
    @PostMapping("/")
    public Tag createTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return tag;
    }

    /**
     * 更新标签
     */
    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        tag.setId(id);
        tagService.updateById(tag);
        return tag;
    }

    /**
     * 删除标签
     */
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.removeById(id);
    }
}
