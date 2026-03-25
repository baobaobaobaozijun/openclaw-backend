package com.openclaw.dto;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

/**
 * 文章数据传输对象 - 用于响应
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
@Data
@Builder
public class ArticleResponseDTO {

    /**
     * 文章 ID
     */
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
    private Integer accessLevel;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
