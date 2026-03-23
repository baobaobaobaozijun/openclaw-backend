package com.openclaw.service;

import com.openclaw.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface TagService extends IService<Tag> {
    List<Tag> listByArticleId(Long articleId);
    void incrementUsageCount(Long tagId);
}
