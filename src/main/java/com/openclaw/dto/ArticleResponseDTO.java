package com.openclaw.dto;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String contentHtml;
    private String summary;
    private String status;
    private Integer accessLevel;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private List<CategoryDTO> categories;
    private List<TagDTO> tags;
}