package org.newsPortal.dao;

import org.newsPortal.models.Category;

import java.util.List;

public interface CategoryDAO {
    void createCategory(Category category);
    List<Category> findAllCategory();
    void updateCategory(Category updatedCategory);
    void deleteById(Long categoryId);
}
