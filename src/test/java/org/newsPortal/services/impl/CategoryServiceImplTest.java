package org.newsPortal.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newsPortal.dao.CategoryDAO;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryDAO categoryDAO;

    @Test
    void canCreateCategory() {
        // when
        categoryService.createCategory(any());
        // then
        verify(categoryDAO).createCategory(any());
    }

    @Test
    void canFindAllCategory() {
        // when
        categoryService.findAllCategory();
        // then
        verify(categoryDAO).findAllCategory();
    }

    @Test
    void canUpdateCategory() {
        // when
        categoryService.updateCategory(any());
        // then
        verify(categoryDAO).updateCategory(any());
    }

    @Test
    void canDeleteById() {
        // when
        categoryService.deleteById(anyLong());
        // then
        verify(categoryDAO).deleteById(anyLong());
    }
}