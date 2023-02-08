package org.newsPortal.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newsPortal.dao.ArticleDAO;
import org.newsPortal.util.PageNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleDAO articleDAO;

    @Test
    void canCreateArticle() {
        // when
        articleService.createArticle(any());
        // then
        verify(articleDAO).createArticle(any());
    }

    @Test
    void canFindAllArticleByCategoryId() {
        // when
        articleService.findAllArticleByCategoryId(anyLong());
        // then
        verify(articleDAO).findAllByCategoryId(anyLong());
    }

    @Test
    void willThrowExceptionIfArticleIsNotExists() {
        // given
        Long id = 1l;
        when(articleDAO.findById(id)).thenReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> articleService.findById(id))
                .isInstanceOf(PageNotFoundException.class)
                .hasMessageContaining("Page cannot be found");
    }

    @Test
    void canUpdateById() {
        // when
        articleService.updateById(any());
        // then
        verify(articleDAO).updateById(any());
    }

    @Test
    void canDeleteById() {
        // when
        articleService.deleteById(anyLong());
        // then
        verify(articleDAO).deleteById(anyLong());
    }
}