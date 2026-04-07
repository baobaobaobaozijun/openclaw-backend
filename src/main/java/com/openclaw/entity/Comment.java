package com.openclaw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

/**
 * 评论实体类
 */
@Data
@TableName("comments")
public class Comment {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("article_id")
    private Long articleId;
    
    @TableField("user_id")
    private Long userId;
    
    @TableField("content")
    private String content;
    
    @TableField("parent_id")
    private Long parentId;
    
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;
    
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;
    
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}