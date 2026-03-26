package com.baozipu.service.impl;

import com.baozipu.service.TagService;
import com.baozipu.entity.Tag;
import com.baozipu.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    
    @Autowired
    private TagMapper tagMapper;
    
    @Override
    public List<Tag> list() {
        return tagMapper.selectList(null);
    }
    
    @Override
    public Tag getById(Long id) {
        return tagMapper.selectById(id);
    }
    
    @Override
    public Tag create(Tag tag) {
        tagMapper.insert(tag);
        return tag;
    }
    
    @Override
    public void update(Long id, Tag tag) {
        tag.setId(id);
        tagMapper.updateById(tag);
    }
    
    @Override
    public void delete(Long id) {
        tagMapper.deleteById(id);
    }
}