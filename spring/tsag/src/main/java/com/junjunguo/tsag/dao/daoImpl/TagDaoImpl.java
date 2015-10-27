package com.junjunguo.tsag.dao.daoImpl;

import com.junjunguo.tsag.dao.TagDao;
import com.junjunguo.tsag.model.TU;
import com.junjunguo.tsag.model.Tag;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
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

    public Tag findByLabelInitialized(String label) {
        log("find by label initialized: ");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class);
        criteria.add(Restrictions.eq("label", label));
        Tag tag = (Tag) criteria.uniqueResult();
        log("find by label initialized: tag= " + tag);
        if (tag != null) {
            log("get user: " + tag.getUsers());
            Hibernate.initialize(tag.getUsers());
            log("get user(after init ..): " + tag.getUsers());
            //            List<User> u = new ArrayList<User>();
            //            for (User user : tag.getUsers()) {
            //                user.setTags(null);
            //                u.add(user);
            //            }
            //            tag.setUsers(u);
            log("get user(after init .. new ..): " + tag.getUsers());
        }
        ;

        return new TU(tag.getId(), tag.getLabel(), tag.getUsers());
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
        log("tags:-- " + tags);
        return tags;
    }

    @Transactional
    public void saveTag(String tag) {
        log("save tag: " + tag);
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

    public void log(String s) {
        System.out.print("\n----------" + this.getClass().getSimpleName() + " " + s);
    }


}
