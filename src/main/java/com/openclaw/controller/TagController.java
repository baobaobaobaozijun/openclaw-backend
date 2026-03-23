package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.entity.Tag;
import com.openclaw.mapper.TagMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagMapper tagMapper;

    @GetMapping
    public Result<List<Tag>> list() {
        List<Tag> tags = tagMapper.selectList(
            new QueryWrapper<Tag>().orderByDesc("usage_count")
        );
        return Result.success(tags);
    }

    @GetMapping("/{id}")
    public Result<Tag> getById(@PathVariable Long id) {
        Tag tag = tagMapper.selectById(id);
        if (tag == null) {
            return Result.error("标签不存在");
        }
        return Result.success(tag);
    }

    @PostMapping
    public Result<Tag> create(@RequestBody Tag tag) {
        tagMapper.insert(tag);
        return Result.success(tag);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        tagMapper.deleteById(id);
        return Result.success(null);
    }
}
