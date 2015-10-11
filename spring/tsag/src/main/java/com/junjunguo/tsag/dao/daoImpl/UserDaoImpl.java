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
        String hql = "from User where email=" + email;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) query.list();

        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }

        return null;
    }

    @Transactional
    public User findByName(String name) {
        String hql = "from User where name=" + name;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) query.list();

        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }

        return null;
    }
}
