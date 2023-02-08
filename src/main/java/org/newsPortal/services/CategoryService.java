package org.newsPortal.services;

import org.newsPortal.models.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(Category category);
    List<Category> findAllCategory();
    void updateCategory(Category updatedCategory);
    void deleteById(Long categoryId);
}
