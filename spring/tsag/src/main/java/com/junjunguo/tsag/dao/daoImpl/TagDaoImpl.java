package com.junjunguo.tsag.dao.daoImpl;

import com.junjunguo.tsag.dao.TagDao;
import com.junjunguo.tsag.model.Tag;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
 */
@Repository
public class TagDaoImpl implements TagDao {
    @Autowired
    private SessionFactory sessionFactory;

    public TagDaoImpl() {
    }

    public TagDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Tag findTagById(int id) {
        Query q = sessionFactory.getCurrentSession().createQuery("from TAG where ID = '" + id + "'");
        return !q.list().isEmpty() ? (Tag) q.list().get(0) : null;
    }

    @Transactional
    public Tag findTagByLabel(String label) {
        Query q = sessionFactory.getCurrentSession().createQuery("from TAG where LABEL = '" + label + "'");
        return !q.list().isEmpty() ? (Tag) q.list().get(0) : null;
    }

    @Transactional
    public List<Tag> findAllTags() {
        @SuppressWarnings("unchecked")
        List<Tag> tags = (List<Tag>) sessionFactory.getCurrentSession().createCriteria(Tag.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return tags;
    }

    public void saveTag(String tag) {
        sessionFactory.getCurrentSession().saveOrUpdate(tag);
    }

    public void deleteTag(String tag) {
        sessionFactory.getCurrentSession().delete(tag);
    }
}
