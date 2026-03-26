package com.baozipu.mapper;

import com.baozipu.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> selectByParentId(Long parentId);
}