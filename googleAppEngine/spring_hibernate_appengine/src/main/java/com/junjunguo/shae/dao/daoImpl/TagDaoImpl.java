package com.junjunguo.shae.dao.daoImpl;

import com.junjunguo.shae.dao.TagDao;
import com.junjunguo.shae.model.Tag;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Repository
public class TagDaoImpl implements TagDao {
    @Autowired private SessionFactory sessionFactory;

    public TagDaoImpl() {
    }

    public TagDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Tag findByLabel(String label) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class);
        criteria.add(Restrictions.eq("label", label));
        Tag tag = (Tag) criteria.uniqueResult();
        return tag;
    }

    @Transactional
    public Tag findById(long id) {
        try {
            Query q = sessionFactory.getCurrentSession().createQuery("from Tag where ID = '" + id + "'");
            System.out.println("q: " + q);
            System.out.println("q.list: " + q.list());

            return !q.list().isEmpty() ? (Tag) q.list().get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public List<Tag> findAllTags() {
        @SuppressWarnings("unchecked")
        List<Tag> tags = (List<Tag>) sessionFactory.getCurrentSession()
                                                   .createCriteria(Tag.class)
                                                   .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return tags;
    }

    public void saveTag(Tag tag) {
        sessionFactory.getCurrentSession().saveOrUpdate(tag);
    }
}
