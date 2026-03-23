package com.openclaw.service.impl;

import com.openclaw.entity.Tag;
import com.openclaw.mapper.TagMapper;
import com.openclaw.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> listByArticleId(Long articleId) {
        // TODO: 通过 article_tags 关联表查询
        return list();
    }

    @Override
    public void incrementUsageCount(Long tagId) {
        Tag tag = getById(tagId);
        if (tag != null) {
            tag.setUsageCount(tag.getUsageCount() + 1);
            updateById(tag);
        }
    }
}
