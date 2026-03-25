package com.openclaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openclaw.entity.Category;
import com.openclaw.mapper.CategoryMapper;
import com.openclaw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listByParentId(Long parentId) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (parentId == null) {
            wrapper.isNull("parent_id");
        } else {
            wrapper.eq("parent_id", parentId);
        }
        return categoryMapper.selectList(wrapper);
    }

    @Override
    public List<Category> listTree() {
        // 查询所有分类
        List<Category> allCategories = categoryMapper.selectList(null);
        
        // 按 parentId 分组
        Map<Long, List<Category>> categoryMap = new HashMap<>();
        for (Category category : allCategories) {
            Long parentId = category.getParentId();
            if (parentId == null) {
                parentId = 0L; // 将 null 视为 0
            }
            
            if (!categoryMap.containsKey(parentId)) {
                categoryMap.put(parentId, new ArrayList<>());
            }
            categoryMap.get(parentId).add(category);
        }
        
        // 构建树形结构
        List<Category> rootCategories = categoryMap.getOrDefault(0L, new ArrayList<>());
        
        // 为每个根节点添加子节点
        buildTree(rootCategories, categoryMap);
        
        return rootCategories;
    }
    
    /**
     * 递归构建树形结构
     */
    private void buildTree(List<Category> categories, Map<Long, List<Category>> categoryMap) {
        for (Category category : categories) {
            Long categoryId = category.getId();
            List<Category> children = categoryMap.getOrDefault(categoryId, new ArrayList<>());
            // 递归处理子节点
            buildTree(children, categoryMap);
            // 这里只是返回原始分类数据，前端通过 parentId 字段判断层级
        }
    }
}