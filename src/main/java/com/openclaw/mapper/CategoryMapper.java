package com.openclaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openclaw.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * Category Mapper 接口
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    // 继承 BaseMapper 后自动拥有 CRUD 方法
    // 如需自定义查询方法，可在此处添加
}