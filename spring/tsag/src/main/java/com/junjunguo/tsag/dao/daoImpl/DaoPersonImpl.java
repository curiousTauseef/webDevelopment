package com.junjunguo.tsag.dao.daoImpl;

import com.junjunguo.tsag.dao.DaoPerson;
import com.junjunguo.tsag.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 06, 2015.
 */
@Repository("daoPerson")
public class DaoPersonImpl implements DaoPerson {
    private SessionFactory sessionFactory;
    private Session session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Person person) {
        this.session = this.sessionFactory.getCurrentSession();
        this.session.save(person);
    }

    public void delete(Person person) {
        this.session = this.sessionFactory.getCurrentSession();
        this.session.delete(person);
    }

    public void update(Person person) {
        this.session = this.sessionFactory.getCurrentSession();
        this.session.update(person);
    }

    public Person getByEmail(String email) {
        this.session = this.sessionFactory.getCurrentSession();
        Query q = this.session.createQuery("from Person where email = " + email);
        return !q.list().isEmpty() ? (Person) q.list().get(0) : null;
    }

    /**
     * @param username
     *
     * @return the first username on the list
     */
    public Person getByUsername(String username) {
        this.session = this.sessionFactory.getCurrentSession();
        Query q = this.session.createQuery("from Person where username = '" + username + "'");
        return !q.list().isEmpty() ? (Person) q.list().get(0) : null;
    }
}
