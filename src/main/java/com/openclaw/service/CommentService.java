package com.openclaw.service;

import com.openclaw.entity.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> findByArticleId(Long articleId);
    Comment create(Long articleId, Long authorId, String content);
    void delete(Long id);
}