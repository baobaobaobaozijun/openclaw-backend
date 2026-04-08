package com.openclaw.scheduler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.openclaw.entity.Article;
import com.openclaw.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章自动发布定时任务
 * 每分钟检查草稿文章，自动发布到期的文章
 */
@Component
@Slf4j
public class ArticlePublishScheduler {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 每分钟执行一次，检查并自动发布到期的草稿文章
     */
    @Scheduled(fixedRate = 60000)
    public void autoPublishArticles() {
        log.info("开始执行自动发布草稿文章任务");
        
        try {
            // 查询所有状态为草稿的文章（status: 0=草稿，1=已发布）
            LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Article::getStatus, 0);
            
            List<Article> draftArticles = articleMapper.selectList(wrapper);
            
            if (draftArticles != null && !draftArticles.isEmpty()) {
                int publishCount = 0;
                
                for (Article article : draftArticles) {
                    // 更新文章状态为已发布
                    article.setStatus("PUBLISHED");
                    articleMapper.updateById(article);
                    
                    log.info("自动发布文章：{}", article.getTitle());
                    publishCount++;
                }
                
                if (publishCount > 0) {
                    log.info("本次任务共自动发布了 {} 篇文章", publishCount);
                } else {
                    log.info("本次任务未发现需要发布的文章");
                }
            } else {
                log.info("当前没有草稿文章");
            }
        } catch (Exception e) {
            log.error("自动发布草稿文章任务执行失败", e);
        }
        
        log.info("自动发布草稿文章任务执行完毕");
    }
}