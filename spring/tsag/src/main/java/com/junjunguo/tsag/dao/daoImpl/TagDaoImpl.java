package com.junjunguo.tsag.dao.daoImpl;

import com.junjunguo.tsag.dao.TagDao;
import com.junjunguo.tsag.model.Tag;
import com.junjunguo.tsag.model.User;
import org.hibernate.*;
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
        Query q = sessionFactory.getCurrentSession().createQuery("from Tag where id = :tagid ");
        q.setParameter("tagid", id);
        if (q.list().isEmpty()) {
            return null;
        } else {
            Tag tag = (Tag) q.list().get(0);
            Hibernate.initialize(tag.getUsers());
            return tag;
        }
    }

    @Transactional
    public Tag findTagByLabel(String label) {
        try {

            Query q = sessionFactory.getCurrentSession().createQuery("from Tag where label = :tagLabel ");
            q.setParameter("tagLabel", label);
            if (q.list().isEmpty()) {
                return null;
            } else {
                Tag tag = (Tag) q.list().get(0);
                Hibernate.initialize(tag.getUsers());
                return tag;
            }

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    @Transactional
    public List<Tag> findAllTags() {
        @SuppressWarnings("unchecked")
        List<Tag> tags = (List<Tag>) sessionFactory.getCurrentSession().createCriteria(Tag.class)
                                                   .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        for (Tag tag : tags) {
            Hibernate.initialize(tag.getUsers());
        }
        return tags;
    }

    @Transactional
    public void saveTag(String tag) {
        System.out.println("save tag: " + tag);
        sessionFactory.getCurrentSession().saveOrUpdate(new Tag(tag));
    }

    @Transactional
    public void saveTag(Tag tag) {
        sessionFactory.getCurrentSession().saveOrUpdate(tag);
    }

    @Transactional
    public void deleteTag(String tag) {
        sessionFactory.getCurrentSession().delete(tag);
    }

    //    private void System.out.println(String s) {
    //        System.out.println(" #----" + this.getClass().getSimpleName() + " " + s);
    //    }

}
