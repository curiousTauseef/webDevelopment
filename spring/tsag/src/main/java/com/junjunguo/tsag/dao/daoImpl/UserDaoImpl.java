package com.junjunguo.tsag.dao.daoImpl;

import com.junjunguo.tsag.dao.UserDao;
import com.junjunguo.tsag.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 11, 2015.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {

    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<User> findAllUsers() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listUser;
    }

    @Transactional
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        User userToDelete = new User();
        userToDelete.setEmail(email);
        sessionFactory.getCurrentSession().delete(userToDelete);
    }

    @Transactional
    public User findByEmail(String email) {
        System.out.println("find by email user dao impl: " + email);
        Query q = sessionFactory.getCurrentSession().createQuery("from User where email = '" + email + "'");
        System.out.println("find by email user dao impl-----");
        return !q.list().isEmpty() ? (User) q.list().get(0) : null;
    }

    @Transactional
    public User findByName(String name) {
        Query q = sessionFactory.getCurrentSession().createQuery("from User where name = '" + name + "'");
        return !q.list().isEmpty() ? (User) q.list().get(0) : null;
    }
}
