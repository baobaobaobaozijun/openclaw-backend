package com.openclaw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("articles")
public class Article {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String title;
    
    private String content;
    
    private String summary;
    
    private Long authorId;
    
    private String status; // DRAFT, PUBLISHED
    
    private Integer accessLevel; // 0-公开，1-付费，2-会员
    
    private Integer viewCount;
    
    private LocalDateTime publishedAt;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
