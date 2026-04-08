package com.baozipu.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import com.openclaw.mapper.ArticleMapper;

@Component
public class ArticlePublishScheduler {
    
    private static final Logger logger = LoggerFactory.getLogger(ArticlePublishScheduler.class);
    
    @Autowired
    private ArticleMapper articleMapper;
    
    /**
     * 每分钟执行一次，检查草稿文章并自动发布
     */
    @Scheduled(fixedRate = 60000)
    public void checkAndPublishDraftArticles() {
        logger.info("开始执行文章发布调度任务");
        
        try {
            // 查询所有状态为 DRAFT 的文章
            List<Long> draftArticleIds = articleMapper.findDraftArticleIds();
            
            if (draftArticleIds != null && !draftArticleIds.isEmpty()) {
                logger.info("发现 {} 篇草稿文章待处理", draftArticleIds.size());
                
                for (Long articleId : draftArticleIds) {
                    // 更新文章状态为 PUBLISHED
                    int updatedRows = articleMapper.updateStatus(articleId, "PUBLISHED");
                    
                    if (updatedRows > 0) {
                        logger.info("成功发布文章 ID: {}", articleId);
                    } else {
                        logger.warn("发布文章失败，ID: {}", articleId);
                    }
                }
            } else {
                logger.info("没有需要发布的草稿文章");
            }
        } catch (Exception e) {
            logger.error("文章发布调度任务执行失败", e);
        }
        
        logger.info("文章发布调度任务执行完毕");
    }
}