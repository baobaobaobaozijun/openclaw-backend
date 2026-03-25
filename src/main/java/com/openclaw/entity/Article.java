package com.openclaw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章实体类
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("articles")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 渲染后的HTML内容
     */
    @TableField("content_html")
    private String contentHtml;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 作者 ID
     */
    private Long authorId;

    /**
     * 文章状态：DRAFT-草稿，PUBLISHED-已发布
     */
    private String status;

    /**
     * 访问级别：0-公开，1-登录可见，2-VIP 可见
     */
    @TableField("access_level")
    private Integer accessLevel;

    /**
     * 浏览次数
     */
    @TableField("view_count")
    private Integer viewCount;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private Integer deleted;
}
