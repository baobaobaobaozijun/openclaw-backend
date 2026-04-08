package com.baozipu.controller;

import com.baozipu.entity.Tag;
import com.baozipu.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagMapper tagMapper;

    @GetMapping
    public List<Tag> list() {
        return tagMapper.selectList(null);
    }

    @PostMapping
    public boolean create(@RequestBody Tag tag) {
        return tagMapper.insert(tag) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return tagMapper.deleteById(id) > 0;
    }
}