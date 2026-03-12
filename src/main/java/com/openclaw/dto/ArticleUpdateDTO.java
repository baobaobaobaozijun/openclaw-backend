package com.openclaw.dto;

import lombok.Data;

/**
 * 文章数据传输对象 - 用于更新文章
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Data
public class ArticleUpdateDTO {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章状态：DRAFT-草稿，PUBLISHED-已发布
     */
    private String status;

    /**
     * 访问级别：0-公开，1-登录可见，2-VIP 可见
     */
    private Integer accessLevel;
}
