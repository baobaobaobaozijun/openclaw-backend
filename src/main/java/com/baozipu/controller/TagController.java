package com.baozipu.controller;

import com.baozipu.entity.Tag;
import com.baozipu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    
    @Autowired
    private TagService tagService;
    
    @GetMapping
    public List<Tag> list() {
        return tagService.list();
    }
    
    @GetMapping("/{id}")
    public Tag getById(@PathVariable Long id) {
        return tagService.getById(id);
    }
    
    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagService.create(tag);
    }
    
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Tag tag) {
        tagService.update(id, tag);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }
}