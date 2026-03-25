package com.openclaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openclaw.entity.Tag;
import com.openclaw.mapper.TagMapper;
import com.openclaw.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> listAll() {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("usage_count");
        wrapper.eq("deleted", false);
        return tagMapper.selectList(wrapper);
    }

    @Override
    public Tag getById(Long id) {
        return tagMapper.selectById(id);
    }

    @Override
    public Tag create(Tag tag) {
        // 检查 name 是否重复
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("name", tag.getName());
        wrapper.eq("deleted", false);
        Tag existingTag = tagMapper.selectOne(wrapper);
        
        if (existingTag != null) {
            throw new RuntimeException("标签名称已存在: " + tag.getName());
        }
        
        // 设置默认值
        tag.setCreatedAt(LocalDateTime.now());
        tag.setUsageCount(tag.getUsageCount() != null ? tag.getUsageCount() : 0);
        tag.setDeleted(false);
        
        tagMapper.insert(tag);
        return tag;
    }

    @Override
    public void delete(Long id) {
        Tag existingTag = tagMapper.selectById(id);
        if (existingTag == null) {
            throw new RuntimeException("标签不存在，ID: " + id);
        }
        
        // 软删除：更新 deleted 字段为 true
        existingTag.setDeleted(true);
        tagMapper.updateById(existingTag);
    }
}