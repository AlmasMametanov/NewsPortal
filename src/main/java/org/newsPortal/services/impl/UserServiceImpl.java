package org.newsPortal.services.impl;

import org.newsPortal.dao.UserDAO;
import org.newsPortal.models.User;
import org.newsPortal.services.UserService;
import org.newsPortal.util.EmailAlreadyExistsException;
import org.newsPortal.util.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new PageNotFoundException("Page cannot be found"));
    }

    @Override
    @Transactional
    public void createUser(User user) {
        Boolean existsEmail = userDAO.checkExistsEmail(user.getEmail());
        if (existsEmail) {
            throw new EmailAlreadyExistsException("Email " + user.getEmail() + " taken");
        }
        userDAO.createUser(user);
    }

    @Override
    @Transactional
    public void updateEmailById(User updatedUser) {
        userDAO.updateEmailById(updatedUser);
    }

    @Override
    @Transactional
    public void updatePasswordById(User updatedUser) {
        userDAO.updatePasswordById(updatedUser);
    }
}
