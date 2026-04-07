package com.openclaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openclaw.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    
    /**
     * 根据文章 ID 查询评论列表（按创建时间倒序）
     */
    List<Comment> selectByArticleId(@Param("articleId") Long articleId);
    
    /**
     * 根据用户 ID 查询评论列表
     */
    List<Comment> selectByUserId(@Param("userId") Long userId);
}