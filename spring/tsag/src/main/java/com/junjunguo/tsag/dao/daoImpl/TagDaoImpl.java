package com.junjunguo.tsag.dao.daoImpl;

import com.junjunguo.tsag.dao.TagDao;
import com.junjunguo.tsag.model.Tag;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
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
        System.out.println("tag dao - find tag by id: " + id);
        //        Query q = sessionFactory.getCurrentSession().createQuery("from TAG where ID = '" + id + "'");
        Query q = sessionFactory.getCurrentSession().createQuery("from Tag where id = :tagid ");
        System.out.println("q set p:");
        q.setParameter("tagid", id);
        List l = q.list();
        System.out.println("list string: " + l.toString());

        return !l.isEmpty() ? (Tag) l.get(0) : null;
    }

    @Transactional
    public Tag findTagByLabel(String label) {
        try {

            Query q = sessionFactory.getCurrentSession().createQuery("from Tag where label = :tagLabel ");
            q.setParameter("tagLabel", label);
            //            System.out.println("list: ");
            List l = q.list();
            //            System.out.println("list string: " + l.toString());

            return !l.isEmpty() ? (Tag) l.get(0) : null;
            //            return !q.list().isEmpty() ? (Tag) q.list().get(0) : null;


            //            System.out.println("get session");
            //            Session session = sessionFactory.getCurrentSession();
            //            System.out.println("session: ");
            //            System.out.println("session: " + session.toString());
            //
            //            Tag tag = (Tag)
            //                    session.createCriteria(Tag.class, label).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list()
            //                           .get(0);
            //            System.out.println("query start" + tag.toString());

            //            return tag;
            //            Query qq = session.createQuery("from TAG where LABEL = '" + label + "'");
            //            System.out.println("query: " + qq.toString());
            //            Query q = sessionFactory.getCurrentSession().createQuery("from TAG where LABEL = '" + label + "'");
            //            return !q.list().isEmpty() ? (Tag) q.list().get(0) : null;
        } catch (Exception e) {
            //        } catch (org.hibernate.HibernateException e) {
            e.getStackTrace();
            return null;
        }
    }

    @Transactional
    public List<Tag> findAllTags() {
        @SuppressWarnings("unchecked")
        List<Tag> tags = (List<Tag>) sessionFactory.getCurrentSession().createCriteria(Tag.class)
                                                   .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return tags;
    }

    @Transactional
    public void saveTag(String tag) {
        System.out.println("save tag: " + tag);
        sessionFactory.getCurrentSession().saveOrUpdate(new Tag(tag));
    }

    @Transactional
    public void deleteTag(String tag) {
        sessionFactory.getCurrentSession().delete(tag);
    }

    //    private void System.out.println(String s) {
    //        System.out.println(" #----" + this.getClass().getSimpleName() + " " + s);
    //    }

}
