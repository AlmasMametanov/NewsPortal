package org.newsPortal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.newsPortal.dao.CategoryDAO;
import org.newsPortal.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
    }

    @Override
    public List<Category> findAllCategory() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT c FROM Category c WHERE parentCategory.id IS NULL").getResultList();
    }

    @Override
    public void updateCategory(Category updatedCategory) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, updatedCategory.getId());
        category.setCategoryName(updatedCategory.getCategoryName());
        category.setParentCategory(updatedCategory.getParentCategory());
    }

    @Override
    public void deleteById(Long categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, categoryId);
        session.delete(category);
    }
}
