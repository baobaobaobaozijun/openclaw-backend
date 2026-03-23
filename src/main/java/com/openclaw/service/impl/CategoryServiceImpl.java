package com.openclaw.service.impl;

import com.openclaw.entity.Category;
import com.openclaw.mapper.CategoryMapper;
import com.openclaw.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> listByParentId(Long parentId) {
        return list(new QueryWrapper<Category>()
            .eq("parent_id", parentId)
            .eq("status", 1)
            .orderByAsc("sort_order"));
    }

    @Override
    public List<Category> listTree() {
        List<Category> all = list(new QueryWrapper<Category>().eq("status", 1).orderByAsc("sort_order"));
        return buildTree(all, 0L);
    }

    private List<Category> buildTree(List<Category> all, Long parentId) {
        return all.stream()
            .filter(c -> c.getParentId().equals(parentId))
            .collect(Collectors.toList());
    }
}
