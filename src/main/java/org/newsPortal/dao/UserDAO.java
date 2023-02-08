package org.newsPortal.dao;

import org.newsPortal.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    void createUser(User user);
    Boolean checkExistsEmail(String email);
    void updateEmailById(User updatedUser);
    void updatePasswordById(User updatedUser);
}
