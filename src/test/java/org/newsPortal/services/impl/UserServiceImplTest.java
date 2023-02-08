package org.newsPortal.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newsPortal.dao.UserDAO;
import org.newsPortal.models.User;
import org.newsPortal.util.EmailAlreadyExistsException;
import org.newsPortal.util.PageNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    @Test
    void canFindAllUser() {
        // when
        userService.findAll();
        // then
        verify(userDAO).findAll();
    }

    @Test
    void willThrowExceptionWhenUserIsNotExists() {
        // given
        String email = "user@mail.com";
        when(userDAO.findByEmail(email)).thenReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> userService.findByEmail(email))
                .isInstanceOf(PageNotFoundException.class)
                .hasMessageContaining("Page cannot be found");
    }

    @Test
    void canCreateUser() {
        // given
        User user = new User("user@mail.com", "qwerty");
        // when
        userService.createUser(user);
        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userDAO).createUser(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(user, capturedUser);
    }

    @Test
    void willThrowExceptionWhenEmailIsTaken() {
        // given
        User user = new User("user@mail.com", "qwerty");
        when(userDAO.checkExistsEmail(anyString())).thenReturn(true);
        // when
        // then
        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(EmailAlreadyExistsException.class)
                .hasMessageContaining("Email " + user.getEmail() + " taken");
        verify(userDAO, never()).createUser(any());
    }

    @Test
    void canUpdateEmailById() {
        // when
        userService.updateEmailById(any());
        // then
        verify(userDAO).updateEmailById(any());
    }

    @Test
    void updatePasswordById() {
        // when
        userService.updatePasswordById(any());
        // then
        verify(userDAO).updatePasswordById(any());
    }
}