package com.openclaw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 标签实体类
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tags")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签 slug（URL 友好）
     */
    private String slug;

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
