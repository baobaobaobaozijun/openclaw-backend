package com.openclaw.service;

import com.openclaw.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> listByParentId(Long parentId);
    List<Category> listTree();
}
