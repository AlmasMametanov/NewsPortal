package org.newsPortal.dao;

import org.newsPortal.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    void createArticle(Article article);
    List<Article> findAllByCategoryId(Long categoryId);
    Optional<Article> findById(Long articleId);
    void updateById(Article updatedArticle);
    void deleteById(Long articleId);
}
