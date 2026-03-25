package com.openclaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openclaw.entity.Tag;
import com.openclaw.mapper.TagMapper;
import com.openclaw.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> listByArticleId(Long articleId) {
        // TODO: 需要关联表 article_tags 查询，暂返回空列表
        return List.of();
    }

    @Override
    public void incrementUsageCount(Long tagId) {
        // Tag 实体没有 usageCount 字段，此方法暂为空实现
        // TODO: 后续添加 usageCount 字段后实现
    }
}
