package com.openclaw.mapper;

import com.openclaw.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    
    @Select("SELECT * FROM article WHERE category_id = #{categoryId}")
    List<Article> selectByCategoryId(@Param("categoryId") Long categoryId);
    
    @Select("SELECT * FROM article WHERE author_id = #{authorId}")
    List<Article> selectByAuthorId(@Param("authorId") Long authorId);
    
    @Select("SELECT * FROM article WHERE status = #{status}")
    List<Article> selectByStatus(@Param("status") String status);
    
    @Update("UPDATE article SET view_count = view_count + 1 WHERE id = #{id}")
    int incrementViewCount(@Param("id") Long id);
}