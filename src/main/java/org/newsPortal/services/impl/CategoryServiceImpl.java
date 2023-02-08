package org.newsPortal.services.impl;

import org.newsPortal.dao.CategoryDAO;
import org.newsPortal.models.Category;
import org.newsPortal.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public void createCategory(Category category) {
        categoryDAO.createCategory(category);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryDAO.findAllCategory();
    }

    @Override
    @Transactional
    public void updateCategory(Category updatedCategory) {
        categoryDAO.updateCategory(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteById(Long categoryId) {
        categoryDAO.deleteById(categoryId);
    }
}
