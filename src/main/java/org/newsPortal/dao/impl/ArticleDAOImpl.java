package org.newsPortal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.newsPortal.dao.ArticleDAO;
import org.newsPortal.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ArticleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createArticle(Article article) {
        Session session = sessionFactory.getCurrentSession();
        article.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        session.save(article);
    }

    @Override
    public List<Article> findAllByCategoryId(Long categoryId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT a FROM Article a WHERE a.category.id = :categoryId", Article.class)
                .setParameter("categoryId", categoryId).getResultList();
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Article.class, articleId));
    }

    @Override
    public void updateById(Article updatedArticle) {
        Session session = sessionFactory.getCurrentSession();
        Article article = session.get(Article.class, updatedArticle.getId());
        article.setHeadline(updatedArticle.getHeadline());
        article.setContent(updatedArticle.getContent());
        article.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
        article.setCategory(updatedArticle.getCategory());
    }

    @Override
    public void deleteById(Long articleId) {
        Session session = sessionFactory.getCurrentSession();
        Article article = session.get(Article.class, articleId);
        session.delete(article);
    }
}
