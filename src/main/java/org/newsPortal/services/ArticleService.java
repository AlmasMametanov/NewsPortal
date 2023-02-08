package org.newsPortal.services;

import org.newsPortal.models.Article;

import java.util.List;

public interface ArticleService {
    void createArticle(Article article);
    List<Article> findAllArticleByCategoryId(Long categoryId);
    Article findById(Long articleId);
    void updateById(Article updatedArticle);
    void deleteById(Long articleId);
}
