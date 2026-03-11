package com.openclaw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分类实体类
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类 slug（URL 友好）
     */
    private String slug;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 父分类 ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private Integer deleted;
}
