package org.newsPortal.services;

import org.newsPortal.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByEmail(String email);
    void createUser(User user);
    void updateEmailById(User updatedUser);
    void updatePasswordById(User updatedUser);
}
