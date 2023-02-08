package org.newsPortal.services.impl;

import org.newsPortal.dao.ArticleDAO;
import org.newsPortal.models.Article;
import org.newsPortal.services.ArticleService;
import org.newsPortal.util.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDAO articleDAO;

    @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    @Transactional
    public void createArticle(Article article) {
        articleDAO.createArticle(article);
    }

    @Override
    public List<Article> findAllArticleByCategoryId(Long categoryId) {
        return articleDAO.findAllByCategoryId(categoryId);
    }

    @Override
    public Article findById(Long articleId) {
        return articleDAO.findById(articleId).orElseThrow(() -> new PageNotFoundException("Page cannot be found"));
    }

    @Override
    @Transactional
    public void updateById(Article updatedArticle) {
        articleDAO.updateById(updatedArticle);
    }

    @Override
    @Transactional
    public void deleteById(Long articleId) {
        articleDAO.deleteById(articleId);
    }
}
