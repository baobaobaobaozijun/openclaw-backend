package com.openclaw.mapper;

import com.openclaw.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章 Mapper 接口
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
