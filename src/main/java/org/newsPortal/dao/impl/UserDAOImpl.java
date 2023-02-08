package org.newsPortal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.newsPortal.dao.UserDAO;
import org.newsPortal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT u FROM User u";
        return session.createQuery(hql).getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT u FROM User u WHERE u.email = :email";
        Optional<User> user;
        try {
            user = Optional.ofNullable((User) session.createQuery(hql).setParameter("email", email).getSingleResult());
        } catch (NoResultException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public Boolean checkExistsEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT u FROM User u WHERE u.email = :email";
        Optional<User> user;
        try {
            user = Optional.ofNullable((User) session.createQuery(hql)
                    .setParameter("email", email).getSingleResult());
        } catch (NoResultException e) {
            user = Optional.empty();
        }
        return user.isPresent();
    }

    @Override
    public void createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void updateEmailById(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, updatedUser.getId());
        user.setEmail(updatedUser.getEmail());
    }

    @Override
    public void updatePasswordById(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, updatedUser.getId());
        user.setPassword(updatedUser.getPassword());
    }

}
